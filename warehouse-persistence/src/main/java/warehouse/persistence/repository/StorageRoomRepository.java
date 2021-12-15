package warehouse.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import warehouse.persistence.entity.StorageRoom;

public interface StorageRoomRepository extends CrudRepository<StorageRoom, Long> {

}
