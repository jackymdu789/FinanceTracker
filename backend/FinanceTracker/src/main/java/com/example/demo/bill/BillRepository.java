package com.example.demo.bill;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByDueDateBeforeAndIsPaidFalse(LocalDate dueDate);

    List<Bill> findAllByAccountAccountId(String accountId);
}
