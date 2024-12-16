package com.masintec.manageroads.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "INTERSECTIONS")
public class Intersection implements Serializable {

	private static final long serialVersionUID = 2077546707471004891L;

	public Intersection() {
	}
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "ID_INTERSECTION", updatable = false, nullable=false)
	private Long id;

	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable (
		name = "INTERSECTIONS_ROADS", 
		joinColumns = { @JoinColumn (name = "ID_INTERSECTION") }, 
		inverseJoinColumns = { @JoinColumn (name = "ID_ROAD") }
	)
	private Set<Road> roads;

	@Column (name = "NAME")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Road> getRoads() {
		return roads;
	}

	public void setRoads(Set<Road> roads) {
		this.roads = roads;
	}

	@Override
	public String toString() {
		return "Intersection [id=" + id + ", roads=" + roads + ", name=" + name + "]";
	}
}
