# Java

# dice_games

Hey all!

This may be a bit of an ambitious project, but I want to create an application to include several different dice games. The reason I've decided on this is because I'd like to build a project without having to look any (or much) up. 

# current progress
yahtzee is making strong progress. currently:

* all scoring methods work flawlessly with the exception of small straight. need to figure out the logic behind differentiating 1-2-3-4 and 1-2-4-5, for instance.
* rolls can be scored at any time. type the category you'd like to be judged
* need a way to keep track of which categories are filled, and which ones aren't. 
* still need to implement calculating the final score once the game has completed. game prints a final score, but does not include bonus points (YET)

Update 09/28/2019
	The game has been refactored tremendously. I originally had all of the UI logic inside of the Yahtzee class, but that doesn't make sense. One, it made me add all these different parameters to all of the different functions, which takes away it's readability. Two, the point of OOP is to make reusable code. If I wanted to turn this into a GUI game, then having all command line inside the class would mean I would need to refactor the code anyway. At least now, I shouldn't have to mess with Yahtzee class very much if I wanted to use it in a different format.
