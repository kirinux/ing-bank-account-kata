package fr.ing.interview.controller;

import fr.ing.interview.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping("create-account")
    public ResponseEntity<Long> create() {
        Long account = adminService.createAccount();
        return ResponseEntity.ok(account);
    }

}
