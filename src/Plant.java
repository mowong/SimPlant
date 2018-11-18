import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Plant {

  private static final int STEP_SECONDS = 60 * 60 * 24; // one day
  private static final String STEP_STRING = "days";

  private Instant born;
  private int lastUpdated; // steps since born

  private Map<Trackers, TrackerInterface> trackerMap;

  Plant() {
    born = Instant.now();
    trackerMap = new HashMap<>(4);
    trackerMap.put(Trackers.WATER, new WaterTracker());
    trackerMap.put(Trackers.FEED, new FeedTracker());
    trackerMap.put(Trackers.SPRAY, new SprayTracker());
    trackerMap.put(Trackers.BLOSSOM, new BlossomTracker(this));
  }

  String action(Trackers tracker) {
    String response;
    update();
    if ( isDead() ) {
      response = getDeathMessage();
      // doesn't tell game the plant is dead
      // game will have to check by calling isDead()
    } else {
      response = getAliveMessage();
      trackerMap.get(tracker).apply();
    }
    return response;
  }

  boolean isDead() {
    boolean dead = false;
    for ( TrackerInterface tracker : trackerMap.values() )
      if ( tracker.isDead() ) dead = true;
    return dead;
  }

  private void update() {
    long nanosOld = Duration.between(born, Instant.now()).toNanos();
    long stepsOld = (nanosOld / 1000000000) / STEP_SECONDS;
    while ( lastUpdated <= stepsOld && !isDead() ) {
      step();
      lastUpdated++;
    }
  }

  private void step() {
    for ( TrackerInterface tracker : trackerMap.values() )
      tracker.step();
  }

  private String getAliveMessage() {
    return "Your plant is " + getDaysOld() + ". " + getStatus();
  }

  private String getDaysOld() {
    return lastUpdated + " " + STEP_STRING +
           " day" + (lastUpdated == 1 ? "" : "s") + " old";
  }

  private String getStatus() {
    StringBuilder status = new StringBuilder();
    for ( TrackerInterface tracker : trackerMap.values() )
      status.append(tracker.getStatus().trim() + " ");
    return status.toString();
  }

  private String getDeathMessage() {
    return "Your plant is dead. " +
           getCauseOfDeath() +
           " It was " + getDaysOld() + ".";
  }

  private String getCauseOfDeath() {
    StringBuilder causeOfDeath = new StringBuilder();
    for ( TrackerInterface tracker : trackerMap.values() )
      causeOfDeath.append(tracker.getCauseOfDeath().trim() + " ");
    return causeOfDeath.toString();
  }

}
