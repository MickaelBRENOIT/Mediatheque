package com.mickaelbrenoit.business.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Loan implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLoan;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat (pattern="yyyy-MM-dd")
	@NotNull
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat (pattern="yyyy-MM-dd")
	@NotNull
	private Date endDate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date givenBackDate;
	
	@NotNull
	@ManyToOne
	private User user;
	
	@NotNull
	@ManyToOne
	private Item item;

	public Loan() {
	}

	public Loan(Date startDate, Date endDate, Date givenBackDate, User user, Item item) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.givenBackDate = givenBackDate;
		this.user = user;
		this.item = item;
	}

	public Long getIdLoan() {
		return idLoan;
	}

	public void setIdLoan(Long idLoan) {
		this.idLoan = idLoan;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getGivenBackDate() {
		return givenBackDate;
	}

	public void setGivenBackDate(Date givenBackDate) {
		this.givenBackDate = givenBackDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Loan [idLoan=" + idLoan + ", startDate=" + startDate + ", endDate=" + endDate + ", givenBackDate="
				+ givenBackDate + ", user=" + user + ", item=" + item + "]";
	}
	

}
