import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by richard.colvin on 26/05/2015.
 */
public class ResultWriter {
  final PrintWriter pw;

  ResultWriter(final PrintWriter pw) {
    this.pw = pw;
  }

  void write(Map<Long, Res> map) {
    for (Long aLong : map.keySet()) {
      Res r = map.get(aLong);

      pw.println(aLong + ", " + r.ave + ", " + r.count);
    }

  }
}
