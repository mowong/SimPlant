class Controller implements Messageable {

  public static void main(String[] args) {
    new Controller();
  }

  // this will be replaced by a game for each phone number
  private GameLoader gameLoader;

  private Controller() {
    gameLoader = new GameLoader();
    System.out.println("SimPlant server live!\n");

    // messages from prompt runs on a new thread
    new Thread(new PromptServer(this)).start();

    // HTTP server runs on the main thread
    // incoming messages will initiate their own threads
    new HttpServer(this).run();

  }

  @Override
  public String message(String from, String body) {
    // retrieve game from database or whatever
    Game game = gameLoader.getGame(from); // always returns a game
    return game.processCommand(body);
  }

}
