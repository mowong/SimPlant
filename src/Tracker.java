import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

class Tracker implements TrackerInterface {

  private Random random = new Random();
  private double level; // percentage

  private final Levels levels;
  private final Zone[] zones;

  Tracker(Levels levels, Zone[] zones) {
    this.levels = levels;
    Arrays.sort(
        zones,(x,y)-> Double.compare(y.getMinPercent(), x.getMinPercent())
    );
    this.zones = zones;
    level = levels.ideal;
  }

  double getLevel() {
    return level;
  }

  void setLevel(double level) {
    this.level = level;
  }

  @Override
  public void step() {
    level -= levels.decrement + levels.decrementDev * random.nextGaussian();
    if ( level < 0 ) level = 0; // > -1 and <= 0 is dead
  }

  @Override
  public void apply() {
    level += levels.increment + levels.incrementDev * random.nextGaussian();
    if ( level > 100 ) level = 101; // > 100 is dead
  }

  @Override
  public String getStatus() {
    return getZone().getStatus();
  }

  @Override
  public boolean isHealthy() {
    return getZone().isHealthy();
  }

  @Override
  public boolean isDead() {
    return getZone().isDeadly();
  }

  @Override
  public String getCauseOfDeath() {
    return getZone().getStatus();
  }


  @Override
  public String toString() {
    return toString("");
  }

  String toString(String prefix) {
    return String.format("%s zone: %s level: %.2f", prefix, getZone().code, level);
  }

  @Override
  public String getLevelCode() {
    return getLevelCode("");
  }

  String getLevelCode(String prefix) {
    return String.format("%s%02.0f%s", prefix, level, getZone().code);
  }


  private Zone getZone() {
    return Stream
               .of(zones)
               .filter(g -> level > g.getMinPercent())
               .findFirst()
               .orElseThrow(() -> new IllegalStateException("No valid Zone."));
  }

  static class Levels {

    private final double ideal;

    private final double decrement;
    private final double decrementDev;

    private final double increment;
    private final double incrementDev;

    Levels(double ideal,
                  double decrement, double decrementDevPercent,
                  double increment, double incrementDevPercent) {
      this.ideal = ideal;
      this.decrement = decrement;
      this.decrementDev = decrement * decrementDevPercent / 100;
      this.increment = increment;
      this.incrementDev = increment * incrementDevPercent / 100;
    }

    @Override
    public String toString() {
      return "ideal        : " + ideal + "\n" +
             "decrement    : " + decrement + "\n" +
             "decrementDev : " + decrementDev + "\n" +
             "increment    : " + increment + "\n" +
             "incrementDev : " + incrementDev;
    }
  }

  static class Zone {

    private String code;
    private double minPercent;
    private boolean healthy;
    private boolean deadly;
    private String[] statuses;

    Zone(String code, double minPercent,
         boolean healthy, boolean deadly,
         String[] statuses) {
      this.code = code;
      this.minPercent = minPercent;
      this.healthy = healthy;
      this.deadly = deadly;
      this.statuses = statuses;
    }

    double getMinPercent() {
      return minPercent;
    }

    boolean isHealthy() {
      return healthy;
    }

    boolean isDeadly() {
      return deadly;
    }

    String getStatus() {
      return statuses[new Random().nextInt(statuses.length)];
    }

    @Override
    public String toString() {
      return "minPercent  : " + minPercent + "\n" +
             "healthy     : " + healthy + "\n" +
             "deadly      : " + deadly + "\n" +
             "statuses    : " + Arrays.toString(statuses);
    }

  }

}
