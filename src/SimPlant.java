class SimPlant implements Messageable {

  public static void main(String[] args) {
    new SimPlant();
  }

  private SimPlant() {
    System.out.println("SimPlant is begin!\n");
    new Thread(new PromptServer(this)).start();
    new Thread(new HttpServer(this)).start();
  }

  @Override
  public String message(String from, String body) {
    return "[ from:\"" + from + "\"; " + "body:\"" + body + "\" ]";
  }

}
