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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Item implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idItem;
	
	@Digits(fraction = 0, integer = 6)
	@Min(value=100000)
	@Max(value=999999)
	@NotNull
	private Long universalProductCode;
	
	@NotNull
	@Size(min=2, max=60)
	private String title;
	
	@NotNull
	@Size(min=2, max=250)
	private String summary;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat (pattern="yyyy/MM/dd")
	@NotNull
	private Date releaseDate;
	
	@Digits(fraction = 0, integer = 4)
	@NotNull
	@Min(value=0)
	@Max(value=9999)
	private int quantity;
	
	@Digits(fraction = 0, integer = 5)
	@Min(value=0)
	@Max(value=99999)
	private int numberPages;
	
	@Digits(fraction = 0, integer = 4)
	@Min(value=0)
	@Max(value=9999)
	private int duration;
	
	@NotNull
	@ManyToOne
	private TypeItem typeItem;
	
	@NotNull
	@ManyToOne
	private Category category;

	public Item() {
	}

	public Item(Long idItem, Long universalProductCode, String title, String summary, Date releaseDate, int quantity,
			int numberPages, int duration, TypeItem typeItem, Category category) {
		this.idItem = idItem;
		this.universalProductCode = universalProductCode;
		this.title = title;
		this.summary = summary;
		this.releaseDate = releaseDate;
		this.quantity = quantity;
		this.numberPages = numberPages;
		this.duration = duration;
		this.typeItem = typeItem;
		this.category = category;
	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public Long getUniversalProductCode() {
		return universalProductCode;
	}

	public void setUniversalProductCode(Long universalProductCode) {
		this.universalProductCode = universalProductCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getNumberPages() {
		return numberPages;
	}

	public void setNumberPages(int numberPages) {
		this.numberPages = numberPages;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public TypeItem getTypeItem() {
		return typeItem;
	}

	public void setTypeItem(TypeItem typeItem) {
		this.typeItem = typeItem;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Item [idItem=" + idItem + ", universalProductCode=" + universalProductCode + ", title=" + title
				+ ", summary=" + summary + ", releaseDate=" + releaseDate + ", quantity=" + quantity + ", numberPages="
				+ numberPages + ", duration=" + duration + ", typeItem=" + typeItem + ", category=" + category + "]";
	}
}
