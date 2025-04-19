package com.jmc.dao.impl;

import com.jmc.dao.SubjectDao;
import com.jmc.dao.config.DatabaseConnection;
import com.jmc.model.Subject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImpl implements SubjectDao {
    private static final String INSERT_SQL = "INSERT INTO subjects (subject_name) VALUES (?)";
    private static final String SELECT_BY_NAME_SQL = "SELECT * FROM subjects WHERE subject_name = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM subjects";

    @Override
    public void addSubject(Subject subject) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, subject.getName());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    subject.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Subject getSubjectByName(String name) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_NAME_SQL)) {

            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToSubject(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Subject> getAllSubjects() throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {

            while (rs.next()) {
                subjects.add(mapRowToSubject(rs));
            }
        }
        return subjects;
    }

    @Override
    public Integer getSubjectIdByName(String name) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT subject_id FROM subjects WHERE subject_name = ?")) {

            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("subject_id");
                }
            }
        }
        return null;
    }

    private Subject mapRowToSubject(ResultSet rs) throws SQLException {
        Subject subject = new Subject(rs.getString("subject_name"));
        subject.setId(rs.getInt("subject_id"));
        return subject;
    }
}