package com;
import java.util.*;
public class Quiz {
	 private String title;
	    private List<Question> questions;

	    public Quiz(String title) {
	        this.title = title;
	        this.questions = new ArrayList<>();
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void addQuestion(Question question) {
	        questions.add(question);
	    }

	    public List<Question> getQuestions() {
	        return questions;
	    }

}
