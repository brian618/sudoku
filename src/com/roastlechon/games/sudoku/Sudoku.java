package com.roastlechon.games.sudoku;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    final JLabel timerLabel = new JLabel("Time Elapsed");
    JPanel panel = new JPanel();
    Board board = new Board();
    int delay = 1000;
    int time = 1;

    final JButton easyButton = new JButton("Easy");
    final JButton mediumButton = new JButton("Medium");
    final JButton hardButton = new JButton("Hard");
    final JLabel lastTime = new JLabel(" ");

    final JFrame messageDialog = new JFrame();

    final int HARD = 2;
    final int MEDIUM = 1;
    final int EASY = 0;

    Timer timer;

    /**
     * Constructor for Sudoku UI
     */
    public Sudoku() {

    panel.setLayout(null);
    initizalizeTimeLabel();
    initizalizeTimer();
	getPreviousTime();
	initizalizeEasyButton();
	initizalizeMediumButton();
	initizalizeHardButton();
	initizalizeFinishButton();

	board.setLocation(10, 5);
	board.setSize(475, 475);
	panel.add(board);

	getContentPane().add(panel);

	initizalizeSaveButton();
	initizalizeLoadButton();
	
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setSize(488,630);
	//this.setSize(320, 384);
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
    private void readFile() {
	String last_time = null;
	try {
	    FileReader fileReader = new FileReader("scores.txt");
	    BufferedReader bufferedReader = new BufferedReader(fileReader);

	    String line = bufferedReader.readLine();
	    while (line != null) {
		if (line.length() > 0) {
		    last_time = line;
		}
		line = bufferedReader.readLine();
	    }
	    bufferedReader.close();
	    lastTime.setText("Previous time: " + last_time + "s");
	} catch (FileNotFoundException ex) {
	    JOptionPane.showMessageDialog(messageDialog, "Sorry, previous time file not found");
	} catch (IOException ex) {
	    JOptionPane.showMessageDialog(messageDialog, "Sorry, could not open previous time file");
	}
    }

    /**
     * Loads the time of the previous save
     * 
     * @return the time loaded from the file
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private int loadTime() throws IOException, ClassNotFoundException {
	ObjectInputStream ois = null;
	int time = 0;
	try {
	    FileInputStream savedGame = new FileInputStream("saveTime.txt");
	    ois = new ObjectInputStream(savedGame);
	    time = (int) ois.readObject();
	} finally {
	    if (ois != null) {
		ois.close();
	    }
	}
	return time;
    }

    /**
     * Loads the time of the previous save
     * 
     * @return the time loaded from the file
     * @throws IOException
     */
    private void saveTime() throws IOException {
	ObjectOutputStream oos = null;
	try {
	    FileOutputStream saveTime = new FileOutputStream("saveTime.txt");
	    oos = new ObjectOutputStream(saveTime);

	    oos.writeObject(time);
	} finally {
	    if (oos != null) {
		oos.flush();
		oos.close();
	    }
	}
    }
    
    private void initizalizeEasyButton(){
    	easyButton.setLayout(null);
    	easyButton.setBounds(20, 500, 90, 40);
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
    	panel.add(easyButton);
    	
    }
    private void initizalizeMediumButton(){
    	mediumButton.setLayout(null);
    	mediumButton.setBounds(140, 500, 90, 40);
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
    	panel.add(mediumButton);
    }
    private void initizalizeHardButton(){
    	hardButton.setLayout(null);
    	hardButton.setBounds(260, 500, 90, 40);
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
    	panel.add(hardButton);
    }
    private void getPreviousTime(){
    	readFile();
    	lastTime.setLayout(null);
    	lastTime.setBounds(260, 570, 150, 20);
    	panel.add(lastTime);
    }
    private void initizalizeTimeLabel(){
    	timerLabel.setLayout(null);
    	timerLabel.setBounds(260, 550, 85, 20);	
    	panel.add(timerLabel);
    }
    private void initizalizeSaveButton(){
    	JButton btnSave = new JButton("Save");
    	btnSave.setBounds(140, 550, 90, 40);
    	panel.add(btnSave);
    	btnSave.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    		try {
    		    board.save();
    		    saveTime();
    		    JOptionPane.showMessageDialog(messageDialog, "Saved Successfully");
    		} catch (IOException e1) {
    		    JOptionPane.showMessageDialog(messageDialog, "Sorry, cannot create save file");
    		}
    	    }
    	});
    }
    private void initizalizeLoadButton(){
    	JButton btnLoad = new JButton("Load");
    	btnLoad.setBounds(20, 550, 90, 40);
    	panel.add(btnLoad);
    	btnLoad.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    		panel.remove(board);
    		try {
    		    board = board.load();
    		    time = loadTime();
    		} catch (ClassNotFoundException e1) {
    		    JOptionPane.showMessageDialog(messageDialog, "Sorry, the save file has been corrupted");
    		} catch (IOException e1) {
    		    JOptionPane.showMessageDialog(messageDialog, "Sorry, no save file exists");
    		}
    		if (board != null) {
    		    finishButton.setEnabled(true);
    		    panel.add(board);
    		    panel.revalidate();
    		    panel.repaint();
    		    timer.restart();
    		    transferFocus();
    		} else {
    		    JOptionPane.showMessageDialog(messageDialog, "Sorry, could not open save file");
    		}
    	    }
    	});	
    }
    
    private void initizalizeTimer(){
    	timerField.setHorizontalAlignment(JFormattedTextField.CENTER);
    	timerField.setLayout(null);
    	timerField.setEnabled(true);
    	timerField.setEditable(false);
    	timerField.setBounds(350, 550, 59, 20);
    	timerField.setValue("0");
    	timerField.setForeground(Color.BLACK);
    	panel.add(timerField);


    	timer = new Timer(delay, new ActionListener() {
    	    public void actionPerformed(ActionEvent evt) {
    		timerField.setValue(time++);
    	    }
    	});	
    }
    
    private void initizalizeFinishButton(){
    	finishButton.setLayout(null);
    	finishButton.setBounds(380, 500, 90, 40);
    	finishButton.setEnabled(false);
    	finishButton.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    		if (board.isValid()) {
    		    if (board.isComplete()) {
    			finishButton.setEnabled(false);
    			timer.stop();
    			JOptionPane.showMessageDialog(messageDialog, "Congratulations, you finished the puzzle in "
    				+ timerField.getValue() + " seconds. Click level to play again.");
    			// save into scores.txt
    			try (PrintWriter output = new PrintWriter(new FileWriter("scores.txt", true))) {
    			    output.printf("%s\r\n", timerField.getValue());
    			} catch (Exception e1) {
    			}
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
       	panel.add(finishButton);
    }
}
