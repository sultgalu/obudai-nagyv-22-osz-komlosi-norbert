package warehouse.persistence;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import warehouse.domain.Box;
import warehouse.domain.Category;
import warehouse.domain.Customer;
import warehouse.domain.Material;
import warehouse.domain.Size;
import warehouse.domain.StorageRoom;
import warehouse.domain.Warehouse;

@Repository
public class Data {
  final private static String dirPath = "";

  public Warehouse load() {
    List<String> srs, cs, bs, w;
    try {
      srs = Arrays.asList(Files.lines(Paths.get(dirPath + "storage.txt")).toArray(String[]::new));
      cs = Arrays.asList(Files.lines(Paths.get(dirPath + "customers.txt")).toArray(String[]::new));
      bs = Arrays.asList(Files.lines(Paths.get(dirPath + "boxes.txt")).toArray(String[]::new));
      w = Arrays.asList(Files.lines(Paths.get(dirPath + "warehouse.txt")).toArray(String[]::new));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    List<Customer> customers = loadCustomers(cs);
    return loadWarehouse(w, loadStorageRooms(srs), customers, loadBoxes(bs, customers));
  }

  private static List<StorageRoom> loadStorageRooms(List<String> data) {
    return new LinkedList<>(Arrays.asList(data.stream().map(line -> {
      return loadStorage(line);
    }).toArray(StorageRoom[]::new)));
  }

  private static List<Customer> loadCustomers(List<String> data) {
    return new LinkedList<>(Arrays.asList(data.stream().map(line -> {
      return loadCustomer(line);
    }).toArray(Customer[]::new)));
  }

  private static List<Box> loadBoxes(List<String> data, List<Customer> customers) {
    return new LinkedList<>(Arrays.asList(data.stream().map(line -> {
      return loadBox(line, customers);
    }).toArray(Box[]::new)));
  }

  private static StorageRoom loadStorage(String data) {
    String[] split = data.split(" ");
    String[] sizeS = split[1].split("x");
    Size size = new Size();
    size.setX(Integer.valueOf(sizeS[0]));
    size.setY(Integer.valueOf(sizeS[1]));

    StorageRoom result = new StorageRoom();
    result.setId(Long.valueOf(split[0]));
    result.setSize(size);
    result.setBoxes(new LinkedList<>());
    result.setFree(true);

    return result;
  }

  private static Customer loadCustomer(String data) {
    String[] split = data.split(" ");

    Customer result = new Customer();
    result.setId(Long.valueOf(split[0]));
    result.setUsername(split[1]);
    result.setPassword(split[2]);
    result.setStorageRooms(new ArrayList<>());

    return result;
  }

  private static Box loadBox(String data, List<Customer> customers) {
    String[] split = data.split(" ");
    Material[] mats = Arrays.asList(split[3].split("\\|")).stream().map(x -> Material.valueOf(x))
      .toArray(Material[]::new);
    Category[] cats = Arrays.asList(split[4].split("\\|")).stream().map(x -> Category.valueOf(x))
      .toArray(Category[]::new);

    Box result = new Box();
    result.setId(Long.valueOf(split[0]));
    result.setOwner(customers.stream().filter(x -> x.getId() == Long.valueOf(split[1])).findFirst().get());
    result.setSize(getSize(split[2]));
    result.setMaterials(Arrays.asList(mats));
    result.setCategories(Arrays.asList(cats));

    return result;
  }

  private static Warehouse loadWarehouse(List<String> data, List<StorageRoom> storageRooms, List<Customer> customers,
    List<Box> boxes) {
    Warehouse warehouse = new Warehouse();
    warehouse.setBoxes(boxes);
    warehouse.setStorageRooms(storageRooms);
    warehouse.setCustomers(customers);

    data.stream().forEach(line -> {
      String[] split = line.split(" ");
      StorageRoom sr = storageRooms.stream().filter(x -> x.getId() == Long.valueOf(split[0])).findFirst().get();
      Customer c = customers.stream().filter(x -> x.getId() == Long.valueOf(split[1])).findFirst().get();

      sr.setFree(false);
      sr.setOwner(c);
      c.getStorageRooms().add(sr);

      Arrays.asList(split[2].split("\\|")).stream().map(x -> Long.valueOf(x)).forEach(x -> {
        Box box = boxes.stream().filter(y -> y.getId() == x).findFirst().get();
        box.setStorageRoom(sr);
        sr.getBoxes().add(box);
      });
    });

    return warehouse;
  }

  public void save(Warehouse w) {
    try (FileWriter writer = new FileWriter("warehouse.txt")) {
      List<String> wLines = getWarehouseLines(w);
      for (String line : wLines) {
        writer.write(line + System.lineSeparator());
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    try (FileWriter writer = new FileWriter("boxes.txt")) {
      List<String> bLines = getBoxesLines(w.getBoxes());
      for (String line : bLines) {
        writer.write(line + System.lineSeparator());
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static List<String> getWarehouseLines(Warehouse w) {
    return Arrays.asList(w.getStorageRooms().stream().map(sr -> {
      String line = String.format("%d %d", sr.getId(), sr.getOwner().getId());
      StringBuilder boxIds = new StringBuilder();
      sr.getBoxes().stream().forEach(b -> {
        boxIds.append(b.getId() + " ");
      });
      return line + " " + boxIds.toString().trim().replace(' ', '|');
    }).toArray(String[]::new));
  }

  private static List<String> getBoxesLines(List<Box> boxes) {
    return Arrays.asList(boxes.stream().map(box -> {
      String line = String.format("%d %d %dx%d", box.getId(), box.getOwner().getId(), box.getSize().getX(), box.getSize().getY());
      StringBuilder mats = new StringBuilder(), cats = new StringBuilder();

      box.getMaterials().stream().forEach(m -> {
        mats.append(m.name() + " ");
      });
      box.getCategories().stream().forEach(c -> {
        cats.append(c.name() + " ");
      });

      return line + " " + mats.toString().trim().replace(' ', '|')
        + " " + cats.toString().trim().replace(' ', '|');
    }).toArray(String[]::new));
  }

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
