/**
* Created by richard.colvin on 26/05/2015.
*/
public class Datum {
  public final long timeOffset;
  public final int millies;
  public final String respCode;

  public Datum(final long timeOffset, final int millies, final String respCode) {
    this.timeOffset = timeOffset;
    this.millies = millies;
    this.respCode = respCode;
  }

  @Override
  public String toString() {
    return "Datum{" +
        "timeOffset=" + timeOffset +
        ", millies=" + millies +
        ", respCode='" + respCode + '\'' +
        '}';
  }
}
