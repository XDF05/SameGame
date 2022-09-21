# Projet - SameGame

### Description

Same Game is a solitary game.

The player has the possibility to choose a block to remove. Every adjacent block with the same color ( and their
adjacent blocks with the same color)
also gets removed

The objective is to have the least amount of blocks remaining by the end of the game

### Details

* the player has the possibility to choose the amount of blocks there is [5-20].
* There are 3 different difficulties which the user can choose from. (each difficulty adds a color).
    * 3 (red - green - blue)
    * 4 (red - green - blue - yellow)
    * 5 (red - green - blue - yellow - purple)

## Structure

This project has been created following the MVC with Observer Pattern.
```
.
├── Console
│   ├── controller 
│   │   └── Controller.java             #Controller for the console App
│   ├── view
│   │   ├── View.java                   #View Interface implemented by MyView
│   │   └── MyView.java                 #View for the console App
│   └── SameGameConsole.java            #Main class for the console App
│
├── JavaFX
│   ├── controller
│   │   └── Controller.java            #Controller for the GUI App
│   ├── view
│   │   ├── BoardHBox.java             #Board representation
│   │   ├── ColoredBlock.java          #ColoredBlock representation 
│   │   ├── ControlsHBox.java          #Box containing controls
│   │   ├── GamePane.java              #Game view 
│   │   ├── InputDialog.java           #Dialog box asking for game settings
│   │   ├── ScoreHBox.java             #Box containing Scores
│   │   └── MyView.java                #Main view for the GUI App 
│   └── SameGameGUI.java               #Main class for the GUI App
│
├── model                              #Contains data reprentations for the game elements
│   ├── Block.java                     #representation for the blocks
│   ├── Board.java                     #representation for the board
│   ├── Game.java                      #representation for the game
│   ├── Model.java                     #Model Interface implemented by Game
│   ├── Direction.java                 #direction Enumeration
│   └── State.java                     #game states Enumartion
│
└── util
    ├── command
    │   ├── Command.java               #Command interface implemented by Remove
    │   └── Remove.java                #Remove command
    └── observer
        ├── Observable.java            #Observable interface for the model
        └── Observer.java              #Observer interface for the views
```
## Credits

created at HE2B-ESI
