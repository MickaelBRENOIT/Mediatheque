package com.mickaelbrenoit.business.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"nameItem"})})
public class TypeItem implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTypeItem;

	@NotNull
	@Size(min = 2, max = 30)
	private String nameItem;

	public TypeItem() {
	}

	public TypeItem(String nameItem) {
		this.nameItem = nameItem;
	}

	public Long getIdTypeItem() {
		return idTypeItem;
	}

	public void setIdTypeItem(Long idTypeItem) {
		this.idTypeItem = idTypeItem;
	}

	public String getNameItem() {
		return nameItem;
	}

	public void setNameItem(String nameItem) {
		this.nameItem = nameItem;
	}

	@Override
	public String toString() {
		return "TypeItem [idTypeItem=" + idTypeItem + ", nameItem=" + nameItem + "]";
	}
}
