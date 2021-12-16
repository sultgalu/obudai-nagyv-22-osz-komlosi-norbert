package warehouse.webdomain;

import javax.validation.constraints.NotEmpty;

import warehouse.app.validation.PasswordConstraint;

public class LoginRequest {

  @NotEmpty(message = "Please enter a username")
  private String username;
  @PasswordConstraint
  private String password;

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }
}
