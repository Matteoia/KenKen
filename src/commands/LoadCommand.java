package commands;

import graphics.Gui;
import models.GameBoardModel;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LoadCommand implements Command{
    private GameBoardModel model;

    public LoadCommand(GameBoardModel model){
        this.model = model;
    }

    @Override
    public void execute() {
       //try {
       //    JFileChooser fileChooser = new JFileChooser();
       //    fileChooser.showOpenDialog(gui);
       //    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()));
       //    GameBoardModel model = (GameBoardModel) inputStream.readObject();
       //    //gui.getController().setModel(model);
       //} catch (Exception e) {
       //    JOptionPane.showMessageDialog(gui, e.getMessage());
       //}
    }
}
