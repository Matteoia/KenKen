package commands;


import graphics.Gui;
import models.GameBoardModel;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveCommand implements Command{
    private GameBoardModel model;

    public SaveCommand(GameBoardModel model){
        this.model = model;
    }

    @Override
    public void execute() {

       // try{
       //     JFileChooser fileChooser = new JFileChooser();
       //     fileChooser.showSaveDialog(gui);
       //     ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile()));
       //     //outputStream.writeObject(gui.getController().getModel());
       //     outputStream.close();
       // }catch(IOException e){
       //     e.printStackTrace();
       // }
    }
}
