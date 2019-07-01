package info.bankaspace.serviceproviders;

/**
 * Created by DC on 17-03-2018.
 */

public class User {
    private String username, email, gender; int id;

    public User(int id, String username, String email, String gender) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.gender = gender;


    }
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }
}
