class SimPlant implements Messageable {

  public static void main(String[] args) {
    new SimPlant();
  }

  private SimPlant() {
    new Thread(new PromptServer(this)).start();
    new Thread(new HttpServer(this)).start();
    System.out.println("SimPlant is begin!\n");
  }

  @Override
  public String message(String from, String body) {
    return "[ from:\"" + from + "\"; " + "body:\"" + body + "\" ]";
  }

}
