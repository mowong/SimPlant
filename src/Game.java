public class Game {
  PlantModel plant;

  Game() {
    this.plant = new PlantModel();
  }

  String processCommand(String command) {
    switch ( command.substring(0, 3).toLowerCase() ) {
      case "wat":
        return plant.action(PlantActions.WATER);
      case "fee":
      case "fer":
        return plant.action(PlantActions.FEED);
      case "spr":
      case "bug":
      case "pes":
        return plant.action(PlantActions.SPRAY);
      case "hel":
        return getHelpMessage();
      default:
        return getUnknownCommandResponse(command);
      /*
      // Game-Level stuff, does it go here?
      case "new":
        // newPlant()
      case "end":
      case "qui":
      case "exi":
      case "kil":
        // endGame();
      case "yes":
        // processYes();
      case "no":
        // processNo();
       */
    }
  }

  private String getHelpMessage() {
    return "This is a not-very-useful help message.";
  }

  private String getUnknownCommandResponse(String message) {
    return "I don't know how to '" + message + "'.";
  }

}
