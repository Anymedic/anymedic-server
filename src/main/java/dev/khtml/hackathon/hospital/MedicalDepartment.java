package dev.khtml.hackathon.hospital;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "medical_department")
public class MedicalDepartment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "department")
	private MedicalDepartmentEnum department;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hospital_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Hospital hospital;

}
