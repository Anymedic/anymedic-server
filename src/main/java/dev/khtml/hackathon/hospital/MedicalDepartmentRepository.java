package dev.khtml.hackathon.hospital;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalDepartmentRepository extends JpaRepository<MedicalDepartment, Long> {

	List<MedicalDepartment> findByDepartment(MedicalDepartmentEnum department);
}
