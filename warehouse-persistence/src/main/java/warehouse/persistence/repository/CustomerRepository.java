package warehouse.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import warehouse.persistence.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
  Customer findByUsername(String username);
}
