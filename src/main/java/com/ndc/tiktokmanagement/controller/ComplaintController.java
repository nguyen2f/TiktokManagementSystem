package com.ndc.tiktokmanagement.controller;

import com.ndc.tiktokmanagement.model.Complaint;
import com.ndc.tiktokmanagement.model.Manager;
import com.ndc.tiktokmanagement.service.ComplaintService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    @GetMapping("manager/list-complaints")
    public String listComplaints(Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        // Lấy danh sách các đơn phàn nàn từ service
        List<Complaint> complaints = complaintService.getAllComplaints();

        // Thêm vào model để truy cập trong view
        model.addAttribute("complaints", complaints);
        return "manager/list-complaints"; // Chuyển tới view liệt kê phàn nàn
    }
}
