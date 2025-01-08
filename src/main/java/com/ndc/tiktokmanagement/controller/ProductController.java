package com.ndc.tiktokmanagement.controller;

import com.ndc.tiktokmanagement.model.Product;
import com.ndc.tiktokmanagement.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.ndc.tiktokmanagement.model.*;
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("manager/list-products")
    public String listProducts(Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        model.addAttribute("products", productService.getAllProducts());
        return "manager/list-products"; // Trả về template "list.html"
    }

    @GetMapping("manager/create-product")
    public String showCreateForm(Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/login";
        }
        model.addAttribute("product", new Product());
        return "manager/create-product"; // Trả về template "create.html"
    }

    @PostMapping("manager/create-product")
    public String createProduct(@ModelAttribute Product product, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/login";
        }
        productService.saveProduct(product);
        return "redirect:/manager/list-products"; // Redirect về danh sách sản phẩm
    }

    @GetMapping("manager/edit-product/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/login";
        }
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "manager/edit-product"; // Trả về template "edit.html"
    }

    @PostMapping("manager/edit-product/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/login";
        }
        product.setId(id);
        productService.saveProduct(product);
        return "redirect:/manager/list-products";
    }

    @GetMapping("manager/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/login";
        }
        productService.deleteProduct(id);
        return "redirect:/manager/list-products";
    }
}
