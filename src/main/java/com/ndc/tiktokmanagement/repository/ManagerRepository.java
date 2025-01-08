package com.ndc.tiktokmanagement.repository;

import com.ndc.tiktokmanagement.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByPhoneNumber(String phoneNumber);
}
