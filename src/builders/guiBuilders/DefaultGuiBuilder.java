package builders.guiBuilders;

import commands.*;
import controllers.GameBoardController;
import graphics.Gui;
import graphics.gameBoards.GameBoardView;
import models.GameBoardModel;

import javax.swing.*;
import java.awt.*;

public class DefaultGuiBuilder extends AbstractGuiBuilder {
    public DefaultGuiBuilder(){
        this.gui = new Gui();
    }

    @Override
    public void buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem salva = new JMenuItem("Salva");
        salva.addActionListener(e -> {new SaveCommand(gui.getModel()).execute();});
        file.add(salva);
        JMenuItem carica = new JMenuItem("Carica");
        carica.addActionListener(e -> {new LoadCommand(gui.getModel()).execute();});
        file.add(carica);
        menuBar.add(file);
        gui.setJMenuBar(menuBar);
    }

    @Override
    public void buildGameBoard(){
        gui.setView(new GameBoardView(gui.getSize().height, level));
        gui.setModel(new GameBoardModel(level));
    }

    @Override
    public void buildLevel(){
        String[] levels = {"Facile", "Medio-Facile", "Medio", "Difficile"};
        int level = JOptionPane.showOptionDialog(gui, "Seleziona la difficoltà del gioco", "Selettore Difficoltà", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, levels, 0);
        if(level == -1)
            System.exit(0);
        this.level = level+3;
    }

    @Override
    public void buildGameControlls() {
        gui.setController(new GameBoardController());
    }
}
