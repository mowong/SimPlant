class SimPlant implements Messageable {

  public static void main(String[] args) {
    new SimPlant();
  }

  // this will be replaced by a game for each phone number
  public Game game = new Game();

  private SimPlant() {
    System.out.println("SimPlant is begin!\n");
    new Thread(new PromptServer(this)).start();
    new Thread(new HttpServer(this)).start();
  }

  @Override
  public String message(String from, String body) {
    return "[ from:\"" + from + "\"; " + "body:\"" + body + "\" ]" +
           game.processCommand(body);
  }

}
