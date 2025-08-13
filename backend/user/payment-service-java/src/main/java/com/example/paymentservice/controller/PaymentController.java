package com.example.paymentservice.controller;

import com.example.paymentservice.model.PaymentOrder;
import com.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    public PaymentOrder createOrder(@RequestParam int amount) {
        return paymentService.createOrder(amount);
    }

    @PostMapping("/capture-payment")
    public PaymentOrder capturePayment(@RequestParam String orderId) {
        return paymentService.capturePayment(orderId);
    }

    @GetMapping("/status/{orderId}")
    public PaymentOrder getStatus(@PathVariable String orderId) {
        return paymentService.getStatus(orderId);
    }
}
