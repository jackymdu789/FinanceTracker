package com.example.demo.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
@CrossOrigin("*")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/{accountId}")
    public ResponseEntity<Bill> addBill(@RequestBody Bill bill, @PathVariable String accountId) {
        bill.setAccount(accountId);
        return ResponseEntity.ok(billService.addBill(bill));
    }

    @GetMapping("/{accountId}")
    public List<Bill> getAllBills(@PathVariable String accountId) {
        return billService.getAllBills(accountId);
    }

    @PutMapping("/{id}/mark-as-paid")
    public ResponseEntity<Bill> markAsPaid(@PathVariable Long id) {
        return ResponseEntity.ok(billService.markAsPaid(id));
    }
}
