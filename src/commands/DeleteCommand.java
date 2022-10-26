package commands;

import graphics.Gui;
import models.GameBoardModel;

public class DeleteCommand implements Command{
    private GameBoardModel model;

    public DeleteCommand(GameBoardModel model){
        this.model = model;
    }

    @Override
    public void execute() {
        this.model.delete();
    }
}
