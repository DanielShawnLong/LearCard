package htwsaar.ariadne.learcard.security.model;
/**
 * Made with tutorial: https://medium.com/swlh/spring-boot-security-jwt-hello-world-example-b479e457664c
 * @author TUTORIAL-Author: Rameez Shaikh / customized Pamela Filipinski
 */
import java.io.Serializable;

public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String userName;
    private String userPassword;


    public JwtRequest() {

    }

    public JwtRequest(String userName, String userPassword) {
        this.setUserName(userName);
        this.setUserPassword(userPassword);
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}