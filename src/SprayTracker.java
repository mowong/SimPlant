import java.util.Random;

public class SprayTracker implements TrackerInterface {
  private static final int MIN_SPRAY_LEVEL = 10;
  private static final int SPRAY_AMOUNT = 15;
  private static final int MAX_SPRAY_LEVEL = 100;
  private static final int MAX_MULTIPLIER = 2;
  private static final int BUGGY_STILL_HEALTHY = 10;
  private static final int DEAD_FROM_BUGS = 100;
  private static final float PROBABILITY_OF_NEW_BUGS = 0.10F;
  private static final float PROBABILITY_OF_MORE_BUGS = 0.15F;

  private int sprayLevel;
  private float numberOfBugs;

  private Random random = new Random();

  SprayTracker() {
    numberOfBugs = 0;
    sprayLevel = 0;
  }

  private boolean hasBugs() {
    return numberOfBugs > 0;
  }

  // decrement spray-level every day,
  // calls bugs if spray level is low
  @Override
  public void step() {
    sprayLevel -= 1;
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

  // return status string
  @Override
  public String getStatus() {
    return hasBugs() ? "It has bugs. " : null;
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

  @Override
  public String getLevelCode() {
    return String.format("s%02.0f",numberOfBugs);
  }

}