package com.mickaelbrenoit.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mickaelbrenoit.business.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{
	
	@Query("SELECT l FROM Loan l WHERE l.user.idUser = :idUser")
	List<Loan> findAllLoansByUserId(@Param("idUser") Long idUser);

}
