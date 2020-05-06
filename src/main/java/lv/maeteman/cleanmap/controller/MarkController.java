package lv.maeteman.cleanmap.controller;

import lv.maeteman.cleanmap.dto.ErrorMsg;
import lv.maeteman.cleanmap.model.Client;
import lv.maeteman.cleanmap.model.Mark;
import lv.maeteman.cleanmap.service.ClientService;
import lv.maeteman.cleanmap.service.MarkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MarkController {

    private ClientService clientService;
    private MarkService markService;

    public MarkController(ClientService clientService, MarkService markService) {
        this.clientService = clientService;
        this.markService = markService;
    }

    @GetMapping("/api/v1/mark")
    public List<Mark> getMarks() {
        return markService.fetchAll();
    }

    @PostMapping("/api/v1/mark")
    public ResponseEntity<?> saveMark(
            @RequestHeader("AUTH_TOKEN") String authToken,
            @RequestBody Mark mark
    ) {
        Client client;
        try {
            client = clientService.getClientByToken(authToken);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ErrorMsg(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            mark.setClientId(client.getId());
            Mark resMark = markService.saveMark(mark);
            return new ResponseEntity<>(resMark, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ErrorMsg(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
