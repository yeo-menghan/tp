package quizhub.ui;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.question.Question;

import java.util.Scanner;
/**
 * Represents the user interface for CLI displays.
 * Supports CLI interactions with users.
 */
public class Ui {
    private final Scanner input = new Scanner(System.in);  // Create a Scanner object
    private Storage dataStorage;
    private QuestionList tasks;
    /**
     * Sets up the bridging between the UI and tasks data.
     *
     * @param tasks A record of all tasks documented that is built on program start and disposed on program exit.
     * @param dataStorage The hard disk record of all tasks documented that persists even on program exit.
     */
    public Ui(Storage dataStorage, QuestionList tasks){
        this.dataStorage = dataStorage;
        this.tasks = tasks;
    }
    /**
     * Print out separating line in CLI to mark
     * start and end of chatbot replies.
     */
    public void showLine(){
        System.out.println("    ____________________________________________________________\n");
    }
    /**
     * Displays app logo and opening message to welcome users
     * on the launch of chatbot.
     */
    public void displayOpeningMessage(){
        String logo =  "    _______          _________ _______                    ______  \n" +
                    "   (  ___  )|\\     /|\\__   __// ___   )|\\     /||\\     /|(  ___ \\ \n" +
                    "   | (   ) || )   ( |   ) (   \\/   )  || )   ( || )   ( || (   ) )\n" +
                    "   | |   | || |   | |   | |       /   )| (___) || |   | || (__/ / \n" +
                    "   | |   | || |   | |   | |      /   / |  ___  || |   | ||  __ (  \n" +
                    "   | | /\\| || |   | |   | |     /   /  | (   ) || |   | || (  \\ \\ \n" +
                    "   | (_\\ \\ || (___) |___) (___ /   (_/\\| )   ( || (___) || )___) )\n" +
                    "   (____\\/_)(_______)\\_______/(_______/|/     \\|(_______)|/ \\___/";
        System.out.println(logo);
        showLine();
        System.out.println("    Welcome to Quizhub!!!\n");
        System.out.println("    Let the quizzing begin XDD");
        System.out.println();
        dataStorage.loadData(tasks);
        assert tasks != null : "Invalid null tasks";
        showLine();
    }
    /**
     * Retrieves the CLI input from the user
     * and documents it as a String object.
     */
    public String getUserInput() {
        if(input.hasNextLine()){
            return input.nextLine();
        } else {
            return "";
        }

    }
    /**
     * Displays closing message on exiting the chatbot.
     */
    public void displayClosingMessage(){
        dataStorage.updateData(tasks);
        System.out.println("    Are you sure you want to stop quizzing?");
        System.out.println("    Well... hope you had fun quizzing :D");
        System.out.println("    See you again soon!");
        showLine();
    }
    /**
     * Displays a question along with its index in a set of questions and the total count of questions.
     * Extracts and displays the question part from the question description, which is in the "question/answer" format.
     * If the format is invalid or missing, it prompts the user to edit the question via the edit function.
     *
     * @param question           The Question object containing the question description.
     * @param currentQuestionIndex The index of the current question in the set of questions.
     * @param totalQuestions     The total count of questions in the set.
     */
    public void displayQuestion(Question question, int currentQuestionIndex, int totalQuestions) {
        showLine();
        System.out.println("    Question " + currentQuestionIndex + " / " + totalQuestions + ":");
        // getQuestionDescription returns question/answer
        String questionDescription = question.getQuestionDescription();
        String[] parts = questionDescription.split("/");

        if (parts.length >= 1) {
            // Extract and display the question part
            System.out.println("    " + parts[0]); // part[0] returns the question part
        } else {
            // Handle the case where the format is invalid
            System.out.println("    Invalid question format, please edit this question via the edit function");
        }

        System.out.print("  Your Answer: ");
    }

    public void showInvalidCommandHelp( String feedback ) {
        System.out.println("    Please enter a valid command :0");
        System.out.println(feedback);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}

