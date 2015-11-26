package com.roastlechon.games.sudoku;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
    final JLabel timerLabel = new JLabel("Timer");
    JPanel panel = new JPanel();
    Board board = new Board();
    int delay = 1000;
    int time = 1;

    final JButton easyButton = new JButton("E");
    final JButton mediumButton = new JButton("M");
    final JButton hardButton = new JButton("H");
    final JLabel lastTime = new JLabel(" ");

    final JFrame messageDialog = new JFrame();
    
    final int HARD = 2;
    final int MEDIUM = 1;
    final int EASY = 0;

    Timer timer;

    /**
     * Constructor for Sudoku
     */
    public Sudoku() {
    
    	readFile();
    lastTime.setLayout(null);
    lastTime.setBounds(10,200,80,20);
    
	timerLabel.setLayout(null);
	timerLabel.setBounds(10, 225, 40, 20);

	timerField.setHorizontalAlignment(JFormattedTextField.CENTER);
	timerField.setLayout(null);
	timerField.setEnabled(true);
	timerField.setEditable(false);
	timerField.setBounds(45, 225, 50, 20);
	timerField.setValue("0");
	timerField.setForeground(Color.BLACK);

	timer = new Timer(delay, new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		timerField.setValue(time++);
	    }
	});

	easyButton.setLayout(null);
	easyButton.setBounds(100, 200, 30, 20);
	easyButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		finishButton.setEnabled(true);
		readFile();
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
	mediumButton.setBounds(130, 200, 30, 20);
	mediumButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		finishButton.setEnabled(true);
		readFile();
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
	hardButton.setBounds(160, 200, 30, 20);
	hardButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		finishButton.setEnabled(true);
		readFile();
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
	finishButton.setBounds(100, 225, 90, 20);
	finishButton.setEnabled(false);
	finishButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (board.isValid()) {
		    if (board.isComplete()) {
			finishButton.setEnabled(false);
			timer.stop();
			JOptionPane.showMessageDialog(messageDialog, "Congratulations, you finished the puzzle in "
				+ timerField.getValue() + " seconds. Click Generate to play again.");
		    //save into scores.txt
			try(PrintWriter output = new PrintWriter(new FileWriter("scores.txt",true))) 
			{
			    output.printf("%s\r\n", timerField.getValue());
			} 
			catch (Exception e1) {}
		    } 
		    else {
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
	panel.add(board);
	// panel.add(generateButton);
	panel.add(mediumButton);
	panel.add(hardButton);
	panel.add(easyButton);
	panel.add(finishButton);
	panel.add(lastTime);

	this.add(panel);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setSize(204, 285);
	this.setLocationRelativeTo(null);
	this.setTitle("Sudoku");
	this.setResizable(false);
	this.setVisible(true);
    }

    public static void main(String[] args) {
	new Sudoku();
    }
    
    /**
     * Read scores.txt to get the time from the last game played.
     */
    private void readFile(){
        String last_time=null;
        try{
	        FileReader fileReader = new FileReader("scores.txt");
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        
		    String line = bufferedReader.readLine();
		    while(line !=null) {
		    	if(line.length() > 0){
		    		last_time = line;
		    	}
		    	line = bufferedReader.readLine();
		    } 
		    bufferedReader.close(); 
		    lastTime.setText("last t: " + last_time+"s");
        }
	    catch(FileNotFoundException ex) {
	        System.out.println(
	            "Unable to open file");                
	    }
	    catch(IOException ex) {
	        System.out.println(
	            "Error reading file ");                  
	    }
    	
    }
}
