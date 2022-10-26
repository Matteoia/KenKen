package commands;

import models.GameBoardModel;

public class SelectNumberCommand implements Command{
    private GameBoardModel model;
    private String n;
    public SelectNumberCommand(GameBoardModel gameBoardModel, int i) {
        this.model = gameBoardModel;
        this.n = String.valueOf(i);
    }

    @Override
    public void execute() {
        this.model.setValue(n);
    }
}
