package htwsaar.ariadne.learcard.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Entity USER
 * @author Pamela Filipinski
 */
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    private String userEmail;

    @Column(nullable = false)
    private String userPassword;

    public User() {
        this.userName = "";
        this.userEmail = "";
        this.userPassword = "";
    }

    public User(String userName,
                String userEmail,
                String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


}
