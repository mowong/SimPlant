//TrackerTester.java

public class TrackerTester {

  private static WaterTracker waterTracker = new WaterTracker();
  private static FeedTracker feedTracker = new FeedTracker();

  public static void main(String[] args) {

    // Apply #1
    System.out.println("\nApplication #1");
    testApply();

    // Apply #2
    System.out.println("\nApplication #2");
    testApply();

    // Step #1
    System.out.println("\nStep #1");
    testStep();

    // Step #2
    System.out.println("\nStep #2");
    testStep();

    // Step #3
    System.out.println("\nStep #3");
    testStep();

    // Step #4
    System.out.println("\nStep #4");
    testStep();

    // Step #5
    System.out.println("\nStep #5");
    testStep();

    // Step #6
    System.out.println("\nStep #6");
    testStep();

    // Step #7
    System.out.println("\nStep #7");
    testStep();

    // Apply #3
    System.out.println("\nApplication #3");
    testApply();

    // Apply #4
    System.out.println("\nApplication #4");
    testApply();

  }

  private static void testApply() {
    applyTrackers();
    printTrackers();
    printTrackerStatus();
    printTrackerHealth();
  }

  private static void testStep() {
    stepTrackers();
    printTrackers();
    printTrackerStatus();
    printTrackerHealth();
  }

  private static void applyTrackers() {
    waterTracker.apply();
    feedTracker.apply();
  }

  private static void stepTrackers() {
    waterTracker.step();
    feedTracker.step();
  }

  private static void printTrackers() {
    System.out.println(waterTracker);
    System.out.println(feedTracker);
  }

  private static void printTrackerStatus() {
    System.out.println(waterTracker.getStatus());
    System.out.println(feedTracker.getStatus());
  }

  private static void printTrackerHealth() {
    System.out.println("Water level: " + (waterTracker.isHealthy() ? "" : "Not " )+ "Healthy");
    if ( waterTracker.isDead() )
      System.out.println(waterTracker.getCauseOfDeath());

    System.out.println("Fertilizer level: " + (feedTracker.isHealthy() ? "" : "Not ") + "Healthy");
    if ( feedTracker.isDead() )
      System.out.println(feedTracker.getCauseOfDeath());

  }

}