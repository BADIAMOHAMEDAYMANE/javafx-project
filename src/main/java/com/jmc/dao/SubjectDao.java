package com.jmc.dao;

import com.jmc.model.Subject;
import java.sql.SQLException;
import java.util.List;

public interface SubjectDao {
    void addSubject(Subject subject) throws SQLException;
    Subject getSubjectByName(String name) throws SQLException;
    List<Subject> getAllSubjects() throws SQLException;
    Integer getSubjectIdByName(String name) throws SQLException;
}