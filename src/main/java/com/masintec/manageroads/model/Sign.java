/**
 * 
 */
package com.masintec.manageroads.model;

import java.io.Serializable;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * This class represents parent class for all road signs
 */
@Entity
@Table (name = "SIGNS")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "TYPE", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "signType")
@JsonSubTypes ({ 
  @Type(value = WarningSign.class, name = "WARNING"), 
  @Type(value = RegulatorySign.class, name = "REGULATORY"), 
  @Type(value = GuideSign.class, name = "GUIDE"),
  @Type(value = InformationSign.class, name = "INFORMATION"),
  @Type(value = UnknownSign.class, name = "UNKNOWN")
})
public abstract class Sign implements Serializable {

	private static final long serialVersionUID = 825833814370826743L;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "ID_SIGN", updatable = false, nullable=false)
	private Long id;

	@JsonIgnore
	@Transient
	@Enumerated (EnumType.STRING)
	private final SignType signType;
	
	@ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn (name = "ID_ROAD", referencedColumnName = "id_road")
	private Road road;

	@Column (name = "NAME")
	private String name;
	
	@Column (name = "DESCRIPTION")
	private String description;
	
	@Column (name = "SIDE")
	@Enumerated (EnumType.STRING)
	private RoadSide side;
	
	@Column (name = "POINT")
	private Long point;

	public Sign(SignType signType, String name, String description, String side, Long point) {
		this.signType = signType;
		this.name = name;
		this.description = description;
		this.side = RoadSide.valueOf(side);
		this.point = point;
	}
	
	public SignType getSignType() {
		return signType;
	}
	
	public SignType getSignByType(String signType) {
		return SignType.getByType(signType);
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

	public Sign(final SignType signType) {
		this.signType = signType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public RoadSide getSide() {
		return side;
	}

	public void setSide(RoadSide side) {
		this.side = side;
	}
	
	public Road getRoad() {
		return road;
	}

	public void setRoad(Road road) {
		this.road = road;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Sign [id=" + id + ", signType=" + signType + ", road=" + road + ", name=" + name + ", description="
				+ description + ", side=" + side + ", point=" + point + "]";
	}

	public static enum SignType {
		UNKNOWN(Values.UNKNOWN_VALUE),
		GUIDE(Values.GUIDE_VALUE),
		REGULATORY(Values.REGULATORY_VALUE),
		WARNING(Values.WARNING_VALUE),
		INFORMATION(Values.INFORMATION_VALUE);
		
		@Column (name = "TYPE")
		private final String stype;

		private SignType(final String stype) {
			this.stype = stype;
		}

		public String getSType() {
			return stype;
		}
		
		public static SignType getByType(final String type) {
			return Stream.of(SignType.values()).filter(st -> st.getSType().equals(type)).findFirst().orElse(SignType.UNKNOWN);
		}
		
		public static final class Values {
			public static final String UNKNOWN_VALUE = "0";
			public static final String GUIDE_VALUE = "1";
			public static final String REGULATORY_VALUE = "2";
			public static final String WARNING_VALUE = "3";
			public static final String INFORMATION_VALUE = "4";
		}
	};
	
	public static enum RoadSide {
		UNKNOWN("UNKNOWN"),
		LEFT("LEFT"),
		RIGHT("RIGHT");

		private String side;

		private RoadSide(final String side) {
			this.side = side;
		}

		public String getSide() {
			return side;
		}
	};
}
