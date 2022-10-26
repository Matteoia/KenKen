package commands;

import models.GameBoardModel;
import utils.Direction;

public class MoveDownCommand implements Command{
    private GameBoardModel model;
    public MoveDownCommand(GameBoardModel gameBoardModel) {
        this.model = gameBoardModel;
    }

    @Override
    public void execute() {
        this.model.setpointedCell(Direction.DOWN);
    }
}
