// Parses Incoming Messages
// Has a PlantModel object
//
class Game {
  private String id;
  private Plant plant;
  private GameLoader loader;
  private GameState gameState;
  private GameState lastState;

  Game(GameLoader loader, String id) {
    this.id = id;
    this.plant = new Plant();
    this.loader = loader; // will call this to delete user
    gameState = GameState.GAME_IS_ON;
  }

  String getInitialStatus() {
    return "You've got a new plant! " + plant.action(PlantAction.CHECK);
  }

  String processCommand(String command) {

    switch ( gameState ) {
      case GAME_IS_ON:
        return commandGameIsOn(command);
      case CONFIRM_NEW:
        return yesOrNo() ? newPlantConfirmed() : retreat();
      case CONFIRM_QUIT:
        return yesOrNo() ? quitConfirmed() : retreat();
      case NEW_PLAYER:
      case CONFIRM_FIRST_GAME:
    }
    return null;
  }

  String retreat() {
    gameState =lastState;
  }

  String commandGameIsOn(String command) {
    switch ( (command + "   ").substring(0, 3).toLowerCase() ) {

      // PLANT COMMANDS

      case "   ":  // EMPTY MESSAGE
      case "loo":  // look
      case "che":  // check
      case "sta":  // status
        return response(plant.action(PlantAction.CHECK));
      case "wat":  // water
        return response(plant.action(PlantAction.WATER));
      case "fee":  // feed
      case "fer":  // fertilize
        return response(plant.action(PlantAction.FEED));
      case "spr":  // spray
      case "bug":  // bug-spray
      case "pes":  // pesticide
        return response(plant.action(PlantAction.SPRAY));

      // GAME COMMANDS

      case "hel":  // help
        return getHelpMessage();
      case "new":  // new plant
      case "end":  // end
      case "qui":  // quit
      case "exi":  // exit
      case "kil":  // kill
        return newPlant();
      default:
        return getUnknownCommandResponse(command);

    }
  }

  private String response(String message) {
    if ( plant.isDead() ) loader.deleteGame(id);
    return message;
  }

  private String getHelpMessage() {
    return "This is a not-very-useful help message.";
  }

  private String getUnknownCommandResponse(String message) {
    return message.length() == 0 ?
               "Please enter a command." :
               "I don't know how to '" + message + "'.";
  }

  private String newPlant() {
    gameState = GameState.CONFIRM_NEW;
    return "Are you sure you want to start a new plant?";
  }

  private String killPlant() {
    gameState = GameState.CONFIRM_QUIT;
    return "Are you sure you want to quit the game? " +
           "(Your plant will be lost!)";
  }

  private String processYes(String command) {
    switch ( gameState ) {
      case GAME_IS_ON:
        return what(command);
      case CONFIRM_NEW:
        return newPlantConfirmed();
      case CONFIRM_QUIT:
        return quitConfirmed();
      default:
        throw new IllegalStateException("Invalid game state.");
    }
  }

  private String processNo(String command) {
    switch ( gameState ) {
      case GAME_IS_ON:
        return what(command);
      case CONFIRM_NEW:
        return newPlantAborted();
      case CONFIRM_QUIT:
        return quitAborted();
      default:
        throw new IllegalStateException("Invalid game state.");
    }

  }

  private String what(String command) {
    return command + "?";
  }

  private String newPlantConfirmed() {
    plant = new Plant();
    return plant.action(PlantAction.CHECK);
  }

  private String newPlantAborted() {
    return "Good.";
  }

  private String quitConfirmed() {
    loader.deleteGame(this.id);
    return "Your plant has been discarded. ";
  }

  private String quitAborted() {
    return "Good.";
  }

}
