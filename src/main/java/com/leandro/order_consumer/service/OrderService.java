package com.leandro.order_consumer.service;

import com.leandro.order_consumer.dto.OrderCreatedEvent;
import com.leandro.order_consumer.entity.OrderEntity;
import com.leandro.order_consumer.entity.OrderItem;
import com.leandro.order_consumer.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent event) {
        var entity = new OrderEntity();
        entity.setOrderId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());
        entity.setTotal(getTotal(event));
        entity.setItens(getOrderItens(event));

        orderRepository.save(entity);
    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
        if (event.itens() == null) {
            return BigDecimal.ZERO;
        }
        return event.itens().stream()
                .map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static List<OrderItem> getOrderItens(OrderCreatedEvent event) {
        return event.itens().stream()
                .map(i -> new OrderItem(i.produto(), i.quantidade(), i.preco()))
                .toList();
    }
}
