package htwsaar.ariadne.learcard.security.model;
/**
 * Made with tutorial: https://medium.com/swlh/spring-boot-security-jwt-hello-world-example-b479e457664c
 * @author TUTORIAL-Author: Rameez Shaikh / customized Pamela Filipinski
 */
import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}