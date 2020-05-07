package fr.ing.interview.bankaccountkata.rest;

import fr.ing.interview.bankaccountkata.dto.BankDto;
import fr.ing.interview.bankaccountkata.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author kso
 */
@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<BankDto>> getBanks() {
        return ResponseEntity.ok(bankService.getBanks());
    }
}
