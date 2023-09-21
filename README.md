# Phamspect Projects
 
## Heads Up Game for Android: 
     * Plain text on blank background heads up game
     * Use randoms words to pop up -> Categories menu -> add database later
     * Timer Display
     * Total Display / Score total
     * Clicker Sound
     * Phone tilt recognition up & down to skip or confirm correct -> Display on screen
     * Show X or Checkmark during tilt for right or wrong
     * Make phone vibrate when phone tilts
   ### Languages
     * Android Studio for Code + Emulator
     * SQL for database

### Week 1
    Finished main menu UI
    Implemented timer functionality
    NEXT TIME:
    
    Make category buttons able to select multiple as a variable to choose possible words for gamestart
    Create UI for in-game, UI will immediately start the timer and show random word from chosen categories

### Week 2
    Added funtionality to select a catergory from category buttons
    Made Activity2 for in game UI
    Added a timer and random word generation to UI

    NEXT TIME:
    Move cateogry choice (1,2,3 etc.) to Acitivity2
    Make dictionary of lists in Activity2 to use as chosen
    Add logic to remove word from list when already showed up before -- DONE
    Add logic to to check for right/wrong answer (eventually by tilting phone) -- DONE
    Add logic to keep track of score (Can also add track of right and wrong answers) --DONE
    
### Week3 
    MAIN MENU CHANGES:
        Made mainmenu Buttons onClick change color
        Created LiveData list to retain categories clicked on in mainmenu
        Made startbutton work only if LiveData list is not empty
    IN GAME CHANGES:    
        Added loading UI transition from mainmenu to in game
        Improved UI looks
        Added right/wrong buttons
        Counts score from in game
        Added logic for removing word from list after seen
    
    NEXT TIME:
    Clear categories and right/wrong values after in-game done
    Change LiveData list from Ints to Strings
    Add ending screen with scores
    Add & implement word dictionaries for categories
    