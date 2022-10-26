package directors;

import builders.guiBuilders.GuiBuilder;

public class GuiDirector {
    public void build(GuiBuilder builder){
        builder.buildLevel();
        builder.buildGameBoard();
        builder.buildGameControlls();
        builder.buildMenuBar();
    }
}
