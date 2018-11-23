class WaterTracker extends Tracker {

  // LEVEL LOGIC
  // One application adds the same amount as 8 days removes.
  // If the plant is at the ideal level,
  // it wall die after 3 applications at once and
  // it will die after 16 days without an application.

  private static final Tracker.Levels LEVELS = new Tracker.Levels(
      40.0,
      2.5, 8.0,
      20, 8.0
  );

  private static final Tracker.Zone[] ZONES =
      {
          // IDEAL + 4 applications  (minimum for zone)
          new Tracker.Zone(
              "+X", 100.0, false, true,
              new String[]{
                  "It has drowned. ",
                  "Your plant got so wet it developed a gnarly " +
                  "fungus that took over everything. ",
                  "There is so much water it suffocated. "
              }
          ),
          // IDEAL + 2 applications  (minimum for zone)
          new Tracker.Zone(
              "+2", 80.0, false, false,
              new String[]{
                  "The leaves are drooping quite badly. ",
                  "It's wilting quite a bit. "
              }
          ),
          // IDEAL + 1.25 applications  (minimum for zone)
          new Tracker.Zone(
              "+1", 65.0, false, false,
              new String[]

                  {
                      "The leaves are a little bit limp. ",
                      "It looks a little sad. "
                  }
          ),
          // IDEAL ZONE
          new Tracker.Zone(
          "±0",30.0, true, false,
                           new String[]

                               {
                                   "The leaves are perfectly perky. "
                               }
          ),
          // IDEAL + 8 days  (maximum for zone)
          new Tracker.Zone(
              "-1",10.0, false, false,
                           new String[]

                               {
                                   "The leaves are a little bit limp. "
                               }
          ),
          // IDEAL + 12 days  (maximum for zone)
          new Tracker.Zone(
              "-2",0.0, false, false,
                           new String[]

                               {
                                   "The leaves are drooping quite badly. "
                               }
          ),
          // IDEAL + 16 days  (maximum for zone)
          new Tracker.Zone(
              "-X",-1.0, false, true,
                           new String[]

                               {
                                   "There is nothing left of it but a dried-out husk. "
                               }
          )
      };

  WaterTracker() {
    super(LEVELS, ZONES);
  }

  @Override
  public String toString() {
    return super.toString("Water ");
  }

  @Override
  public String getLevelCode() {
    return super.getLevelCode("w");
  }

}
