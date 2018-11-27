//TrackerTester.java

import java.util.ArrayList;
import java.util.List;

public class TrackerTester {

  private static List<TrackerInterface> trackerList;

  public static void main(String[] args) {

    trackerList = new ArrayList<>(2);
    trackerList.add(new WaterTracker());
    trackerList.add(new FeedTracker());
    trackerList.add(new BugTracker());

    System.out.println("\n\nInitial State of Trackers:");
    printTrackers();

    // Apply #1
    testApply(1);

    // Apply #2
    testApply(2);

    // Step #1
    testStep(1);

    // Step #2
    testStep(2);

    // Step #3
    testStep(3);

    // Step #4
    testStep(4);

    // Step #5
    testStep(5);

    // Step #6
    testStep(6);

    // Step #7
    testStep(7);

    // Apply #3
    testApply(3);

    // Apply #4
    testApply(4);

  }

  private static void testApply(int num) {
    System.out.println("\n\nApplication #" + num);
    applyTrackers();
    printTrackers();
  }

  private static void testStep(int num) {
    System.out.println("\n\nStep #" + num);
    stepTrackers();
    printTrackers();
  }


  private static void applyTrackers() {
    for ( TrackerInterface tracker : trackerList )
      tracker.apply();
  }

  private static void stepTrackers() {
    for ( TrackerInterface tracker : trackerList )
      tracker.step();
  }

  private static void printTrackers() {
    for ( TrackerInterface tracker : trackerList ) {
      System.out.println();
      System.out.println(tracker);
      System.out.println(tracker.getStatus());
      System.out.println("Health: " + (tracker.isHealthy() ? "GOOD" : "BAD" ));
      if ( tracker.isDead() )
        System.out.println("Dead: " + tracker.getCauseOfDeath());
    }
  }
}