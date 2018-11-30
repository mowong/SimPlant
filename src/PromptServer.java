import java.util.Scanner;

class PromptServer implements Runnable {

  private static final String PHONE_NUMBER = "PROMPT";

  private Messageable receiver;

  PromptServer(Messageable receiver) {
    this.receiver = receiver;
  }

  @Override
  public void run() {
    Scanner scanner = new Scanner(System.in);

    while ( true ) {
      String input = scanner.nextLine();
      if ( input.equalsIgnoreCase("shutdown") )
        System.exit(0);
      System.out.println(
          receiver.message(
              PHONE_NUMBER, input
          )
      );
    }
  }
}
