package commands;

import graphics.Gui;
import models.GameBoardModel;

import javax.swing.*;

public class SolveCommand implements Command{
    private GameBoardModel model;

    public SolveCommand(GameBoardModel model){
        this.model = model;
    }

    @Override
    public void execute() {
        try{
            int totSoluzioni = Integer.parseInt(JOptionPane.showInputDialog("Inserisci il numero massimo di soluzioni", 1));
            if (totSoluzioni > 0) {
                this.model.solve(totSoluzioni);
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Input non valido");
        }
    }
}
