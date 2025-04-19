package com.jmc.dao.impl;

import com.jmc.dao.config.DatabaseConnection;
import com.jmc.dao.QuestionDao;
import com.jmc.model.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private static final String INSERT_SQL = "INSERT INTO questions (subject_id, question_text) VALUES (?, ?)";
    private static final String SELECT_BY_SUBJECT_SQL = "SELECT * FROM questions WHERE subject_id = ?";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM questions WHERE question_id = ?";

    @Override
    public void addQuestion(Question question) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, question.getSubjectId());
            stmt.setString(2, question.getText());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    question.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public List<Question> getQuestionsBySubject(int subjectId) throws SQLException {
        List<Question> questions = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_SUBJECT_SQL)) {

            stmt.setInt(1, subjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    questions.add(mapRowToQuestion(rs));
                }
            }
        }
        return questions;
    }

    @Override
    public Question getQuestionById(int questionId) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {

            stmt.setInt(1, questionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToQuestion(rs);
                }
            }
        }
        return null;
    }

    private Question mapRowToQuestion(ResultSet rs) throws SQLException {
        Question question = new Question(
                rs.getInt("subject_id"),
                rs.getString("question_text")
        );
        question.setId(rs.getInt("question_id"));
        return question;
    }
}