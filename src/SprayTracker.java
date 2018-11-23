import java.util.Random;
import java.util.stream.Stream;

public class SprayTracker implements TrackerInterface {
  private static final int MIN_SPRAY_LEVEL = 10;
  private static final int SPRAY_AMOUNT = 10;
  private static final int STARTING_SPRAY_AMOUNT = 20;
  private static final int MAX_SPRAY_LEVEL = 50;
  private static final int MAX_MULTIPLIER = 2;
  private static final int BUGGY_STILL_HEALTHY = 10;
  private static final int DEAD_FROM_BUGS = 100;
  private static final double PROBABILITY_OF_NEW_BUGS = 0.10F;
  private static final double PROBABILITY_OF_MORE_BUGS = 0.15F;

  private int sprayLevel;
  private double numberOfBugs;

  private Random random = new Random();

  private enum Zone {
	    // must be in order from largest to smallest
	    // minimum number, healthy, deadly, array of status messages
	    MANY_XX(100.0, false, true,
	            new String[]{
	                "It has turned into a plant skelton. ",
	                "The bugs ate it all up. "
	            }
	    ),
	    MANY_02(80.0, false, false,
	            new String[]{
	                "The bugs are sending scouting parties to find new plants to eat. ",
	                "There's not much plant left. "
	            }
	    ),
	    MANY_01(65.0, false, false,
	            new String[]{
	                "The bugs are starting to take over. ", 
	                "The bugs are making themselves at home. "
	            }
	    ),
	    MORE(30.0, false, false,
	            new String[]{
	                "It's getting a buggy. "
	            }
	    ),
	    SOME(10, true, false,
	            new String[]{
	                "What's a few bugs between friends? "
	            }
	    ),
	    PERFECT(-1.0, true, false,
	            new String[]{
	                "No bugs to be seen. "
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

	  private double level; // percentage
	  
  SprayTracker() {
    numberOfBugs = 0;
    sprayLevel = STARTING_SPRAY_AMOUNT;
    level = numberOfBugs;
  }

  private boolean hasBugs() {
    return numberOfBugs > 0;
  }

  // decrement spray-level every day,
  // calls bugs if spray level is low
  @Override
  public void step() {
    sprayLevel -= 2;
    if ( sprayLevel < 0 )
      sprayLevel = 0;
    if ( !hasBugs() ) {
      if ( sprayLevel < MIN_SPRAY_LEVEL )
        bugCaller();
    } else {
      bugMultiplier();
    }
  }

  // apply pesticides
  @Override
  public void apply() {
    sprayLevel += SPRAY_AMOUNT;
    if ( sprayLevel > MAX_SPRAY_LEVEL )
      sprayLevel = MAX_SPRAY_LEVEL;
    // kill bugs
    bugKiller();
  }

//   return status string
  @Override
  public String getStatus() {
	  return Zone.getZone(level).getStatus();
//    return hasBugs() ? "It has bugs. " : null;
  }

  // if spray level is below a certain point bugs might appear
  private void bugCaller() {
    if ( random.nextFloat() < PROBABILITY_OF_NEW_BUGS )
      numberOfBugs = 1; // will multiply quickly
  }

  // should only be called weekly, once a week? - put in random chance
  private void bugMultiplier() {
    if ( random.nextFloat() < PROBABILITY_OF_MORE_BUGS )
      numberOfBugs *= (random.nextFloat() * MAX_MULTIPLIER + 1);
  }

  // kill some bugs (if there are any)
  private void bugKiller() {
    if ( hasBugs() )
      numberOfBugs *= random.nextFloat();
    if (numberOfBugs < 1)
    	numberOfBugs = 0;
  }

  @Override
  public boolean isHealthy() {
    return numberOfBugs < BUGGY_STILL_HEALTHY;
  }

  // if bugs go over a certain number, plant is dead
  @Override
  public boolean isDead() {
    return numberOfBugs > DEAD_FROM_BUGS;
  }

  // return reason plant died: from bugs
  @Override
  public String getCauseOfDeath() {
    return "The plant was eaten up by bugs.";
  }
}