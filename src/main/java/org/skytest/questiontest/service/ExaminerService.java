package org.skytest.questiontest.service;

import org.skytest.questiontest.model.Question;

import java.util.Collection;

public interface ExaminerService {
  Collection<Question> getQuestions(int amount);
}