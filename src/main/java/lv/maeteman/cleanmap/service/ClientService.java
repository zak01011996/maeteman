package lv.maeteman.cleanmap.service;

import lv.maeteman.cleanmap.model.Client;
import lv.maeteman.cleanmap.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public String login(String email, String password) {
        Optional<Client> userOptional = clientRepository.login(email, hashWith256(password));

        if (!userOptional.isPresent()) {
            throw new RuntimeException("Wrong email or password!");
        }

        String token = UUID.randomUUID().toString();
        LocalDateTime validTill = LocalDateTime.now().plusDays(360);

        Client client = userOptional.get();
        client.setAuthToken(token);
        client.setAuthTokenValidTill(validTill);

        clientRepository.save(client);

        return token;
    }

    public void logout(String authToken) {
        Optional<Client> userOptional = clientRepository.findUserByAuthToken(authToken);

        userOptional.ifPresent(client -> {
            client.setAuthTokenValidTill(null);
            client.setAuthToken(null);

            clientRepository.save(client);
        });
    }


    public Client getClientByToken(String authToken) {
        Optional<Client> clientOptional = clientRepository.findUserByAuthToken(authToken);

        if (!clientOptional.isPresent()) {
            throw new RuntimeException("Client not found with provided token");
        }

        return clientOptional.get();
    }

    public Client registerClient(Client client) {
        client.setPassword(hashWith256(client.getPassword()));
        return clientRepository.saveAndFlush(client);
    }

    private String hashWith256(String textToHash) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] byteOfTextToHash = textToHash.getBytes(StandardCharsets.UTF_8);
            byte[] hashedByetArray = digest.digest(byteOfTextToHash);
            String encoded = Base64.getEncoder().encodeToString(hashedByetArray);
            return encoded;
        } catch (NoSuchAlgorithmException ex) {
            return "";
        }
    }
}
