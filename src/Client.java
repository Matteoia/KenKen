import builders.guiBuilders.DefaultGuiBuilder;
import builders.guiBuilders.GuiBuilder;
import directors.GuiDirector;
import graphics.Gui;

public class Client {
    public static void main(String[] args) {
        GuiBuilder builder = new DefaultGuiBuilder();
        GuiDirector director = new GuiDirector();
        director.build(builder);
        Gui app = builder.getResult();
        app.start();
    }
}
