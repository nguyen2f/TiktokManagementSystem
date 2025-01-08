package com.ndc.tiktokmanagement.controller;

import com.ndc.tiktokmanagement.model.Customer;
import com.ndc.tiktokmanagement.model.Employee;
import com.ndc.tiktokmanagement.model.Manager;
import com.ndc.tiktokmanagement.service.ManagerService;
import com.ndc.tiktokmanagement.service.EmployeeService;
import com.ndc.tiktokmanagement.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    public ManagerService managerService;

    @Autowired
    public EmployeeService employeeService;

    @Autowired
    public CustomerService customerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }


    // Đăng nhập
    @GetMapping("/login")
    public String showLoginForm() {
        return "manager/login";
    }

    @PostMapping("/login")
    public String loginManager(@RequestParam String phoneNumber,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        if (managerService.checkLogin(phoneNumber, password)) {
            Manager loggedInManager = managerService.findByPhoneNumber(phoneNumber);
            session.setAttribute("loggedInManager", loggedInManager);
            return "redirect:/manager/home"; // Chuyển hướng về trang chủ người dùng
        } else {
            model.addAttribute("error", "Số điện thoại hoặc mật khẩu không chính xác.");
            return "manager/login";
        }
    }
    @GetMapping("/home")
    public String managerHome(HttpSession session, Model model) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        model.addAttribute("user", loggedInManager);
        return "manager/home";
    }

    // Đăng xuất
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/manager/login";
    }

    @GetMapping("/list-employees")
    public String getAllEmployee(Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        List<Employee> employees = employeeService.getAllEmployee();
        model.addAttribute("employees", employees);
        return "manager/list-employees"; // Trả về tên file template
    }
    // Trang để tạo một Employee mới

    @GetMapping("/create-employee")
    public String createEmployeeForm(Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        model.addAttribute("employee", new Employee());
        return "manager/create-employee"; // Trả về template form
    }

    // Lưu một Employee mới
    @PostMapping("/create-employee")
    public String createEmployee(@ModelAttribute Employee employee, Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        try {
            employeeService.createEmployee(employee);
            return "redirect:/manager/list-employees"; // Redirect sau khi thành công
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create employee: " + e.getMessage());
            return "manager/create-employee"; // Trả về trang hiện tại với lỗi
        }
    }

    // Trang chỉnh sửa Employee
    @GetMapping("/update-employee/{id}")
    public String updateShipperForm(@PathVariable long id, Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        Employee employee = employeeService.getById(id); // Đảm bảo service có phương thức này
        model.addAttribute("employee", employee);
        return "manager/update-employee";
    }

    // Lưu thay đổi của Employee
    @PostMapping("/update-employee/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee, Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        try {
            employeeService.updateEmployee(id, employee);
            return "redirect:/manager/list-employees";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update employee: " + e.getMessage());
            return "manager/update-employee";
        }
    }

    // Xóa Employee
    @GetMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable Long id, Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }try {
            employeeService.deleteEmployee(id);
            return "redirect:/manager/list-employees";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to delete empLoyee: " + e.getMessage());
            return "manager/list-employees"; // Quay lại danh sách kèm thông báo lỗi
        }
    }

    // Hiển thị danh sách Customer
    @GetMapping("/list-customers")
    public String getAllCustomers(Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "manager/list-customers";
    }

    // Trang chỉnh sửa Customer
    @GetMapping("/update-customer/{id}")
    public String updateCustomerForm(@PathVariable Long id, Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        Customer customer = customerService.getById(id); // Đảm bảo service có phương thức này
        model.addAttribute("customer", customer);
        return "manager/update-customer";
    }

    // Lưu thay đổi của Customer
    @PostMapping("/update-customer/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer, Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        try {
            customerService.updateCustomer(id, customer);
            return "redirect:/manager/list-customers";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update customer: " + e.getMessage());
            return "manager/update-customer";
        }
    }

    // Xóa Customer
    @GetMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable Long id, Model model, HttpSession session) {
        Manager loggedInManager = (Manager) session.getAttribute("loggedInManager");
        if (loggedInManager == null) {
            return "redirect:/manager/login";
        }
        try {
            customerService.deleteCustomer(id);
            return "redirect:/manager/list-customers";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to delete customer: " + e.getMessage());
            return "manager/list-customers";
        }
    }
}
