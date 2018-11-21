class Controller implements Messageable {

  public static void main(String[] args) {
    new Controller();
  }

  // this will be replaced by a game for each phone number
  public Game game;

  private Controller() {
    System.out.println("SimPlant is begin!\n");
    new Thread(new PromptServer(this)).start();
    new Thread(new HttpServer(this)).start();
  }

  @Override
  public String message(String from, String body) {
    //// retrieve game from database or whatever
    if ( game == null ) return noGame(from, body); // no current game;
    return game.processCommand(body);
  }

  String endGame() {
    game = null; // can we return to game's method if it is now null?
    // IT WORKS!
    return "Your plant has been discarded. Would you like to grow a new plant?";
  }

  private String noGame(String from, String body) {
    if ( body.length() > 0 && body.toLowerCase().charAt(0) == 'y' ) {
      return newGame(from, body);
    }
    return "Would you like to grow a new plant?";
  }

  private String newGame(String from, String body) {
    game = new Game(this);
    return game.getInitialStatus();
  }


}
