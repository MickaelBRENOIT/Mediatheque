package com.mickaelbrenoit.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mickaelbrenoit.business.model.Loan;
import com.mickaelbrenoit.business.repository.LoanRepository;
import com.mickaelbrenoit.business.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired
	private LoanRepository loanRepository;

	@Override
	public Loan save(Loan entity) {
		return loanRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		loanRepository.delete(id);
	}

	@Override
	public List<Loan> findAll() {
		return (List<Loan>) loanRepository.findAll();
	}

	@Override
	public Loan findById(Long id) {
		return loanRepository.findOne(id);
	}

}
