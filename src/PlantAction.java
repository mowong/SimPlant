public enum PlantAction {
  CHECK(""), // No additional feedback for checking status
  WATER("Water applied. "),
  FEED("Fertilizer applied. "),
  SPRAY("Pesticides applied. "),
  BLOSSOM("You have somehow made your plant bloom. "); // This shouldn't happen.

  private String feedback;

  PlantAction(String feedback) {
    this.feedback = feedback;
  }

  public String getFeedback() {
    return feedback;
  }
}
