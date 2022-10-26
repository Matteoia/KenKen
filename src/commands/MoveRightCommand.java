package commands;

import models.GameBoardModel;
import utils.Direction;

public class MoveRightCommand implements Command {
    private GameBoardModel model;
    public MoveRightCommand(GameBoardModel gameBoardModel) {
        this.model = gameBoardModel;
    }

    @Override
    public void execute() {
        this.model.setpointedCell(Direction.RIGHT);
    }
}
