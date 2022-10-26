package commands;

import models.GameBoardModel;

public class SelectCellCommand implements Command{
    private GameBoardModel model;
    public SelectCellCommand(GameBoardModel gameBoardModel) {
        this.model = gameBoardModel;
    }

    @Override
    public void execute() {
        this.model.setConstraint();
    }
}
