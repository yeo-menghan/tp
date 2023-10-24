package quizhub.command;

import quizhub.questionlist.QuestionList;
import quizhub.storage.Storage;
import quizhub.ui.Ui;

public class CommandShuffle extends Command {
    public CommandShuffle() {
        super(CommandType.SHUFFLE);
    }
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        questions.shuffleQuestions();
    }
}