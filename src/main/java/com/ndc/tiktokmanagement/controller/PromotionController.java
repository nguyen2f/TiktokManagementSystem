package com.ndc.tiktokmanagement.controller;

import com.ndc.tiktokmanagement.model.Manager;
import com.ndc.tiktokmanagement.model.Promotion;
import com.ndc.tiktokmanagement.service.PromotionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    // Hiển thị form thêm promotion
    @GetMapping("manager/create-promotion")
    public String showCreateForm(Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        model.addAttribute("promotion", new Promotion());
        return "manager/create-promotion"; // Tạo view create-promotion.html
    }

    // Thêm Promotion từ form
    @PostMapping("manager/create-promotion")
    public String addPromotion(@ModelAttribute Promotion promotion, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        // Xử lý lưu promotion vào cơ sở dữ liệu
        promotionService.addPromotion(promotion);
        return "redirect:/manager/list-promotions"; // Chuyển hướng đến danh sách promotion
    }

    // Hiển thị danh sách các promotion
    @GetMapping("/manager/list-promotions")
    public String listPromotions(Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        model.addAttribute("promotions", promotionService.getAllPromotions());
        return "manager/list-promotions"; // Tạo view list-promotions.html
    }
}
