package builders.guiBuilders;

import controllers.GameBoardController;
import graphics.Gui;
import graphics.gameBoards.GameBoardView;
import models.GameBoardModel;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractGuiBuilder implements GuiBuilder{
    protected Gui gui;
    protected int level;
    public AbstractGuiBuilder(){
        this.gui = new Gui();
    }

    @Override
    public Gui getResult(){
        return gui;
    }
}
