
In this project we wanted to implement a difficulty functionality, save/load feature, 
score list, and previous time feature


Added difficulty setting:
	Files modified: Sudoku.java, GenerationAlgorithm.java
	
	Replaced the generate button with hard, medium, and easy buttons.
	When each is pressed respectively an appropriate game is generated.
	Different difficulties were added by modifying the generation algorithm,
	allowing for a flag to decide which difficulty of game needs to be generated.
	
Added time list:
	Files modified: Sudoku.java
	
	A file has been added for each difficulty where the time needed to complete
	the game is added, at the finish of every game. This file is also used, to display 
	the previous game time on board. This time is recalculated every run as well as when easy,
	medium or hard is pressed.
	
Added score:
	Files modified: Sudoku.java
	
	Score is sent to a file. At the start of every game the score is read and best score
	is put on board. Formula for best score is 1000000 - (3-difficulty)*time.
	
Fixed bug in solutions algorithm:
	Files modified: SolutionAlgorithm.java
	
	Ensured that the algorithm was checking zones, rows and columns because 
	we noticed that it was previously only checking zones.
	
Added save and load option:
	Files modified: Sudoku.java, Square.java
	
	Buttons were added allowing a player to save the most recent game, and
	load the most previously saved game. Whenever save is pressed, all squares 
	and their contents as well as time and previous game are saved, so that
	when a player presses load, the game generated is in the exact same state as
	when it was previously saved.
	
Refactoring:
	Files modified: all of them
	
	Cleaned up code to ensure that it was cleaner and easier to understand.
	Added comments to reduce confusion between developers.
	
Testing:
	Files modified: SolutionAlgorithmTest.java, SquareTest.java
	
	Added more tests to ensure the reliability of the game.