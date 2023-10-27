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
   ### TODO
     * tilt control

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
    Clear categories and right/wrong values after in-game done -- DONE
    Change LiveData list from Ints to Strings --DONE
    Add ending screen with scores
    Add & implement word dictionaries for categories
    
### Week4
    added dictionary to view model with empty list of string
    added title screen and transition changes
    limit the number of selected category to 1

    NEXT TIME:
    add words to empty list
    Add ending screen with score and a return to main menu
    improve title to main menu transition

### Week5
    made ending screen with scroe tracker
    redesigned category map to static map
    changed live data from list to string

    NEXT TIME:
    reorganizing code and layout
    separating function into intention

### Week6
    Separated into multiple activity using intent
    tranfer data across differnt view model of each actitity using put Extra and get extra

--------------------------------------FINSHED BASESD GAME-----------------------------------------------------
     Next Time:
     determin what to do with hard coded list of words 
     Start database 
     make file to store list of Words and categories for the based game 
     increase the number of word in each category
     change category
     
    
    
