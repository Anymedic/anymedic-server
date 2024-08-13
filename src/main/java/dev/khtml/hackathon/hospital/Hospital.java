package dev.khtml.hackathon.hospital;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hospital")
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "road_address")
	private String roadAddress;

	@Column(name = "region")
	private String region;

	@Column(name = "town")
	private String town;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "latitude")
	private double latitude;

	@Column(name = "longitude")
	private double longitude;
}
