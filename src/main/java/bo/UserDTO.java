package bo;

public class UserDTO {
    private int id;
    private String username;
    private String email;
    private Role role;

    public UserDTO(int id, String username, String email, Role role){
        this.id = id;
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

    public int getId() {
        return id;
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
