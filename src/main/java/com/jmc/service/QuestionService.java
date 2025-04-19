package com.jmc.service;

import com.jmc.dao.PropositionDao;
import com.jmc.dao.QuestionDao;
import com.jmc.dao.SubjectDao;
import com.jmc.model.Proposition;
import com.jmc.model.Question;
import com.jmc.model.Subject;
import java.sql.SQLException;
import java.util.List;

public class QuestionService {
    private final SubjectDao subjectDao;
    private final QuestionDao questionDao;
    private final PropositionDao propositionDao;

    public QuestionService(SubjectDao subjectDao, QuestionDao questionDao,PropositionDao propositionDao) {
        this.subjectDao = subjectDao;
        this.questionDao = questionDao;
        this.propositionDao = propositionDao;
    }

    public List<Subject> getAllSubjects() throws SQLException {
        return subjectDao.getAllSubjects();
    }

    public List<Question> getQuestionsForSubject(String subjectName) throws SQLException {
        Subject subject = subjectDao.getSubjectByName(subjectName);
        if (subject != null) {
            List<Question> questions = questionDao.getQuestionsBySubject(subject.getId());

            // Load propositions for each question
            for (Question question : questions) {
                List<Proposition> propositions = propositionDao.getPropositionsByQuestion(question.getId());
                question.setPropositions(propositions);
            }

            return questions;
        }
        return List.of();
    }
}