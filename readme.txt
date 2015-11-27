
Added difficulty setting:
	Replaced the generate button with hard, medium, and easy buttons.
	When each is pressed respectively an appropriate game is generated.
	Different difficulties were added by modifying the generation algorithm,
	allowing for a flag to decide which difficulty of game needs to be generated.
Added time list:
	A file has been added where the time needed to complete the game is added,
	at the finish of every game. This file is also used, to display the previous
	game time on board. This time is recalculated every run as well as when easy,
	medium or hard is pressed.
Fixed bug in solutions algorithm:
	Ensured that the algorithm was checking zones, rows and columns because 
	we noticed that it was previously only checking zones
Added save and load option:
	buttons were added allowing a player to save the most recent game, and
	load the most previously saved game. Whenever save is pressed, all squares 
	and there contents as well as time and previous game are saved, so that
	when a player pressed load the game generated is in the exact same state as
	when it was previously tested
Refactoring:
	Cleaned up code to ensure that it was cleaner and easier to understand.
	Added comments to reduce confusion between developers.
Testing:
	Added more tests to ensure the reliability of the game.