package com.roastlechon.games.sudoku.model;

import java.io.Serializable;

public class Square implements Serializable {

    
    /**
     * Serialization of the square for saving
     */
    private static final long serialVersionUID = -1699695250417443410L;
    public int row;
    public int col;
    public int zone;
    public int value;
    public int index;

    /**
     * Constructor for square
     */
    public Square() {

    }

    /**
     * Constructor for Square
     * 
     * @param index,
     *            the index of the square in the list of all squares
     * @param value,
     *            the value of the square
     */
    public Square(int index, int value) {
	int n = index + 1;
	this.index = index;
	this.value = value;
	row = getRow(n);
	col = getCol(n);
	zone = getZone(n);
    }

    /**
     * @param index,
     *            of the square
     * @return int, the column that the square is contained in (1 to 9)
     */
    private static int getCol(int index) {
	int row = 0;
	row = index % 9;

	if (row == 0) {
	    row = 9;
	}

	return row;
    }

    /**
     * @param index,
     *            of the square
     * @return int, row that the square is contained in
     */
    private static int getRow(int index) {
	int col = 0;
	if (getCol(index) == 9) {
	    col = index / 9;
	} else {
	    col = index / 9 + 1;
	}

	return col;
    }

    /**
     * @param index,
     *            of the square
     * @return int, the zone that contains the square
     */
    private static int getZone(int index) {
	int zone = 0;

	int col = getCol(index);
	int row = getRow(index);

	if (col > 0 & col < 4 & row > 0 & row < 4) {
	    zone = 1;
	} else if (col > 3 & col < 7 & row > 0 & row < 4) {
	    zone = 2;
	} else if (col > 6 & col < 10 & row > 0 & row < 4) {
	    zone = 3;
	} else if (col > 0 & col < 4 & row > 3 & row < 7) {
	    zone = 4;
	} else if (col > 3 & col < 7 & row > 3 & row < 7) {
	    zone = 5;
	} else if (col > 6 & col < 10 & row > 3 & row < 7) {
	    zone = 6;
	} else if (col > 0 & col < 4 & row > 6 & row < 10) {
	    zone = 7;
	} else if (col > 3 & col < 7 & row > 6 & row < 10) {
	    zone = 8;
	} else if (col > 6 & col < 10 & row > 6 & row < 10) {
	    zone = 9;
	}

	return zone;
    }

    /**
     * Set the value of the square to a specified value
     * 
     * @param value
     */
    public void setValue(int value) {
	this.value = value;
    }

    /**
     * Check for equality
     * 
     * @param square
     * @return boolean, true if equal and false otherwise
     */
    public boolean equals(Square square) {
	if (this.value == square.value) {
	    return true;
	} else {
	    return false;
	}

    }

    /**
     * @return string, the value of the square
     */
    public String toString() {
	return "Square [row = " + row + ", col = " + col + ", zone = " + zone + ", value = " + value;
    }

}
