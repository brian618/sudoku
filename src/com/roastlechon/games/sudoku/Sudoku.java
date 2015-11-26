package com.roastlechon.games.sudoku;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.roastlechon.games.sudoku.business.GenerationAlgorithm;
import com.roastlechon.games.sudoku.view.Board;

public class Sudoku extends JFrame {

    private static final long serialVersionUID = 9179949591758990253L;
    final JButton generateButton = new JButton("Generate");
    final JButton finishButton = new JButton("Finish");
    JFormattedTextField timerField = new JFormattedTextField();
    final JLabel timerLabel = new JLabel("Time Elapsed");
    JPanel panel = new JPanel();
    Board board = new Board();
    int delay = 1000;
    int time = 1;

    final JButton easyButton = new JButton("E");
    final JButton mediumButton = new JButton("M");
    final JButton hardButton = new JButton("H");

    final JFrame messageDialog = new JFrame();

    final int HARD = 2;
    final int MEDIUM = 1;
    final int EASY = 0;

    Timer timer;

    /**
     * Constructor for Sudoku UI
     */
    public Sudoku() {
	timerLabel.setLayout(null);
	timerLabel.setBounds(7, 305, 75, 20);

	timerField.setHorizontalAlignment(JFormattedTextField.CENTER);
	timerField.setLayout(null);
	timerField.setEnabled(true);
	timerField.setEditable(false);
	timerField.setBounds(10, 330, 59, 20);
	timerField.setValue("0");
	timerField.setForeground(Color.BLACK);

	timer = new Timer(delay, new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		timerField.setValue(time++);
	    }
	});

	easyButton.setLayout(null);
	easyButton.setBounds(165, 305, 45, 20);
	easyButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		finishButton.setEnabled(true);
		panel.remove(board);
		board = new Board(new GenerationAlgorithm(EASY).puzzle);
		panel.add(board);
		panel.revalidate();
		panel.repaint();
		timerField.setValue("0");
		time = 1;
		timer.restart();
		transferFocus();
	    }
	});

	mediumButton.setLayout(null);
	mediumButton.setBounds(211, 305, 45, 20);
	mediumButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		finishButton.setEnabled(true);
		panel.remove(board);
		board = new Board(new GenerationAlgorithm(MEDIUM).puzzle);
		panel.add(board);
		panel.revalidate();
		panel.repaint();
		timerField.setValue("0");
		time = 1;
		timer.restart();
		transferFocus();
	    }
	});
	hardButton.setLayout(null);
	hardButton.setBounds(257, 305, 45, 20);
	hardButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		finishButton.setEnabled(true);
		panel.remove(board);
		board = new Board(new GenerationAlgorithm(HARD).puzzle);
		panel.add(board);
		panel.revalidate();
		panel.repaint();
		timerField.setValue("0");
		time = 1;
		timer.restart();
		transferFocus();
	    }
	});

	/*
	 * generateButton.setLayout(null); generateButton.setBounds(100, 200,
	 * 45, 20); generateButton.addActionListener(new ActionListener() {
	 * public void actionPerformed(ActionEvent e) {
	 * finishButton.setEnabled(true); panel.remove(board); board = new
	 * Board(new GenerationAlgorithm().puzzle); panel.add(board);
	 * panel.revalidate(); panel.repaint(); timerField.setValue("0"); time =
	 * 1; timer.restart(); transferFocus(); } });
	 */

	finishButton.setLayout(null);
	finishButton.setBounds(165, 330, 136, 20);
	finishButton.setEnabled(false);
	finishButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (board.isValid()) {
		    if (board.isComplete()) {
			finishButton.setEnabled(false);
			timer.stop();
			JOptionPane.showMessageDialog(messageDialog, "Congratulations, you finished the puzzle in "
				+ timerField.getValue() + " seconds. Click Generate to play again.");
		    } else {
			JOptionPane.showMessageDialog(messageDialog,
				"The sudoku puzzle is not complete. Please check the puzzle and click Finish.");
		    }
		} else {
		    JOptionPane.showMessageDialog(messageDialog,
			    "The sudoku puzzle is not complete. Please check the puzzle and click Finish.");
		}
	    }
	});

	panel.setLayout(null);
	panel.add(timerLabel);
	panel.add(timerField);
	board.setLocation(10, 5);
	board.setSize(295, 295);
	panel.add(board);
	// panel.add(generateButton);
	panel.add(mediumButton);
	panel.add(hardButton);
	panel.add(easyButton);
	panel.add(finishButton);

	getContentPane().add(panel);

	JButton btnSave = new JButton("Save");
	btnSave.setBounds(88, 305, 75, 20);
	panel.add(btnSave);

	JButton btnLoad = new JButton("Load");
	btnLoad.setBounds(88, 330, 75, 20);
	panel.add(btnLoad);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setSize(320, 384);
	this.setLocationRelativeTo(null);
	this.setTitle("Sudoku");
	this.setResizable(false);
	this.setVisible(true);
    }

    public static void main(String[] args) {
	new Sudoku();
    }
}
