class Controller implements Messageable {

  public static void main(String[] args) {

    boolean hasPrompt = true;

    if ( args.length > 0 && args[0].equals("noui") )
      hasPrompt = false;


    Controller controller = new Controller();

    // start threads for input servers

    // NOTE: running threads are not garbage-collected
    // because they are "Garbage Collection Roots"
    // therefore they do not require reference variables.

    // message prompt runs on a new thread
    if ( hasPrompt )
      new Thread(new PromptServer(controller)).start();

    // HTTP server runs on the a new thread
    // incoming messages will initiate their own threads
    new Thread(new HttpServer(controller)).start();


  }

  // this will be replaced by a game for each phone number
  private GameLoader gameLoader;

  private Controller() {
    gameLoader = new GameLoader();
    System.out.println("SimPlant server live!\n");


  }

  @Override
  public String message(String from, String body) {
    // retrieve game from database or whatever
    // and process the command with it
    return gameLoader.getGame(from).processCommand(body);
  }

}
