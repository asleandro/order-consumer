package com.leandro.order_consumer.repository;

import com.leandro.order_consumer.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {
}
