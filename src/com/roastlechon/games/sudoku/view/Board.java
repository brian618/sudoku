package com.roastlechon.games.sudoku.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import com.roastlechon.games.sudoku.business.SolutionAlgorithm;
import com.roastlechon.games.sudoku.model.Square;

public class Board extends JPanel {

    private static final long serialVersionUID = 6507971223315054861L;

    /**
     * Constructs a board with 9 zones
     */
    public Board() {
	setLayout(null);
	setPreferredSize(new Dimension(200, 200));
	setBounds(0, 0, 200, 200);

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
	setPreferredSize(new Dimension(200, 200));
	setBounds(0, 0, 200, 200);

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
     * Assertion to see if SolutionAlgorithm is valid
     * 
     * @return true of the algorithm is valid and false otherwise
     */
    public boolean isComplete() {
	SolutionAlgorithm sa = new SolutionAlgorithm(this);
	return sa.valid;
    }

}