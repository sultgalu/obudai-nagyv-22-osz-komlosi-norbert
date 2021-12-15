package warehouse.persistence;

import org.springframework.stereotype.Repository;

import warehouse.persistence.entity.Size;

@Repository
public class Data {
  final private static String dirPath = "";

  private static Size getSize(String str) {
    String[] split = str.split("x");
    return getSize(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
  }

  private static Size getSize(int x, int y) {
    Size size = new Size();
    size.setX(x);
    size.setY(y);
    return size;
  }
}
