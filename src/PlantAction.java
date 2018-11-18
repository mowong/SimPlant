public enum PlantAction {
  WATER("Your plant has been watered."),
  FEED("Your plant has been fertilized."),
  SPRAY("Your plant has been sprayed with pesticides."),
  BLOSSOM("You have somehow made your plant bloom."); // This shouldn't happen.

  private String feedback;

  PlantAction(String feedback) {
    this.feedback = feedback;
  }

  public String getFeedback() {
    return feedback;
  }
}
