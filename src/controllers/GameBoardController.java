package controllers;


import commands.*;
import models.GameBoardModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameBoardController extends JPanel implements KeyListener {
    private JPanel numberGrid;
    private JPanel movecontrolls;
    private JPanel solveControlls;
    private GameBoardModel model;

    public GameBoardController() {
        this.setLayout(new GridLayout(3, 1));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.white);
        createSolveControlls();
        this.add(solveControlls);
        createNumberGrid();
        this.add(numberGrid);
        createMoveControlls();
        this.add(movecontrolls);
    }

    private void createMoveControlls() {
        this.movecontrolls = new JPanel(new GridLayout(3, 1));
        this.movecontrolls.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        this.movecontrolls.setBackground(Color.white);
        Dimension d = new Dimension(50, 50);
        JPanel tmp = new JPanel();
        tmp.setBackground(Color.white);
        JButton up = new JButton("↑");
        up.addActionListener(e -> {new MoveUpCommand(model).execute();});
        up.setPreferredSize(d);
        tmp.add(up);
        movecontrolls.add(tmp);
        tmp = new JPanel();
        tmp.setBackground(Color.white);
        JButton left = new JButton("←");
        left.addActionListener(e-> {new MoveLeftCommand(model).execute();});
        left.setPreferredSize(d);
        tmp.add(left);
        JButton ok = new JButton("o");
        ok.addActionListener(e->{new StartCreateCommand(model).execute(); new SelectCellCommand(model).execute(); });
        ok.setPreferredSize(d);
        tmp.add(ok);
        JButton right = new JButton("→");
        right.addActionListener(e->{new MoveRightCommand(model).execute();});
        right.setPreferredSize(d);
        tmp.add(right);
        movecontrolls.add(tmp);
        tmp = new JPanel();
        tmp.setBackground(Color.white);
        JButton down = new JButton("↓");
        down.addActionListener(e->{new MoveDownCommand(model).execute();});
        down.setPreferredSize(d);
        tmp.add(down);
        movecontrolls.add(tmp);
    }

    private void createNumberGrid() {
        JPanel tmp;
        this.numberGrid = new JPanel(new GridLayout(3, 2));
        this.numberGrid.setBackground(Color.white);
        this.numberGrid.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        for(int i=0; i<6; i++){
            tmp = new JPanel();
            tmp.setBackground(Color.white);
            JButton button = new JButton(String.valueOf(i+1));
            int finalI = i;
            button.addActionListener(e -> {new SelectNumberCommand(model, finalI+1).execute();});
            button.setPreferredSize(new Dimension(60, 60));
            tmp.add(button);
            numberGrid.add(tmp);
        }
    }

    private void createSolveControlls() {
        this.solveControlls = new JPanel();
        this.solveControlls.setLayout(new GridLayout(4, 1));
        this.solveControlls.setBackground(Color.white);
        JPanel tmp = new JPanel();
        tmp.setBackground(Color.white);
        JCheckBox checkConstraint = new JCheckBox("Check Constraint");
        checkConstraint.addActionListener(e->{new ActivateConstraintCheckCommand(model).execute();});
        checkConstraint.setBackground(Color.white);
        tmp.add((checkConstraint));
        solveControlls.add(tmp);
        tmp = new JPanel();
        tmp.setBackground(Color.white);
        JButton solve = new JButton("solve");
        solve.addActionListener(e->{new SolveCommand(model).execute();});
        solve.setPreferredSize(new Dimension(150, 40));
        tmp.add(solve);
        solveControlls.add(tmp);
        tmp = new JPanel();
        tmp.setBackground(Color.white);
        JButton back = new JButton("←");
        back.addActionListener(e ->{new ShowPrevSolutionCommand(model).execute();});
        back.setPreferredSize(new Dimension(70, 40));
        tmp.add(back);
        JButton next = new JButton("→");
        next.addActionListener(e->{new ShowNextSolutionCommand(model).execute();});
        next.setPreferredSize(new Dimension(70, 40));
        tmp.add(next);
        solveControlls.add(tmp);
        tmp = new JPanel();
        tmp.setBackground(Color.white);
        JButton startCreation = new JButton("sc");
        startCreation.addActionListener(e->{new StartCreateCommand(model).execute();});
        tmp.add(startCreation);
        JButton endCreation = new JButton("ec");
        endCreation.addActionListener(e->{new EndCreateCommand(model).execute();});
        tmp.add(endCreation);
        JButton delete = new JButton("d");
        delete.addActionListener(e->{new DeleteCommand(model).execute();});
        tmp.add(delete);
        solveControlls.add(tmp);
    }

    public void setModel(GameBoardModel gameBoardModel) {
        this.model = gameBoardModel;
    }


    public boolean dispatchKeyEvent(KeyEvent e) {
       if(e.getID() == KeyEvent.KEY_PRESSED){
           switch(e.getKeyCode()){
               case 10 -> {
                   new StartCreateCommand(model).execute();
                   new SelectCellCommand(model).execute();
               }
               case 27 -> {
                   new EndCreateCommand(model).execute();
               }
               case 127 -> {
                   new DeleteCommand(model).execute();
               }
               case 37 -> {
                   new MoveLeftCommand(model).execute();
               }
               case 38 -> {
                   new MoveUpCommand(model).execute();
               }
               case 39 -> {
                   new MoveRightCommand(model).execute();
               }
               case 40 -> {
                   new MoveDownCommand(model).execute();
               }
               default -> {
                   if(e.getKeyCode() >= 49 && e.getKeyCode() <= 57)
                      new SelectNumberCommand(model, e.getKeyCode()-48).execute();
               }
           }
       }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        dispatchKeyEvent(e);
    }
    @Override
    public void keyReleased(KeyEvent e) { }
}
