package com.jmc.dao;


import com.jmc.model.Question;
import java.sql.SQLException;
import java.util.List;

public interface QuestionDao {
    void addQuestion(Question question) throws SQLException;
    List<Question> getQuestionsBySubject(int subjectId) throws SQLException;
    Question getQuestionById(int questionId) throws SQLException;
}