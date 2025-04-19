package com.jmc.dao;

import com.jmc.model.Proposition;
import java.sql.SQLException;
import java.util.List;

public interface PropositionDao {
    void addProposition(Proposition proposition) throws SQLException;
    void addPropositions(List<Proposition> propositions) throws SQLException;
    List<Proposition> getPropositionsByQuestion(int questionId) throws SQLException;
}