class Controller implements Messageable {

  public static void main(String[] args) {
    new Controller();
  }

  private Controller() {
    System.out.println("SimPlant is begin!\n");
    new Thread(new PromptServer(this)).start();
    new Thread(new HttpServer(this)).start();
  }

  @Override
  public String message(String from, String body) {
    return "[ from:\"" + from + "\"; " + "body:\"" + body + "\" ]";
  }

}
