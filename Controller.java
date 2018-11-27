class Controller implements Messageable {

  public static void main(String[] args) {
    new Controller();
  }

  // this will be replaced by a game for each phone number
  private GameLoader gameLoader;

  private Controller() {
    gameLoader = new GameLoader();
    System.out.println("SimPlant server live!\n");

    // incoming message will initiate their own threads
    new Thread(new PromptServer(this)).start();

    // messages from prompt runs on main thread
//    new HttpServer(this).run();

  }

  @Override
  public String message(String from, String body) {
    // retrieve game from database or whatever
    Game game = gameLoader.getGame(from); // always returns a game
    return game.processCommand(body);
  }

}
