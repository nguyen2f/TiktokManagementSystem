package com.ndc.tiktokmanagement.controller;
import com.ndc.tiktokmanagement.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.ndc.tiktokmanagement.model.*;
import com.ndc.tiktokmanagement.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private EmployeeService employeeService;


    // Hiển thị form tạo đơn hàng
    @GetMapping("/user/create-order")
    public String showCreateOrderForm(HttpSession session, Model model) {
        Customer loggedInCustomer = (Customer) session.getAttribute("loggedInUser");
        if (loggedInCustomer == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("order", new Order()); // Khởi tạo đối tượng Order rỗng
        return "user/create-order";
    }

    @GetMapping("/manager/list-orders")
    public String findAllOrders(HttpSession session, Model model) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/login";
        }

        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        return "manager/list-orders";
    }
    /*
    // Phân công người nhận hàng (pickup shipper)
    @PostMapping("/manager/assign-pickupshipper")
    @ResponseBody
    public ResponseEntity<String> assignPickupShipper(@RequestParam Long orderId, @RequestParam Long shipperId) {
        try {
            Order order = orderService.assignPickupShipper(orderId, shipperId);
            // Trả về số điện thoại người shipper đã được phân công
            return ResponseEntity.ok("Phân công người nhận thành công! Số điện thoại: " + order.getPickupShipperPhoneNumber());
        } catch (Exception e) {
            // Trả lỗi với thông tin chi tiết hơn
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phân công không thành công: " + e.getMessage());
        }
    }

    // Phân công người giao hàng (delivery shipper)
    @PostMapping("/manager/assign-delivershipper")
    @ResponseBody
    public ResponseEntity<String> assignDeliverShipper(@RequestParam Long orderId, @RequestParam Long shipperId) {
        try {
            Order order = orderService.assignDeliverShipper(orderId, shipperId);
            // Trả về số điện thoại người shipper đã được phân công
            return ResponseEntity.ok("Phân công người giao thành công! Số điện thoại: " + order.getDeliverShipperPhoneNumber());
        } catch (Exception e) {
            // Trả lỗi với thông tin chi tiết hơn
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phân công không thành công: " + e.getMessage());
        }
    }

    @PutMapping("/employee/{orderId}/update-status")
    public String updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status,
            @RequestParam String description,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        Employee loggedInEmployee = (Employee) session.getAttribute("loggedInEmployee");
        if (loggedInEmployee == null) {
            return "redirect:/shipper/login";
        }
        try {
            orderService.updateOrderStatus(orderId, status, description);
            redirectAttributes.addFlashAttribute("message", "Status updated successfully.");
            return "redirect:/shipper/deliver-orders";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/shipper/deliver-orders";
        }
    }
    //manager
    @GetMapping("/employee/pickup-orders")
    public String getPickupOrders(HttpSession session, Model model) {
        Employee loggedInEmployee = (Employee) session.getAttribute("loggedInEmployee");
        if (loggedInEmployee == null) {
            return "redirect:/shipper/login";
        }

        List<Order> pickupOrders = orderService.findByPickupShipperPhoneNumber(loggedInEmployee.getPhoneNumber());
        model.addAttribute("pickupOrders", pickupOrders);
        model.addAttribute("loggedInShipper", loggedInEmployee); // Đảm bảo luôn thêm biến này
        return "shipper/pickup-orders";
    }
    @GetMapping("/employee/deliver-orders")
    public String getDeliverOrders(HttpSession session, Model model) {
        Employee loggedInEmployee = (Employee) session.getAttribute("loggedInEmployee");
        if (loggedInEmployee == null) {
            return "redirect:/shipper/login";
        }

        List<Order> deliverOrders = orderService.findByDeliverShipperPhoneNumber(loggedInEmployee.getPhoneNumber());
        model.addAttribute("deliverOrders", deliverOrders);
        model.addAttribute("loggedInShipper", loggedInEmployee); // Đảm bảo luôn thêm biến này
        return "shipper/deliver-orders";
    }

*/

}
