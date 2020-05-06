package lv.maeteman.cleanmap.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "auth_token")
    private String authToken;

    @Column(name = "auth_token_valid_till")
    private LocalDateTime authTokenValidTill;


    public Client() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public LocalDateTime getAuthTokenValidTill() {
        return authTokenValidTill;
    }

    public void setAuthTokenValidTill(LocalDateTime authTokenValidTill) {
        this.authTokenValidTill = authTokenValidTill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return id == client.id &&
                Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(email, client.email) &&
                Objects.equals(password, client.password) &&
                Objects.equals(authToken, client.authToken) &&
                Objects.equals(authTokenValidTill, client.authTokenValidTill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, authToken, authTokenValidTill);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", authToken='" + authToken + '\'' +
                ", authTokenValidTill=" + authTokenValidTill +
                '}';
    }
}
