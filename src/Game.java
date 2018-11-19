// maybe we don't need a game class???
// maybe this can all go in the containing class!!!
// but there is state involved, where will that go?
public class Game {
  PlantModel plant;

  Game() {
    this.plant = new PlantModel();
  }

  String processCommand(String command) {
    switch ( (command + "   ").substring(0, 3).toLowerCase() ) {
      case "loo": // look
      case "che": // check
      case "sta": // status
        return plant.action(PlantAction.CHECK);
      case "wat": // water
        return plant.action(PlantAction.WATER);
      case "fee": // feed
      case "fer": // fertilize
        return plant.action(PlantAction.FEED);
      case "spr": // spray
      case "bug": // bug-spray
      case "pes": // pesticide
        return plant.action(PlantAction.SPRAY);
      case "hel": // help
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
    return message.length() == 0 ?
               "Please enter a command." :
               "I don't know how to '" + message + "'.";
  }

}
