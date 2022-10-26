package commands;

import graphics.Gui;
import models.GameBoardModel;

public class ShowNextSolutionCommand implements Command{
    private GameBoardModel model;

    public ShowNextSolutionCommand(GameBoardModel model){
        this.model = model;
    }
    @Override
    public void execute() {
        model.showNextSolution();
    }
}
