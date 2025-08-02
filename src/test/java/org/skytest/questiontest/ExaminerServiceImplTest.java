package org.skytest.questiontest;

import org.skytest.questiontest.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skytest.questiontest.service.ExaminerServiceImpl;
import org.skytest.questiontest.service.QuestionService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void testGetQuestions() {

        Question q1 = new Question("Q1", "A1");
        Question q2 = new Question("Q2", "A2");
        Question q3 = new Question("Q3", "A3");

        when(questionService.getAll()).thenReturn(Arrays.asList(q1, q2, q3));
        when(questionService.getRandomQuestion())
                .thenReturn(q1)
                .thenReturn(q2)
                .thenReturn(q3);

        Collection<Question> result = examinerService.getQuestions(2);
        assertEquals(2, result.size());
        assertTrue(result.contains(q1));
        assertTrue(result.contains(q2));


        verify(questionService, times(2)).getRandomQuestion();
    }

    @Test
    void testNotEnoughQuestions() {
        when(questionService.getAll()).thenReturn(Collections.singletonList(
                new Question("Q", "A")
        ));

        assertThrows(IllegalArgumentException.class,
                () -> examinerService.getQuestions(2)
        );
    }

    @Test
    void testUniqueQuestionsInResult() {
        Question q = new Question("Q", "A");
        when(questionService.getAll()).thenReturn(Collections.singletonList(q));
        when(questionService.getRandomQuestion()).thenReturn(q);

        Collection<Question> result = examinerService.getQuestions(1);
        assertEquals(1, result.size());
        assertTrue(result.contains(q));
    }
}