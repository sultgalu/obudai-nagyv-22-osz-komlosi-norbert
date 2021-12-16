package warehouse.app.transform;

import org.springframework.stereotype.Component;

import warehouse.app.webdomain.BoxView;
import warehouse.app.webdomain.NewBoxRequest;
import warehouse.persistence.entity.Box;
import warehouse.persistence.entity.Size;

@Component
public class BoxTransformer {
  public void transform(Box box, NewBoxRequest request) {
    box.setCategories(request.getCategories());
    box.setMaterials(request.getMaterials());

    Size size = new Size();
    String[] split = request.getSize().split("x");
    size.setX(Integer.valueOf(split[0]));
    size.setY(Integer.valueOf(split[1]));
    box.setSize(size);
  }

  public void transform(BoxView boxView, Box box) {
    boxView.setId(box.getId());
    boxView.setStorageRoomId(box.getStorageRoom().getId());
    boxView.setSize(box.getSize().getX() * box.getSize().getY());
    boxView.setCategories(box.getCategories());
    boxView.setMaterials(box.getMaterials());
  }
}
