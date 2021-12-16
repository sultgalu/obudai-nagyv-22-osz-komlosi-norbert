package warehouse.app.webdomain;

import javax.validation.constraints.NotEmpty;

import warehouse.app.validation.PasswordConstraint;

public class LoginRequest {

  @NotEmpty(message = "Please enter a username")
  private final String username;
  @PasswordConstraint
  private final String password;

  public String getUsername() {
    return this.username;
  }

  public LoginRequest(@NotEmpty(message = "Please enter a username") String username, String password) {
    super();
    this.username = username;
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }
}
