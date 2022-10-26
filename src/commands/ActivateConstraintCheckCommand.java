package commands;

import models.GameBoardModel;

public class ActivateConstraintCheckCommand implements Command{
    private GameBoardModel model;
    public ActivateConstraintCheckCommand(GameBoardModel model) {
        this.model = model;
    }

    @Override
    public void execute(){
        this.model.setMustCheck();
    }

}
