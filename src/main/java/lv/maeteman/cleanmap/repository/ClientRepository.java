package lv.maeteman.cleanmap.repository;

import lv.maeteman.cleanmap.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT u FROM Client u WHERE u.email = ?1 AND u.password = ?2")
    Optional<Client> login(String email, String password);

    Optional<Client> findUserByAuthToken(String authToken);
}
