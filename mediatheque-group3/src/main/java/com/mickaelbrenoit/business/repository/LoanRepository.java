package com.mickaelbrenoit.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mickaelbrenoit.business.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

}
