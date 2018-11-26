public class FeedTracker extends Tracker {

<<<<<<< HEAD
  // LEVEL LOGIC
  // One application adds the same amount as 33 days removes.
  // If the plant is at the ideal level,
  // it wall die after 3 applications at once and
  // it will die after 66 days without an application.
=======
  /**
   * ratio between increments and decrements
   */
  private static final double INC_DEC_RATIO = 33.0;

  /**
   * from ideal, number of increments required to kill
   */
  private static final double DEADLY_INCS = 3.0;

  /**
   * from ideal, number of decrements required to kill
   */
  private static final double DEADLY_DECS = 66.0;

  // derived values
  private static final double DECREMENT =
      (100 / DEADLY_INCS) / (INC_DEC_RATIO + DEADLY_DECS / DEADLY_INCS);
  private static final double INCREMENT = DECREMENT * INC_DEC_RATIO;
  private static final double IDEAL = DECREMENT * DEADLY_DECS;
>>>>>>> f4d3aa61ff78f0d4d34aa4b07044038f198c36da

  private static final Tracker.Levels LEVELS = new Tracker.Levels(
      40.0,
      20.0 / 33.0, 8.0,
      20.0, 8.0
  );

  private static final Tracker.Zone[] ZONES =
      {
          new Tracker.Zone(
<<<<<<< HEAD
              "+X",100.0, false, true,
=======
              "+X", 100.0, false, true,
>>>>>>> f4d3aa61ff78f0d4d34aa4b07044038f198c36da
              new String[]{
                  "It has burnt to a crisp. ",
                  "The leaves are all shrivelled up and burnt. "
              }
          ),
          new Tracker.Zone(
<<<<<<< HEAD
              "+2",80.0, false, false,
=======
              "+2", (2.0 / 3) * (100 - IDEAL) + IDEAL,
              false, false,
>>>>>>> f4d3aa61ff78f0d4d34aa4b07044038f198c36da
              new String[]{
                  "Some leaves near the top are shrivelled up. " +
                  "The tips of some others are yellowing"
              }
          ),
          new Tracker.Zone(
<<<<<<< HEAD
              "+1",65.0, true, false,
=======
              "+1", (5.0 / 12) * (100 - IDEAL) + IDEAL,
              true, false,
>>>>>>> f4d3aa61ff78f0d4d34aa4b07044038f198c36da
              new String[]{
                  "Some of the top-most leaves are looking a little yellow. " +
                  "The tips of them are also a little burnt. "
              }
          ),
          new Tracker.Zone(
<<<<<<< HEAD
              "±0",30.0, true, false,
=======
              "±0", (3.0 / 4) * IDEAL,
              true, false,
>>>>>>> f4d3aa61ff78f0d4d34aa4b07044038f198c36da
              new String[]{
                  "The leaves are a nice deep green. ",
                  "The leaves look so healthy!"
              }
          ),
          new Tracker.Zone(
<<<<<<< HEAD
              "-1",10.0, true, false,
=======
              "-1", (1.0 / 4) * IDEAL,
              true, false,
>>>>>>> f4d3aa61ff78f0d4d34aa4b07044038f198c36da
              new String[]{
                  "Some of the bottom leaves are turning yellow. ",
                  "There's some yellowing on the lower leaves"
              }
          ),
          new Tracker.Zone(
<<<<<<< HEAD
              "-2",0.0, false, false,
=======
              "-2", 0.0, false, false,
>>>>>>> f4d3aa61ff78f0d4d34aa4b07044038f198c36da
              new String[]{
                  "Many leaves are yellow. " +
                  "Some of them are drying up and falling off. "
              }
          ),
          new Tracker.Zone(
<<<<<<< HEAD
              "-X",-1.0, false, true,
=======
              "-X", -1.0, false, true,
>>>>>>> f4d3aa61ff78f0d4d34aa4b07044038f198c36da
              new String[]{
                  "The leaves have all brown and crinkly. ",
                  "The leaves are falling off. "
              }
          )
      };

  FeedTracker() {
    super(LEVELS, ZONES);
  }

  @Override
  public String toString() {
    return super.toString("Feed ");
  }

  @Override
  public String getLevelCode() {
    return super.getLevelCode("f");
  }

}
