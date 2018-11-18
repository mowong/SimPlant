import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Plant {

  private Game game;
  private LocalDateTime lastUpdated;

  Map<Trackers, TrackerInterface> trackerMap;

  Plant(Game game) {
    trackerMap = new HashMap<>(4);
    trackerMap.put(Trackers.WATER, new WaterTracker());
    trackerMap.put(Trackers.FEED, new FeedTracker());
    trackerMap.put(Trackers.SPRAY, new SprayTracker());
    trackerMap.put(Trackers.BLOSSOM, new BlossomTracker());
  }

  String action(Trackers what) {
    return null;
  }

  void update() {

  }

  boolean isDead() {
    return false;
  }

  String getStatus() {

    return null;
  }

  String getCauseOfDeath() {
    return null;
  }

}
