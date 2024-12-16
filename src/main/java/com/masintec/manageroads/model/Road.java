package com.masintec.manageroads.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table (name = "ROADS")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "TYPE", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "roadType")
@JsonSubTypes ({ 
  @Type(value = GravelRoad.class, name = "GRAVEL"), 
  @Type(value = ConcreteRoad.class, name = "CONCRETE"), 
  @Type(value = AsphaltRoad.class, name = "ASPHALT"),
  @Type(value = UnknownRoad.class, name = "UNKNOWN")
})
public abstract class Road implements Serializable {

	private static final long serialVersionUID = 2815665821639191507L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "ID_ROAD", updatable = false, nullable=false)
	private Long id;

	@JsonIgnore
	@OneToMany (mappedBy = "road", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Sign> signs;

	@JsonIgnore
	@ManyToMany (mappedBy = "roads", cascade = CascadeType.ALL)
	private Set<Intersection> intersections;

	@JsonIgnore
	@Transient
	@Enumerated (EnumType.STRING)
	private RoadType roadType;

	@Column (name = "NAME")
	private String name;

	@Column (name = "DESCRIPTION")
	private String description;

	public Road() {
	}
	
	public Road(RoadType roadType) {
		this.roadType = roadType;
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

	public RoadType getRoadType() {
		return roadType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<Intersection> getIntersections() {
		return intersections;
	}

	public void setIntersections(Set<Intersection> intersections) {
		this.intersections = intersections;
	}

	public List<Sign> getSigns() {
		return signs;
	}

	public void setSigns(List<Sign> signs) {
		this.signs = signs;
	}

	@Override
	public String toString() {
		return "Road [id=" + id + ", type=" + roadType + ", name=" + name + ", description=" + description + "]";
	}
	
	public enum RoadType {
		UNKNOWN(Values.UNKNOWN_VALUE),
		GRAVEL (Values.GRAVEL_VALUE),
		ASPHALT(Values.ASPHALT_VALUE),
		CONCRETE(Values.CONCRETE_VALUE);

		private final String rtype;

		private RoadType(final String rtype) {
			this.rtype = rtype;
		}

		public String getRType() {
			return rtype;
		}

		public static RoadType getByRType(final String rtype) {
			return Stream.of(RoadType.values()).filter(st -> st.getRType().equals(rtype)).findFirst().orElse(RoadType.UNKNOWN);
		}

		public static class Values {
			public static final String UNKNOWN_VALUE = "0";
			public static final String GRAVEL_VALUE = "1";
			public static final String ASPHALT_VALUE = "2";
			public static final String CONCRETE_VALUE = "3"; 
		}
	};
}
