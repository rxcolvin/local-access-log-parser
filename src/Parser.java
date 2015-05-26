import java.util.Date;

/**
* Created by richard.colvin on 26/05/2015.
*/
public class Parser {

  Datum accept(String line) {
    String[] items = line.split(" ");
    try {
      Date timeStamp = Main.df.parse(items[3]);
      String respCode = items[8];
      int millis = Integer.parseInt(items[10]);
      return new Datum(timeStamp.getTime(), millis, respCode);
    } catch (Exception e) {
      System.out.println(line);
      e.printStackTrace();
    }
    return null;
  }

}
