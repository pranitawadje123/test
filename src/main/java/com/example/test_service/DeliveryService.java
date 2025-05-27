package com.example.test_service;

abstract class DeliveryService {

    String trackingId;

    DeliveryService(String trackingId) {
        this.trackingId = trackingId;
    }
}
