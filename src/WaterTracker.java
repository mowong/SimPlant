class WaterTracker extends Tracker {

<<<<<<< HEAD
  // LEVEL LOGIC
  // One application adds the same amount as 8 days removes.
  // If the plant is at the ideal level,
  // it wall die after 3 applications at once and
  // it will die after 16 days without an application.

  private static final Tracker.Levels LEVELS = new Tracker.Levels(
      40.0,
      2.5, 8.0,
      20, 8.0
=======
  /**
   * ratio between increments and decrements
   */
  private static final double INC_DEC_RATIO = 8.0;

  /**
   * from ideal, number of increments required to kill
   */
  private static final double DEADLY_INCS = 3.0;

  /**
   * from ideal, number of decrements required to kill
   */
  private static final double DEADLY_DECS = 16.0;

  // derived values
  private static final double DECREMENT =
      (100 / DEADLY_INCS) / (INC_DEC_RATIO + DEADLY_DECS / DEADLY_INCS);
  private static final double INCREMENT = DECREMENT * INC_DEC_RATIO;
  private static final double IDEAL = DECREMENT * DEADLY_DECS;


  private static final Tracker.Levels LEVELS = new Tracker.Levels(
      IDEAL,
      DECREMENT, 8.0,
      INCREMENT, 8.0
>>>>>>> f4d3aa61ff78f0d4d34aa4b07044038f198c36da
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
<<<<<<< HEAD
              "+2", 80.0, false, false,
=======
              "+2", (2.0 / 3) * (100 - IDEAL) + IDEAL,
              false, false,
>>>>>>> f4d3aa61ff78f0d4d34aa4b07044038f198c36da
              new String[]{
                  "The leaves are drooping quite badly. ",
                  "It's wilting quite a bit. "
              }
          ),
          // IDEAL + 1.25 applications  (minimum for zone)
          new Tracker.Zone(
<<<<<<< HEAD
              "+1", 65.0, false, false,
=======
              "+1", (5.0 / 12) * (100 - IDEAL) + IDEAL,
              true, false,
>>>>>>> f4d3aa61ff78f0d4d34aa4b07044038f198c36da
              new String[]

                  {
                      "The leaves are a little bit limp. ",
                      "It looks a little sad. "
                  }
          ),
          // IDEAL ZONE
          new Tracker.Zone(
<<<<<<< HEAD
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
=======
              "±0", (3.0 / 4) * IDEAL,
              true, false,
              new String[]

                  {
                      "The leaves are perfectly perky. "
                  }
          ),
          // IDEAL + 8 days  (maximum for zone)
          new Tracker.Zone(
              "-1", (1.0 / 4) * IDEAL,
              true, false,
              new String[]

                  {
                      "The leaves are a little bit limp. "
                  }
          ),
          // IDEAL + 12 days  (maximum for zone)
          new Tracker.Zone(
              "-2", 0.0, false, false,
              new String[]

                  {
                      "The leaves are drooping quite badly. "
                  }
          ),
          // IDEAL + 16 days  (maximum for zone)
          new Tracker.Zone(
              "-X", -1.0, false, true,
              new String[]

                  {
                      "There is nothing left of it but a dried-out husk. "
                  }
>>>>>>> f4d3aa61ff78f0d4d34aa4b07044038f198c36da
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
