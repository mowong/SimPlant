import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class PlantModel {

  private static final int STEP_SECONDS = 60 * 60 * 24; // one day
  private static final String STEP_STRING = "days";

  private Instant born;
  private int lastUpdated; // steps since born

  private Map<PlantActions, TrackerInterface> trackerMap;

  PlantModel() {
    born = Instant.now();
    trackerMap = new LinkedHashMap<>(4);
    trackerMap.put(PlantActions.WATER, new WaterTracker());
    trackerMap.put(PlantActions.FEED, new FeedTracker());
    trackerMap.put(PlantActions.SPRAY, new SprayTracker());
    trackerMap.put(PlantActions.BLOSSOM, new BlossomTracker(this));
  }

  String action(PlantActions tracker) {
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
    return trackerMap.values().stream().anyMatch(TrackerInterface::isDead);
  }

  boolean isHealthy() {
    return trackerMap.values().stream().allMatch(TrackerInterface::isHealthy);
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
    trackerMap.values().forEach(TrackerInterface::step);
  }

  private String getAliveMessage() {
    return "Your plant is " + getDaysOld() + ". " + getStatus();
  }

  private String getDaysOld() {
    return lastUpdated + " " + STEP_STRING +
           " day" + (lastUpdated == 1 ? "" : "s") + " old";
  }

  private String getStatus() {
    return trackerMap.values().stream()
               .map(TrackerInterface::getStatus)
               .filter(Objects::nonNull)
               .map(String::trim)
               .collect(Collectors.joining(" "));
  }

  private String getDeathMessage() {
    return "Your plant is dead. " +
           getCauseOfDeath() +
           " It was " + getDaysOld() + ".";
  }

  private String getCauseOfDeath() {
    return trackerMap.values().stream()
               .map(TrackerInterface::getCauseOfDeath)
               .filter(Objects::nonNull)
               .map(String::trim)
               .collect(Collectors.joining(" "));
  }

}