package com.ndc.tiktokmanagement.controller;

import com.ndc.tiktokmanagement.model.Manager;
import com.ndc.tiktokmanagement.model.Report;
import com.ndc.tiktokmanagement.service.ReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Liệt kê báo cáo
    @GetMapping("manager/list-reports")
    public String listReports(Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        model.addAttribute("reports", reportService.getAllReports());
        return "manager/list-reports"; // View hiển thị danh sách báo cáo
    }

    // Trang tạo báo cáo mới
    @GetMapping("manager/create-report")
    public String createReportForm(Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        model.addAttribute("report", new Report());
        return "manager/create-report"; // View tạo báo cáo
    }

    // Xử lý tạo báo cáo mới
    @PostMapping("manager/create-report")
    public String createReport(@ModelAttribute("report") Report report, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        reportService.createReport(report);
        return "redirect:/manager/reports/list"; // Sau khi tạo, chuyển hướng về danh sách báo cáo
    }
}
