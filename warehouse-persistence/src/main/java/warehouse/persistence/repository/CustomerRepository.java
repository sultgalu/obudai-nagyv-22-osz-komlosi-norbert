package warehouse.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import warehouse.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
