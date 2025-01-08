package com.ndc.tiktokmanagement.repository;

import com.ndc.tiktokmanagement.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

}
