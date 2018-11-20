import java.util.Random;
import java.util.stream.Stream;

public class FeedTracker implements TrackerInterface {

  // LEVEL LOGIC
  // One application adds the same amount as 33 days removes.
  // If the plant is at the ideal level,
  // it wall die after 3 applications at once and
  // it will die after 66 days without an application.

  private static final double IDEAL_LEVEL = 40.0;

  private static final double DECREMENT = 20.0 / 33.0;
  // 68% of the time it will be ± this amount:
  private static final double DECREMENT_SD = (20.0 / 33.0) * 0.08; // 8%


  private static final double INCREMENT = 20.0;
  // 68% of the time it will be ± this amount:
  private static final double INCREMENT_SD = 1.6; // 8%

  private enum Zone {
    // must be in order from largest to smallest
    // minimum percentage, healthy, deadly, array of status messages
    MORE_XX(100.0, false, true,
            new String[]{
                "It has burnt to a crisp. ",
                "The leaves are all shrivelled up and burnt. "
            }
    ),
    MORE_02(80.0, false, false,
            new String[]{
                "Some leaves near the top are shrivelled up. " +
                "The tips of some others are yellowing"
            }
    ),
    MORE_01(65.0, true, false,
            new String[]{
                "Some of the top-most leaves are looking a little yellow. " +
                "The tips of them are also a little burnt. "
            }
    ),
    PERFECT(30.0, true, false,
            new String[]{
                "The leaves are a nice deep green. ",
                "The leaves look so healthy!"
            }
    ),
    LESS_01(10.0, true, false,
            new String[]{
                "Some of the bottom leaves are turning yellow. ",
                "There's some yellowing on the lower leaves"
            }
    ),
    LESS_02(0.0, false, false,
            new String[]{
                "Many leaves are yellow. " +
                "Some of them are drying up and falling off. "
            }
    ),
    LESS_XXX(-1.0, false, true,
             new String[]{
                 "The leaves have all brown and crinkly. ",
                 "The leaves are falling off. "
             }
    );

    private double min;
    private boolean healthy;
    private boolean deadly;
    private String[] statuses;

    public double getMin() {
      return min;
    }

    Zone(double min, boolean healthy, boolean deadly, String[] statuses) {
      this.min = min;
      this.healthy = healthy;
      this.deadly = deadly;
      this.statuses = statuses;
    }

    static Zone getZone(double percentage) {
      return Stream
                 .of(Zone.values())
                 .filter(g -> percentage > g.getMin())
                 .findFirst()
                 .orElseThrow(() -> new IllegalStateException("No valid Zone."));
    }

    public boolean isHealthy() {
      return healthy;
    }

    public boolean isDeadly() {
      return deadly;
    }

    public String getStatus() {
      return statuses[new Random().nextInt(statuses.length)];
    }
  }

  private Random random = new Random();
  private double level; // percentage

  public FeedTracker() {
    level = IDEAL_LEVEL;
  }

  @Override
  public void step() {
    level -= DECREMENT + DECREMENT_SD * random.nextGaussian();
    if ( level < 0 ) level = 0; // > -1 and <= 0 is dead
  }

  @Override
  public void apply() {
    level += INCREMENT + INCREMENT_SD * random.nextGaussian();
    if ( level > 100 ) level = 101; // > 100 is dead
  }

  @Override
  public String getStatus() {
    return Zone.getZone(level).getStatus();
  }

  @Override
  public boolean isHealthy() {
    return Zone.getZone(level).isHealthy();
  }

  @Override
  public boolean isDead() {
    return Zone.getZone(level).isDeadly();
  }

  @Override
  public String getCauseOfDeath() {
    return Zone.getZone(level).getStatus();
  }
}
