import java.util.Random;

public class BloomTracker implements TrackerInterface {

  /**
   * Chance of plant starting to bloom increments this amount every day.
   */
  private static final Double STARTING_CHANCE_MULTIPLIER = 0.047619;
  private static final Double STARTING_CHANCE_EXPONENT = 6.0;

  /**
   * Chance of plant stopping to bloom increments this amount every day.
   */
  private static final Double STOPPING_CHANCE_MULTIPLIER = 0.071429;
  private static final Double STOPPING_CHANCE_EXPONENT = 4.0;

  /**
   * Comments appended to status message. One is chosen at random.
   */
  private static final String[] COMMENTS = {
      "So pretty! ",
      "Smells great! "
  };

  private final Plant plant;
  private int stepsHealthy;
  private int stepsBlooming; // -1 if not blooming

  BloomTracker(Plant plant) {
    this.plant = plant;
    this.stepsHealthy = 0;
    this.stepsBlooming = -1;
  }

  @Override
  public void step() {

    if ( plant.isHealthy() )
      stepsHealthy++;
    else
      stepsHealthy = 0;

    if ( isBlooming() )
      stepsBlooming++;

    updateBlooming();
  }

  private void updateBlooming() {

    if ( isBlooming() ) { // blooming

      // plant stops blooming if it has become unhealthy
      // also has increasing chance to stop every day

      double chance = Math.pow(
          STOPPING_CHANCE_MULTIPLIER * stepsBlooming,
          STOPPING_CHANCE_EXPONENT
      );

      if ( !plant.isHealthy() || Math.random() < chance )
        stepsBlooming = -1;

    } else { // not blooming

      double chance = Math.pow(
          STARTING_CHANCE_MULTIPLIER * stepsHealthy,
          STARTING_CHANCE_EXPONENT
      );

      // plant has a increasing chance to start blooming every day
      if ( Math.random() < chance )
        stepsBlooming = 0;

    } // end if ( isBlooming() )

  }

  @Override
  public void apply() {
    throw new UnsupportedOperationException("Cannot apply bloom.");
  }

  @Override
  public String getStatus() {
    if ( isBlooming() )
      return "It has been blooming for " +
             (stepsBlooming == 0 ?
                  "less than a " + Plant.STEP_STRING :
                  stepsBlooming + " " + Plant.STEP_STRING +
                  (stepsBlooming == 1 ? "" : "s")
             ) + ". " +
             COMMENTS[new Random().nextInt(COMMENTS.length)];
    else
      return null; // no status message if not blooming
  }

  @Override
  public boolean isHealthy() {
    return true; // will never make plant unhealthy
  }

  @Override
  public boolean isDead() {
    return false; // will never make plant dead
  }

  @Override
  public String getCauseOfDeath() {
    return null; // will never make plant dead
  }

  private boolean isBlooming() {
    return stepsBlooming >= 0;
  }

  public String getLevelCode() {
    return String.format("b%02d,h%02d", stepsBlooming, stepsHealthy);
  }
}
