package com;
import java.util.*;
public class QuizApplication {
	 private final QuizService quizService;
	    private final Scanner scanner;

	    public QuizApplication(QuizService quizService) {
	        this.quizService = quizService;
	        this.scanner = new Scanner(System.in);
	    }

	    public void run() {
	        while (true) {
	            System.out.println("\n--- Quiz Application ---");
	            System.out.println("1. Create a new quiz");
	            System.out.println("2. Add questions to a quiz");
	            System.out.println("3. Take a quiz");
	            System.out.println("4. Exit");
	            System.out.print("Choose an option: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                    createQuiz();
	                    break;
	                case 2:
	                    addQuestionsToQuiz();
	                    break;
	                case 3:
	                    takeQuiz();
	                    break;
	                case 4:
	                    System.out.println("Exiting application. Goodbye!");
	                    return;
	                default:
	                    System.out.println("Invalid option. Please try again.");
	            }
	        }
	    }

	    private void createQuiz() {
	        System.out.print("Enter the title of the new quiz: ");
	        String title = scanner.nextLine();
	        if (quizService.createQuiz(title)) {
	            System.out.println("Quiz created successfully.");
	            System.out.print("Do you want to add a question to the quiz now? (yes/no): ");
	            String response = scanner.nextLine().trim().toLowerCase();
	            if (response.equals("yes")) {
	                addQuestionsToQuiz(title);
	            }
	        } else {
	            System.out.println("A quiz with this title already exists.");
	        }
	    }

	    private void addQuestionsToQuiz() {
	        System.out.print("Enter the title of the quiz: ");
	        String title = scanner.nextLine();
	        addQuestionsToQuiz(title);
	    }

	    private void addQuestionsToQuiz(String title) {
	        Quiz quiz = quizService.getQuiz(title);

	        if (quiz == null) {
	            System.out.println("Quiz not found.");
	            return;
	        }

	        System.out.print("Enter the question text: ");
	        String questionText = scanner.nextLine();

	        List<String> options = new ArrayList<>();
	        for (int i = 1; i <= 4; i++) {
	            System.out.print("Enter option " + i + ": ");
	            options.add(scanner.nextLine());
	        }

	        System.out.print("Enter the number of the correct option (1-4): ");
	        int correctAnswerIndex = scanner.nextInt() - 1;
	        scanner.nextLine(); // Consume newline

	        if (correctAnswerIndex < 0 || correctAnswerIndex >= options.size()) {
	            System.out.println("Invalid correct answer index.");
	            return;
	        }

	        quiz.addQuestion(new Question(questionText, options, correctAnswerIndex));
	        System.out.println("Question added successfully.");
	    }

	    private void takeQuiz() {
	        System.out.print("Enter the title of the quiz you want to take: ");
	        String title = scanner.nextLine();
	        Quiz quiz = quizService.getQuiz(title);

	        if (quiz == null) {
	            System.out.println("Quiz not found.");
	            return;
	        }

	        int score = 0;
	        List<Question> questions = quiz.getQuestions();
	        if (questions.isEmpty()) {
	            System.out.println("This quiz has no questions.");
	            return;
	        }

	        for (Question question : questions) {
	            System.out.println("\n" + question.getQuestionText());
	            List<String> options = question.getOptions();
	            for (int i = 0; i < options.size(); i++) {
	                System.out.println((i + 1) + ". " + options.get(i));
	            }

	            System.out.print("Your answer (1-4): ");
	            int answer = scanner.nextInt() - 1;

	            if (answer == question.getCorrectAnswerIndex()) {
	                System.out.println("Correct!");
	                score++;
	            } else {
	                System.out.println("Wrong! The correct answer was: " + 
	                                   options.get(question.getCorrectAnswerIndex()));
	            }
	        }

	        System.out.println("\nYou scored " + score + " out of " + questions.size() + ".");
	        System.out.println("Grade: " + calculateGrade(score, questions.size()));
	    }

	    private String calculateGrade(int score, int total) {
	        double percentage = ((double) score / total) * 100;

	        if (percentage >= 90) {
	            return "A";
	        } else if (percentage >= 80) {
	            return "B";
	        } else if (percentage >= 70) {
	            return "C";
	        } else if (percentage >= 60) {
	            return "D";
	        } else {
	            return "F";
	        }
	    }

	    public static void main(String[] args) {
	        QuizService quizService = new QuizService();
	        QuizApplication app = new QuizApplication(quizService);
	        app.run();
	    }
}
