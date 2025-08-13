package com.example.paymentservice.service;

import com.example.paymentservice.model.PaymentOrder;
import com.example.paymentservice.repository.PaymentOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentOrderRepository repository;

    public PaymentOrder createOrder(int amount) {
        PaymentOrder po = new PaymentOrder();
        po.setRazorpayOrderId("order_" + UUID.randomUUID().toString().replace("-", ""));
        po.setAmount(amount);
        po.setCurrency("INR");
        po.setStatus("CREATED");
        repository.save(po);
        return po;
    }

    public PaymentOrder capturePayment(String orderId) {
        Optional<PaymentOrder> optional = repository.findByRazorpayOrderId(orderId);
        if (optional.isPresent()) {
            PaymentOrder po = optional.get();
            po.setStatus("PAID");
            repository.save(po);
            return po;
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    public PaymentOrder getStatus(String orderId) {
        return repository.findByRazorpayOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
