package com.ndc.tiktokmanagement.service;
import com.ndc.tiktokmanagement.model.Order;
import com.ndc.tiktokmanagement.repository.OrderRepository;
import com.ndc.tiktokmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    public OrderRepository orderRepository;



    @Autowired
    public EmployeeRepository employeeRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        return orderRepository.save(order);

    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
    // Thống kê số lượng đơn hàng và tỷ lệ thành công/thất bại
    public Map<String, Long> getOrderStatistics() {
        Map<String, Long> statistics = new HashMap<>();
        statistics.put("COMPLETED", orderRepository.countByStatus("COMPLETED"));
        statistics.put("PROCESSING", orderRepository.countByStatus("PROCESSING"));
        statistics.put("PENDING", orderRepository.countByStatus("PENDING"));
        statistics.put("CANCELLED", orderRepository.countByStatus("CANCELLED"));
        return statistics;
    }

    public List<Map<String, Object>> getDailyRevenue() {
        List<Object[]> results = orderRepository.calculateDailyRevenue();
        List<Map<String, Object>> revenues = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> data = new HashMap<>();
            data.put("date", result[0]); // Ngày đặt hàng
            data.put("revenue", result[1]); // Tổng doanh thu
            revenues.add(data);
        }
        return revenues;
    }
    public void processOrder(Order order) {}
    public void trackOrder(Order order) {}
/*
    @Transactional
    public void updateOrderStatus(Long orderId, String newStatus, String description) {
        // Tìm đơn hàng
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng."));
        // Cập nhật trạng thái hiện tại
        order.setStatus(newStatus);
        orderRepository.save(order);
        // Thêm bản ghi vào OrderHistory
        OrderHistory history = new OrderHistory();
        history.setOrder(order);
        history.setStatus(newStatus);
        history.setDescription(description);
        history.setTimestamp(LocalDateTime.now());
        orderHistoryRepository.save(history);
    }

    public List<OrderHistoryDTO> getOrderHistory(Long orderId) {
        List<OrderHistory> history = orderHistoryRepository.findByOrderId(orderId);

        // Chuyển đổi danh sách `OrderHistory` thành danh sách `OrderHistoryDTO`
        return history.stream()
                .map(h -> new OrderHistoryDTO(
                        h.getStatus(),
                        h.getDescription(),
                        h.getTimestamp()))
                .collect(Collectors.toList());
    }
/*
    public Order assignPickupShipper(Long orderId, Long shipperId)  {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng."));

        Employee employee = employeeRepository.findById(shipperId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy employee."));

        order.setPickupShipperPhoneNumber(employee.getPhoneNumber());
        return orderRepository.save(order);
    }

    public Order assignDeliverShipper(Long orderId, Long shipperId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng."));

        Employee employee = employeeRepository.findById(shipperId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy employee."));

        order.setDeliverShipperPhoneNumber(employee.getPhoneNumber());
        return orderRepository.save(order);
    }
    public double calculateShippingCost(double orderWeight, int orderQuantity) {
        // Logic tính chi phí dựa trên trọng lượng, số lượng và địa chỉ
        double baseCost = 10000; // Chi phí cơ bản
        double orderWeightCost = orderWeight * 2000; // Chi phí dựa trên trọng lượng (giả sử 1kg = 1000 VNĐ)
        double orderQuantityCost = orderQuantity * 3500; // Chi phí dựa trên số lượng (giả sử mỗi món hàng thêm 500 VNĐ)

        return baseCost + orderWeightCost + orderQuantityCost;
    }
*/
}
