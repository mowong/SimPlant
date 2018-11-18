public class BlossomTracker implements TrackerInterface {

  private PlantModel plant;
  private int stepsHealthy = 0;

  BlossomTracker(PlantModel plant) {
    this.plant = plant;
  }

  @Override
  public void step() {

  }

  @Override
  public void apply() {

  }

  @Override
  public String getStatus() {
    return null;
  }

  @Override
  public boolean isHealthy() {
    return false;
  }

  @Override
  public boolean isDead() {
    return false;
  }

  @Override
  public String getCauseOfDeath() {
    return null;
  }
}
