/**
 * Created by richard.colvin on 26/05/2015.
 */
public class Res {
  public int count = 0;
  public float ave = 0;

  public void add(int value) {
    ave = (ave * count + value) / (count + 1);
    count++;
  }
}
