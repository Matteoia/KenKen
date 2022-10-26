package commands;

import models.GameBoardModel;

public class EndCreateCommand implements Command {
    private GameBoardModel model;
    public EndCreateCommand(GameBoardModel gameBoardModel) {
        this.model = gameBoardModel;
    }

    @Override
    public void execute() {
        this.model.endCreate();
    }
}
