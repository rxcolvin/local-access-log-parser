import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by richard.colvin on 01/05/2015.
 */
public class Main {

  static SimpleDateFormat df = new SimpleDateFormat("[dd/MMM/yyyy:hh:mm:ss");


  public static void main(String[] args) {

    if (args.length < 3) {
      println("usage [TBD]");
      System.exit(1);
    }

    String csvName = args[0];
    String csv2Name = args[1];

    File csv = new File(csvName);
    File csv2 = new File(csv2Name);
    try {
      PrintWriter pw = new PrintWriter(csv);
      PrintWriter pw2 = new PrintWriter(csv2);
      ResultProcessor rp = new ResultProcessor(pw);
      ResultWriter rw = new ResultWriter(pw2);

      Parser p = new Parser();

      for (int i = 2; i < args.length; i++) {
        File file = new File(args[i]);

        if (file.exists()) {
          LineNumberReader lnr = null;
          try {
            lnr = new LineNumberReader(new FileReader(file));
            String line = lnr.readLine();
            while (line != null) {
              rp.accept(p.accept(line));
              line = lnr.readLine();
            }
          } catch (Exception e) {
            e.printStackTrace();
          } finally {
            try {
              lnr.close();
            } catch (IOException e) {
              e.printStackTrace();
            }

          }
        }
      }
      pw.close();
      rw.write(rp.map);
      pw2.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    }

  }

  private static void println(final String s) {
    System.out.println(s);
  }

  static class ResultProcessor {
    private final PrintWriter pw;

    private long startTime = 0;
    public final Map<Long, Res> map = new TreeMap<Long, Res>();


    ResultProcessor(final PrintWriter pw) {
      this.pw = pw;

    }

    void accept(Datum d) {
      if (startTime == 0) {
        startTime = d.timeOffset;
      }
      if (d.respCode.equals("200")) {
        final long key = d.timeOffset - startTime;
        pw.println("" + key + ", " + d.millies);
        map.computeIfAbsent(key, aLong -> new Res()).add(d.millies);
      } else {
        println("Error:" + d);
      }
    }




  }

  static class Res {
    public int count = 0;
    public float ave = 0;

    void add(int value) {
      ave = (ave * count + value) / (count + 1);
      count++;
    }
  }

  static class ResultWriter {
    final PrintWriter pw;

    ResultWriter(final PrintWriter pw) {
      this.pw = pw;
    }

    void write(Map<Long, Res> map ) {
      for (Long aLong : map.keySet()) {
        Res r = map.get(aLong);

        pw.println(aLong + ", " + r.ave + ", " + r.count);
      }

    }
  }
}