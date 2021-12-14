package warehouse.persistence;

import java.util.ArrayList;
import java.util.Arrays;
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
  public Warehouse load() {
    Warehouse w = new Warehouse();
    w.boxes = getBoxes();
    w.storageRooms = getStorageRooms();
    w.customers = getCustomers();

    addRoomAndBoxesToCustomer(w, 0, 0, Arrays.asList(0l));
    addRoomAndBoxesToCustomer(w, 1, 1, Arrays.asList(0l));
    addRoomAndBoxesToCustomer(w, 2, 2, Arrays.asList(0l));

    return w;
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

  private static Size getSize(int x, int y) {
    Size size = new Size();
    size.x = x;
    size.y = y;
    return size;
  }
}
