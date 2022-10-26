package commands;

import models.GameBoardModel;

public class StartCreateCommand implements Command {
    private GameBoardModel model;
    public StartCreateCommand(GameBoardModel gameBoardModel) {
        this.model = gameBoardModel;
    }

    @Override
    public void execute() {
        this.model.startBlock();
    }
}
