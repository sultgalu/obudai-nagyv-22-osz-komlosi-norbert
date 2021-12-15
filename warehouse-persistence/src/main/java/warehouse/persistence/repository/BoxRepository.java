package warehouse.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import warehouse.persistence.entity.Box;

public interface BoxRepository extends CrudRepository<Box, Long> {

}
