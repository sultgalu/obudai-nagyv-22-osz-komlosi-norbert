package warehouse.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import warehouse.service.WarehouseService;

@Component
public class App {
  @Autowired
  private WarehouseService service;

  public void play() {
    this.service.initializeData();

    boolean running = true;
    while (running) {
    }
  }

}
