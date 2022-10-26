package builders.guiBuilders;

import graphics.Gui;

public interface GuiBuilder {
    void buildLevel();
    public void buildMenuBar();
    public void buildGameBoard();
    public void buildGameControlls();
    public Gui getResult();
}
