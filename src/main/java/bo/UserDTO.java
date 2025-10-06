package bo;

public class UserDTO {
    private String username;
    private String email;
    private Role role;

    public UserDTO(String username, String email, Role role){
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getRole() {
        if (this.role == null) {
            return "";
        }
        return this.role.name();
    }
}
