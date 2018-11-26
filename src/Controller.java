class Controller implements Messageable {

  public static void main(String[] args) {
    new Controller();
  }

  // this will be replaced by a game for each phone number
  private GameLoader gameLoader;

  private Controller() {
    gameLoader = new GameLoader(this);
    System.out.println("SimPlant server live!\n");
    new Thread(new PromptServer(this)).start();
<<<<<<< HEAD
//    new Thread(new HttpServer(this)).start();
=======

    // run this on main thread
    new HttpServer(this).run();

>>>>>>> f4d3aa61ff78f0d4d34aa4b07044038f198c36da
  }

  @Override
  public String message(String from, String body) {
    // retrieve game from database or whatever
    Game game = gameLoader.getGame(from);
    if ( game == null ) return noGame(from, body); // no current game;
    // process the incoming message
    return game.processCommand(body);
  }

  String endGame(Game game) {
    gameLoader.deleteGame(game.id); // can we return to game's method if it is now null?
    return "Your plant has been discarded. Would you like to grow a new plant?";
  }

  String gameOver(Game game, String message) {
    gameLoader.deleteGame(game.id); // can we return to game's method if it is now null?
    return message + "Would you like to grow a new plant?";
  }

  private String noGame(String from, String body) {
    if ( body.length() > 0 && body.toLowerCase().charAt(0) == 'y' ) {
      return newGame(from, body);
    }
    return "Would you like to grow a new plant?";
  }

  private String newGame(String from, String body) {
    Game game = gameLoader.newGame(from);
    return game.getInitialStatus();
  }


}
