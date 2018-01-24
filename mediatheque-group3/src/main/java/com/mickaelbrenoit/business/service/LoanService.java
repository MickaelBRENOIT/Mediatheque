package com.mickaelbrenoit.business.service;

import java.util.List;

import com.mickaelbrenoit.business.model.Loan;

public interface LoanService {
	Loan save(Loan entity);
    void delete(Long id);
    List<Loan> findAll();
    Loan findById(Long id);
    List<Loan> findAllLoansByUserId(Long idUser);
}
