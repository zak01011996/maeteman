package lv.maeteman.cleanmap.controller;

import lv.maeteman.cleanmap.dto.ErrorMsg;
import lv.maeteman.cleanmap.dto.Login;
import lv.maeteman.cleanmap.dto.Token;
import lv.maeteman.cleanmap.model.Client;
import lv.maeteman.cleanmap.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/api/v1/client")
    public ResponseEntity<?> register(@RequestBody Client client) {
        try {
            Client resClient = clientService.registerClient(client);

            return new ResponseEntity<>(resClient, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ErrorMsg(HttpStatus.FORBIDDEN, ex.getMessage()), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/api/v1/client/login")
    public ResponseEntity<?> login(@RequestBody Login credentials) {
        try {
           String token = clientService.login(credentials.getEmail(), credentials.getPassword());

           return new ResponseEntity<>(new Token(token), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ErrorMsg(HttpStatus.FORBIDDEN, ex.getMessage()), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/api/v1/client/logout")
    public ResponseEntity<?> logout(@RequestHeader("AUTH_TOKEN") String authToken) {
        clientService.logout(authToken);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/v1/client/info")
    public ResponseEntity<?> info(@RequestHeader("AUTH_TOKEN") String authToken) {
        Client client;
        try {
            client = clientService.getClientByToken(authToken);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ErrorMsg(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }
}
