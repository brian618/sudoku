package com.roastlechon.games.sudoku.view;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.roastlechon.games.sudoku.business.GenerationAlgorithm;
import com.roastlechon.games.sudoku.business.SolutionAlgorithm;
import com.roastlechon.games.sudoku.model.Square;

public class Board extends JPanel {

    private static final long serialVersionUID = 6507971223315054861L;

    /**
     * Constructs a board with 9 zones
     */
    public Board() {
	setLayout(null);
	setPreferredSize(new Dimension(295, 295));
	setBounds(10, 5, 295, 295);

	add(new Zone("1"));
	add(new Zone("2"));
	add(new Zone("3"));
	add(new Zone("4"));
	add(new Zone("5"));
	add(new Zone("6"));
	add(new Zone("7"));
	add(new Zone("8"));
	add(new Zone("9"));
    }

    /**
     * Creates and sets the board with the squares of the provided Sudoku
     * 
     * @param puzzle,
     *            List<List<Square>> containing the Sudoku
     */
    public Board(List<List<Square>> puzzle) {
	setLayout(null);
	setPreferredSize(new Dimension(295, 295));
	setBounds(10, 5, 295, 295);

	for (int i = 0; i < 9; i++) {
	    add(new Zone(String.valueOf(i + 1), puzzle.get(i)));
	}
    }

    /**
     * Check if the board is valid
     */
    public boolean isValid() {
	for (int i = 0; i < this.getComponents().length; i++) {
	    Zone z = (Zone) this.getComponents()[i];
	    for (int j = 0; j < z.getComponents().length; j++) {
		Field f = (Field) z.getComponents()[j];
		if (f.getValue() == null || f.getValue().equals(0)) {
		    return false;
		}
	    }
	}
	return true;
    }

    /**
     * Assertion to check if user's solution is correct
     * 
     * @return true of the algorithm is valid and false otherwise
     */
    public boolean isComplete() {
	SolutionAlgorithm sa = new SolutionAlgorithm(this);
	return sa.valid;
    }

    /**
     * Save the current game
     * 
     * @throws IOException
     */
    public boolean save() throws IOException {
	boolean success = true;
	ObjectOutputStream oos = null;
	try {
	    FileOutputStream saveGame = new FileOutputStream("saveGame.txt");
	    oos = new ObjectOutputStream(saveGame);
	    // grab the zones from the board
	    for (int i = 0; i < this.getComponentCount(); i++) {
		Zone z = (Zone) this.getComponent(i);
		// grab the fields from the zone, grab user input, and save the
		// square from the field
		for (int j = 0; j < z.getComponentCount(); j++) {
		    Field f = (Field) z.getComponent(j);
		    Square square = f.getSquare();
		    if (f.getValue() != null && !f.getValue().equals(0)) {
			String value = (String) f.getValue();
			square.setValue(Integer.valueOf(value));
		    }
		    oos.writeObject(square);
		}
	    }
	    System.out.println("Game saved");
	} finally {
	    oos.flush();
	    oos.close();
	}
	return success;
    }

    /**
     * Loads the most recently saved game
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Board load() throws ClassNotFoundException, IOException {
	ObjectInputStream ois = null;
	GenerationAlgorithm genAlg = new GenerationAlgorithm();
	genAlg.squares = new ArrayList<Square>();
	genAlg.puzzle = new ArrayList<List<Square>>();
	try {
	    FileInputStream savedGame = new FileInputStream("saveGame.txt");
	    ois = new ObjectInputStream(savedGame);
	    this.removeAll();
	    // grab the squares from the save file
	    for (int i = 0; i < 81; i++) {
		Square s = (Square) ois.readObject();
		genAlg.squares.add(s);
	    }
	} finally {
	    if (ois != null) {
		ois.close();
	    }
	}
	return new Board(genAlg.createPuzzle(genAlg.squares));
    }

}