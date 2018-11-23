public class FeedTracker extends Tracker {

  // LEVEL LOGIC
  // One application adds the same amount as 33 days removes.
  // If the plant is at the ideal level,
  // it wall die after 3 applications at once and
  // it will die after 66 days without an application.

  private static final Tracker.Levels LEVELS = new Tracker.Levels(
      40.0,
      20.0 / 33.0, 8.0,
      20.0, 8.0
  );

  private static final Tracker.Zone[] ZONES =
      {
          new Tracker.Zone(
              "+X",100.0, false, true,
              new String[]{
                  "It has burnt to a crisp. ",
                  "The leaves are all shrivelled up and burnt. "
              }
          ),
          new Tracker.Zone(
              "+2",80.0, false, false,
              new String[]{
                  "Some leaves near the top are shrivelled up. " +
                  "The tips of some others are yellowing"
              }
          ),
          new Tracker.Zone(
              "+1",65.0, true, false,
              new String[]{
                  "Some of the top-most leaves are looking a little yellow. " +
                  "The tips of them are also a little burnt. "
              }
          ),
          new Tracker.Zone(
              "±0",30.0, true, false,
              new String[]{
                  "The leaves are a nice deep green. ",
                  "The leaves look so healthy!"
              }
          ),
          new Tracker.Zone(
              "-1",10.0, true, false,
              new String[]{
                  "Some of the bottom leaves are turning yellow. ",
                  "There's some yellowing on the lower leaves"
              }
          ),
          new Tracker.Zone(
              "-2",0.0, false, false,
              new String[]{
                  "Many leaves are yellow. " +
                  "Some of them are drying up and falling off. "
              }
          ),
          new Tracker.Zone(
              "-X",-1.0, false, true,
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
