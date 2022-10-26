package commands;

import models.GameBoardModel;
import utils.Direction;

public class MoveUpCommand implements Command{
    private GameBoardModel model;
    public MoveUpCommand(GameBoardModel gameBoardModel) {
        this.model = gameBoardModel;
    }

    @Override
    public void execute() {
        this.model.setpointedCell(Direction.UP);
    }
}
