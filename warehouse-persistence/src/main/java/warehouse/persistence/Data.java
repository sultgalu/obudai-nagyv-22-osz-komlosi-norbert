package warehouse.persistence;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
  public Warehouse load2() {
    Warehouse w = new Warehouse();
    w.boxes = getBoxes();
    w.storageRooms = getStorageRooms();
    w.customers = getCustomers();

    addRoomAndBoxesToCustomer(w, 0, 0, Arrays.asList(0l));
    addRoomAndBoxesToCustomer(w, 1, 1, Arrays.asList(0l));
    addRoomAndBoxesToCustomer(w, 2, 2, Arrays.asList(0l));

    return w;
  }

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
    size.x = Integer.valueOf(sizeS[0]);
    size.y = Integer.valueOf(sizeS[1]);

    StorageRoom result = new StorageRoom();
    result.id = Long.valueOf(split[0]);
    result.size = size;
    result.boxes = new LinkedList<>();
    result.isFree = true;

    return result;
  }

  private static Customer loadCustomer(String data) {
    String[] split = data.split(" ");

    Customer result = new Customer();
    result.id = Long.valueOf(split[0]);
    result.username = split[1];
    result.password = split[2];
    result.storageRooms = new ArrayList<>();

    return result;
  }

  private static Box loadBox(String data, List<Customer> customers) {
    String[] split = data.split(" ");
    Material[] mats = Arrays.asList(split[3].split("\\|")).stream().map(x -> Material.valueOf(x))
      .toArray(Material[]::new);
    Category[] cats = Arrays.asList(split[4].split("\\|")).stream().map(x -> Category.valueOf(x))
      .toArray(Category[]::new);

    Box result = new Box();
    result.id = Long.valueOf(split[0]);
    result.owner = customers.stream().filter(x -> x.id == Long.valueOf(split[1])).findFirst().get();
    result.size = getSize(split[2]);
    result.materials = Arrays.asList(mats);
    result.categories = Arrays.asList(cats);

    return result;
  }

  private static Warehouse loadWarehouse(List<String> data, List<StorageRoom> storageRooms, List<Customer> customers,
    List<Box> boxes) {
    Warehouse warehouse = new Warehouse();
    warehouse.boxes = boxes;
    warehouse.storageRooms = storageRooms;
    warehouse.customers = customers;

    data.stream().forEach(line -> {
      String[] split = line.split(" ");
      StorageRoom sr = storageRooms.stream().filter(x -> x.id == Long.valueOf(split[0])).findFirst().get();
      Customer c = customers.stream().filter(x -> x.id == Long.valueOf(split[1])).findFirst().get();

      sr.isFree = false;
      sr.owner = c;
      c.storageRooms.add(sr);

      Arrays.asList(split[2].split("\\|")).stream().map(x -> Long.valueOf(x)).forEach(x -> {
        Box box = boxes.stream().filter(y -> y.id == x).findFirst().get();
        box.storageRoom = sr;
        sr.boxes.add(box);
      });
    });

    return warehouse;
  }

  public void save(Warehouse w) {

  }

  private static void addRoomAndBoxesToCustomer(Warehouse w, long cId, long srId, List<Long> bIds) {
    Customer c = w.customers.stream().filter(x -> x.id == cId).findFirst().get();
    StorageRoom sr = w.storageRooms.stream().filter(x -> x.id == srId).findFirst().get();
    c.storageRooms.add(sr);

    List<Box> bs = w.boxes.stream().filter(b -> bIds.contains(b.id)).collect(Collectors.toList());
    bs.forEach(b -> b.owner = c);

    sr.owner = c;
    sr.boxes.addAll(bs);
  }

  private static List<StorageRoom> getStorageRooms() {
    List<StorageRoom> srs = new ArrayList<>();

    int id = 0;

    srs.add(createStorageRoom(id++, getSize(1000, 1000)));
    srs.add(createStorageRoom(id++, getSize(2000, 43334)));
    srs.add(createStorageRoom(id++, getSize(321312, 2312)));

    return srs;
  }

  private static StorageRoom createStorageRoom(long id, Size size) {
    StorageRoom sr = new StorageRoom();
    sr.id = id;
    sr.size = size;
    sr.boxes = new ArrayList<>();
    sr.isFree = true;

    return sr;
  }

  private static List<Customer> getCustomers() {
    List<Customer> cs = new ArrayList<>();

    int id = 0;

    cs.add(createCustomer(id++, "Sanyi", "asd"));
    cs.add(createCustomer(id++, "Márk", "asd"));
    cs.add(createCustomer(id++, "Dzsi", "asd"));

    return cs;
  }

  private static Customer createCustomer(long id, String u, String p) {
    Customer c = new Customer();
    c.id = id;
    c.username = u;
    c.password = p;
    c.storageRooms = new ArrayList<>();

    return c;
  }

  private static List<Box> getBoxes() {
    List<Box> boxes = new ArrayList<>();

    int id = 0;
    boxes.add(createBox(id++, getSize(400, 400), Arrays.asList(Material.GLASS), Arrays.asList(Category.FRAGILE)));
    boxes.add(createBox(id++, getSize(100, 200), Arrays.asList(Material.METAL), Arrays.asList(Category.HEAVY)));
    boxes.add(createBox(id++, getSize(10, 90), Arrays.asList(Material.PAPER), Arrays.asList(Category.LIGHT)));

    return boxes;
  }

  private static Box createBox(long id, Size size, List<Material> mats, List<Category> cats) {
    Box box = new Box();
    box.id = id;
    box.size = size;
    box.materials = mats;
    box.categories = cats;

    return box;
  }

  private static Size getSize(String str) {
    String[] split = str.split("x");
    return getSize(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
  }

  private static Size getSize(int x, int y) {
    Size size = new Size();
    size.x = x;
    size.y = y;
    return size;
  }
}
