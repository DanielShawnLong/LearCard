package htwsaar.ariadne.learcard.security.model;
/**
 * Made with tutorial: https://medium.com/swlh/spring-boot-security-jwt-hello-world-example-b479e457664c
 * @author TUTORIAL-Author: Rameez Shaikh / customized Pamela Filipinski
 */


public class UserDTO {

    private String userName;

    private String userPassword;

    private String userEmail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}