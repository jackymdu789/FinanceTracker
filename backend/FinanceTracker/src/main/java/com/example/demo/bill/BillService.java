package com.example.demo.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public Bill addBill(Bill bill) {
        return billRepository.save(bill);
    }

    public List<Bill> getAllBills(String accountId) {
        return billRepository.findAllByAccountAccountId(accountId);
    }

    public Bill markAsPaid(Long id) {
        Bill bill = billRepository.findById(id).orElseThrow(() -> new RuntimeException("Bill not found"));
        bill.setPaid(true);
        return billRepository.save(bill);
    }

    @Scheduled(cron = "0 0 9 * * ?") // Daily at 9 AM
    public void sendReminders() {
        List<Bill> bills = billRepository.findByDueDateBeforeAndIsPaidFalse(LocalDate.now().plusDays(1));
        // Logic to send reminders
        for (Bill bill : bills) {
            // Send reminder logic (e.g., email or SMS)
            System.out.println("Reminder: Bill " + bill.getName() + " is due on " + bill.getDueDate());
        }
    }
}
