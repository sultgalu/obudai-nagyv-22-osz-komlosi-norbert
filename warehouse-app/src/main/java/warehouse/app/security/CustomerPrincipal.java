package warehouse.app.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import warehouse.persistence.entity.Customer;

public class CustomerPrincipal implements UserDetails {

  private static final long serialVersionUID = 1L;
  final private Customer customer;

  public CustomerPrincipal(Customer customer) {
    super();
    this.customer = customer;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.asList(Role.ROLE_CUSTOMER);
  }

  @Override
  public String getPassword() {
    return this.customer.getPassword();
  }

  @Override
  public String getUsername() {

    return this.customer.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
