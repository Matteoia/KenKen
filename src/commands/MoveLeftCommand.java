package commands;

import models.GameBoardModel;
import utils.Direction;

public class MoveLeftCommand implements Command{
    private GameBoardModel model;
    public MoveLeftCommand(GameBoardModel gameBoardModel) {
        this.model = gameBoardModel;
    }

    @Override
    public void execute() {
        this.model.setpointedCell(Direction.LEFT);
    }
}
