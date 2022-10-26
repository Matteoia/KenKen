package commands;

import graphics.Gui;
import models.GameBoardModel;

public class ShowPrevSolutionCommand implements Command{
    private GameBoardModel model;
    public ShowPrevSolutionCommand(GameBoardModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        this.model.showPrevSolution();
    }
}
