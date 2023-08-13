public class User {
    private int id; // The user ID
    private String username; // The user's username
    private String email; // The user's email address

    /**
     * Creates a new `User` object with the specified ID, username, and email.
     *
     * @param id The user ID
     * @param username The user's username
     * @param email The user's email address
     */
    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    /**
     * Creates a new `User` object with default values for the ID, username, and email.
     */
    public User() {
        this.id = 0;
        this.username = "";
        this.email = "";
    }

    /**
     * Gets the user ID.
     *
     * @return The user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user ID.
     *
     * @param id The new user ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user's username.
     *
     * @return The user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username The new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's email address.
     *
     * @return The user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email The new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }
}