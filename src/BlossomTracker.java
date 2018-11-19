public class BlossomTracker implements TrackerInterface {

  private PlantModel plant;
  private int stepsHealthy = 0;

  BlossomTracker(PlantModel plant) {
    this.plant = plant;
  }

  @Override
  public void step() {
    if ( plant.isHealthy() ) stepsHealthy++;
  }

  @Override
  public void apply() {

  }

  @Override
  public String getStatus() {
    if ( isBlooming() )
      return "It is blooming (pretty).";
    else
      return null;
  }

  @Override
  public boolean isHealthy() {
    return true; // wont make plant unhealthy
  }

  @Override
  public boolean isDead() {
    return false; // wont make plant dead
  }

  @Override
  public String getCauseOfDeath() {
    return null; // wont make plant dead
  }

  private boolean isBlooming() {
    return false;
  }
}
