package com.mickaelbrenoit.business.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Item implements Serializable{
	
	@Id
	@Digits(fraction = 0, integer = 12)
	@Size(min=12, max=12)
	private Long UniversalProductCode;
	
	@NotNull
	@Size(min=2, max=60)
	private String title;
	
	@NotNull
	@Size(min=2, max=250)
	private String summary;
	
	@DateTimeFormat
	@NotNull
	private Date releaseDate;
	
	@Digits(fraction = 0, integer = 4)
	@NotNull
	private int quantity;
	
	@Digits(fraction = 0, integer = 5)
	private int numberPages;
	
	@Digits(fraction = 2, integer = 4)
	private Double duration;
	
	@ManyToOne
	private TypeItem typeItem;
	
	@ManyToOne
	private Category category;
	
	
	
}
