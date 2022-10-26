package graphics;

import controllers.GameBoardController;
import models.GameBoardModel;
import graphics.gameBoards.GameBoardView;
import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    private final int height = 700;
    private final int width = 850;
    private GameBoardController controller;
    private GameBoardView view;
    private GameBoardModel model;

    public Gui(){
        super();
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(width, height));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void start(){
        setFocusable(true);
        this.setVisible(true);
        while (true) {
            this.requestFocusInWindow();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
        }
    }


    public void setView(GameBoardView gameBoardView) {
        this.view = gameBoardView;
        this.add(view, BorderLayout.CENTER);
    }

    public void setModel(GameBoardModel gameBoardModel){
        this.model = gameBoardModel;
        this.model.setView(view);
    }

    public void setController(GameBoardController gameBoardController){
        this.controller = gameBoardController;
        this.controller.setModel(model);
        this.add(controller, BorderLayout.EAST);
        this.addKeyListener(controller);
    }

    public GameBoardModel getModel() {
        return model;
    }
}
