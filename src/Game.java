// Parses Incoming Messages
// Has a PlantModel object
//
public class Game {
  Plant plant;
  Controller controller;
  GameState gameState;

  Game(Controller controller) {
    this.plant = new Plant();
    this.controller = controller; // will call this to delete game etc.
  }

  String getInitialStatus() {
    return "You've got a new plant! " + plant.action(PlantAction.CHECK);
  }

  String processCommand(String command) {
    switch ( (command + "   ").substring(0, 3).toLowerCase() ) {

      // PLANT COMMANDS

      case "loo":  // look
      case "che":  // check
      case "sta":  // status
        return plant.action(PlantAction.CHECK);
      case "wat":  // water
        return plant.action(PlantAction.WATER);
      case "fee":  // feed
      case "fer":  // fertilize
        return plant.action(PlantAction.FEED);
      case "spr":  // spray
      case "bug":  // bug-spray
      case "pes":  // pesticide
        return plant.action(PlantAction.SPRAY);

      // GAME COMMANDS

      case "hel":  // help
        return getHelpMessage();
      case "new":
        return newPlant();
      case "end":  // end
      case "qui":  // quit
      case "exi":  // exit
      case "kil":  // kill
        return quitGame();
      case "yes":  // yes
      case "y  ":  // y
        return processYes(command);
      case "no ":  // no
      case "n  ":  // n
        return processNo(command);

      // UNKNOWN COMMANDS

      default:
        return getUnknownCommandResponse(command);

    }
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

  private String quitGame() {
    gameState = GameState.CONFIRM_QUIT;
    return "Are you sure you want to quit the game? " +
           "(Your plant will be lost!)";
  }

  private String processYes(String command) {
    switch ( gameState ) {
      case ACTIVE:
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
      case ACTIVE:
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
    return controller.endGame();
  }

  private String quitAborted() {
    return "Good.";
  }

}
