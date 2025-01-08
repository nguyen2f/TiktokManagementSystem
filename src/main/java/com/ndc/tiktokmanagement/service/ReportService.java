package com.ndc.tiktokmanagement.service;

import com.ndc.tiktokmanagement.model.Report;
import com.ndc.tiktokmanagement.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    // Lấy tất cả báo cáo
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // Tạo báo cáo mới
    public Report createReport(Report report) {
        report.setGeneratedDate(LocalDate.now());
        return reportRepository.save(report);
    }
    public void exportPDF() {}
    public void exportExcel() {}
    public void generateSalesReport() {}
    public void generateInventoryReport() {}
    public void generateCustomerReport() {}
    public void generateEmployeeReport() {}
}