package users.domain.model;

import java.util.UUID;

public class UserModel {
    private UUID id;
    private String username;
    private String email;

    // Constructors
    public UserModel() {}

    public UserModel(UUID id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
