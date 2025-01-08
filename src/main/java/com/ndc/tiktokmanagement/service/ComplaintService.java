package com.ndc.tiktokmanagement.service;

import com.ndc.tiktokmanagement.model.Complaint;
import com.ndc.tiktokmanagement.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {
    @Autowired
    private ComplaintRepository complaintRepository;

    public List<Complaint> getAllComplaints() {
        // Lấy tất cả phàn nàn từ cơ sở dữ liệu
        return complaintRepository.findAll();
    }
    public void updateComplaint() {}
    public void deleteComplaint(Complaint complaint) {
        complaintRepository.delete(complaint);
    }
    public void processComplaint(Complaint complaint) {}
    public void resolveComplaint(Complaint complaint) {}
    public void trackComplaint(Complaint complaint) {}
    public void registerComplaint(Complaint complaint) {}
    public void assignEmployee(Complaint complaint) {}
    public void updateStatus(Complaint complaint) {}
}
