package at.fhtw.bic.carsharing.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private Long id;    // managed by UserRepository

    private String username;
    private String password;
    private String passwordHash;    // managed by registration
    private String token;   // managed by login/logout

    private String firstname;
    private String lastname;
    private int age;
    private String drivingLicenseNr;
    private String creditCardNr;

    @JsonProperty("isRoleFleetManager") // This is necessary to correctly serialize/deserialize booleans with JSON
    private boolean isRoleFleetManager;
}
