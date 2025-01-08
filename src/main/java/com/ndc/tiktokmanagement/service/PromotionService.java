package com.ndc.tiktokmanagement.service;

import com.ndc.tiktokmanagement.model.Promotion;
import com.ndc.tiktokmanagement.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    // Thêm mới Promotion
    public Promotion addPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    // Lấy danh sách tất cả các Promotion
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    // Lấy Promotion theo ID
    public Optional<Promotion> getPromotionById(int id) {
        return promotionRepository.findById(id);
    }

    // Xóa Promotion theo ID
    public void deletePromotion(int id) {
        promotionRepository.deleteById(id);
    }
    public void applyDiscount(){}
    public void validateCode(){}

}