package warehouse.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import warehouse.persistence.entity.Customer;
import warehouse.persistence.repository.CustomerRepository;

@Service
public class CustomerDetailsService implements UserDetailsService {
  @Autowired
  private CustomerRepository customerRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Customer c = this.customerRepo.findByUsername(username);
    if (c == null) {
      throw new UsernameNotFoundException(username);
    }
    return new CustomerPrincipal(c);
  }
}
