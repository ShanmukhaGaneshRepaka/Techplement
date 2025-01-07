package com;
import java.util.*;
public class QuizService {
	
	
	  private Map<String, Quiz> quizzes;

	    public QuizService() {
	        quizzes = new HashMap<>();
	    }

	    public boolean createQuiz(String title) {
	        if (quizzes.containsKey(title)) {
	            return false;
	        }
	        quizzes.put(title, new Quiz(title));
	        return true;
	    }

	    public Quiz getQuiz(String title) {
	        return quizzes.get(title);
	    }

	    public Map<String, Quiz> getAllQuizzes() {
	        return quizzes;
	    }
}
