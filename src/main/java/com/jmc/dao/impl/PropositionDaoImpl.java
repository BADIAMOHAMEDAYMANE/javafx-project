package com.jmc.dao.impl;

import com.jmc.dao.PropositionDao;
import com.jmc.dao.config.DatabaseConnection;
import com.jmc.model.Proposition;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropositionDaoImpl implements PropositionDao {
    private static final String INSERT_SQL = "INSERT INTO propositions (question_id, proposition_text, is_correct) VALUES (?, ?, ?)";
    private static final String SELECT_BY_QUESTION_SQL = "SELECT * FROM propositions WHERE question_id = ?";

    @Override
    public void addProposition(Proposition proposition) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, proposition.getQuestionId());
            stmt.setString(2, proposition.getText());
            stmt.setBoolean(3, proposition.isCorrect());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    proposition.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void addPropositions(List<Proposition> propositions) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            for (Proposition proposition : propositions) {
                stmt.setInt(1, proposition.getQuestionId());
                stmt.setString(2, proposition.getText());
                stmt.setBoolean(3, proposition.isCorrect());
                stmt.addBatch();
            }

            stmt.executeBatch();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                int i = 0;
                while (rs.next()) {
                    propositions.get(i++).setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public List<Proposition> getPropositionsByQuestion(int questionId) throws SQLException {
        List<Proposition> propositions = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_QUESTION_SQL)) {

            stmt.setInt(1, questionId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    propositions.add(mapRowToProposition(rs));
                }
            }
        }
        return propositions;
    }

    private Proposition mapRowToProposition(ResultSet rs) throws SQLException {
        Proposition proposition = new Proposition(
                rs.getInt("question_id"),
                rs.getString("proposition_text"),
                rs.getBoolean("is_correct")
        );
        proposition.setId(rs.getInt("proposition_id"));
        return proposition;
    }
}