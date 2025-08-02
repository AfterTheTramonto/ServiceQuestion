package org.skytest.questiontest;

import org.skytest.questiontest.model.Question;
import org.junit.jupiter.api.Test;
import org.skytest.questiontest.service.JavaQuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private final JavaQuestionService service = new JavaQuestionService();

    @Test
    void testAddAndGetAll() {
        Question q1 = service.add("Q1", "A1");
        Question q2 = service.add("Q2", "A2");
        Collection<Question> all = service.getAll();

        assertEquals(2, all.size());
        assertTrue(all.contains(q1));
        assertTrue(all.contains(q2));
    }

    @Test
    void testRemove() {
        Question q = service.add("Q", "A");
        assertNotNull(service.remove(q));
        assertFalse(service.getAll().contains(q));
    }

    @Test
    void testGetRandomQuestion() {
        service.add("Q1", "A1");
        service.add("Q2", "A2");

        Set<Question> results = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            results.add(service.getRandomQuestion());
        }

        assertEquals(2, results.size());
    }

    @Test
    void testUniqueQuestions() {
        service.add("Q", "A");
        service.add("Q", "A");
        assertEquals(1, service.getAll().size());
    }

    @Test
    void testRemoveNonExisting() {
        assertNull(service.remove(new Question("Not exist", "Not exist")));
    }
}