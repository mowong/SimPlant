// Parses Incoming Messages
// Has a PlantModel object
//
class Game {
  private String id;
  private Plant plant;
  private GameLoader loader;
  private GameState gameState;
  private GameState lastState;
  private int oldestPlant;

  Game(GameLoader loader, String id) {
    gameState = GameState.NEW_PLAYER;
    oldestPlant = -1;
    //// in a future version Game will call loader with id to delete a player
    // this.id = id;
    // this.loader = loader;
  }

  // all threads enter through here
  // therefore this is the only method that needs to be synchronized
  synchronized String processCommand(String rawCommand) {
    //String command = (rawCommand + "   ").substring(0, 3).toLowerCase();
    String command = rawCommand.toLowerCase();
    try {
      switch ( gameState ) {
        case NEW_PLAYER:
          return newPlayer();
        case GAME_IS_ON:
          return gameIsOn(command);
        case GAME_OVER:
          return gameOver();
        case CONFIRM_NEW:
          return yesOrNo(command) ? confirmNew() : abortNew();
        case CONFIRM_QUIT:
          return yesOrNo(command) ? confirmQuit() : abortQuit();
      }
    } catch ( IllegalArgumentException e ) {
      switch ( gameState ) {
        case CONFIRM_NEW:
          return "Please answer 'yes' or 'no': " + askNew();
        case CONFIRM_QUIT:
          return "Please answer 'yes' or 'no': " + askQuit();
        case GAME_IS_ON:
          return helpMessage();
      }
    }

    return null;
  }

  private boolean yesOrNo(String command)
      throws IllegalArgumentException {
    switch ( command ) {
      case "yes": // yes
      case "'yes'":
      case "y":
      case "ya":
      case "yea":
      case "ok":
      case "for sure": // for sure
      case "do it": // do it
        return true;
      case "no":
      case "'no'":
      case "n":
      case "no way":
      case "abort":
        return false;
      default:
        throw new IllegalArgumentException();
    }
  }

  private void revertState() {
    gameState = lastState;
  }

  private void setState(GameState newState) {
    lastState = gameState;
    gameState = newState;
  }

  private String gameIsOn(String command)
      throws IllegalArgumentException {
    switch ( command ) {

      // PLANT COMMANDS

      case "": // return status message to empty command
      case "check":
      case "che":
      case "c":
      case "'check'":
      case "look":
      case "loo":
      case "l":
      case "status":
        return doAction(PlantAction.CHECK);
      case "water":
      case "wat":
      case "w":
      case "'water'":
        return doAction(PlantAction.WATER);
      case "feed":
      case "fee":
      case "f":
      case "fertilize":
      case "fer":
        return doAction(PlantAction.FEED);
      case "spray":
      case "spr":
      case "s":
      case "bug":
      case "b":
        return doAction(PlantAction.SPRAY);

      // GAME COMMANDS

      case "help":  // help
      case "hel":
      case "h":
      case "'help'":  // 'help'
        return helpMessage();
      case "end":  // end
      case "quit":  // quit
      case "exit":  // exit
      case "kill":  // kill
        return askQuit();
      default:
        throw new IllegalArgumentException();
    }
  }

  private String doAction(PlantAction action) {
    return checkForDead(plant.action(action));
  }

  private String checkForDead(String message) {
    if ( plant.isDead() ) {
      updateOldestPlant();
      setState(GameState.GAME_OVER);
      return message + gameOver();
    }
    return message;
  }

  private void updateOldestPlant() {
    int age = plant.getAge();
    if ( age > oldestPlant ) oldestPlant = age;
  }

  private String helpMessage() {
    return "You can \'water\', \'feed', \'spray\' or \'check\' your plant. \n " +
           "You can also \'kill\' your plant to start again.";
  }

  private String getHowToGetHelp() {
    return "(Send 'help' for help!)";
  }

  // asks to confirm starting a new plant (new players)
  private String newPlayer() {
    return "Welcome to SimPlant! " + askNew();
  }

  // asks to confirm starting a new plant (returning players)
  private String gameOver() {
    return "Your oldest plant was " + Plant.ageString(oldestPlant) + ". "
           + askNew();
  }


  // asks to confirm starting a new plant
  private String askNew() {
    setState(GameState.CONFIRM_NEW);
    return "Would you like to grow a new plant?";
  }

  // starts a new plant
  private String confirmNew() {
    setState(GameState.GAME_IS_ON);
    plant = new Plant();
    return "You have a new plant! " +
           plant.action(PlantAction.CHECK) +
           " " + // action status strips trailing space
           getHowToGetHelp();
  }

  // returns to previous state
  private String abortNew() {
    setState(GameState.GAME_OVER); // revertState();
    return "Let me know when you change your mind! (Any text will do.)";
  }

  // asks to confirm quitting a plant
  private String askQuit() {
    setState(GameState.CONFIRM_QUIT);
    return "Are you sure you want to quit the game? " +
           "(Your plant will be lost!)";
  }

  // returns to previous state
  private String abortQuit() {
    setState(GameState.GAME_IS_ON); // revertState();
    return "Ok good. " + doAction(PlantAction.CHECK);
  }

  // quits the game
  private String confirmQuit() {
    updateOldestPlant();
    setState(GameState.GAME_OVER);
    return gameOver();
  }


}
