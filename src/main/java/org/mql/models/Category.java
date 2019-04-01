package org.mql.models;

import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "cat_id")
	private int id;

	@Column
	private String name;

	@Column
	private String description;
	
	
	@OneToMany(mappedBy = "category")
	private List<Formation> formations = new Vector<>(); 

	public Category() {
		super();
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public List<Formation> getFormations() {
		return formations;
	}
	
	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}
	
	@Override
	public String toString() {
		return "\n"+name;
	}
}
