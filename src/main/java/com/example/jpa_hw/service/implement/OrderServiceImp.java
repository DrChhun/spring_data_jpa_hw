package com.example.jpa_hw.service.implement;

import com.example.jpa_hw.models.Customer;
import com.example.jpa_hw.models.Order;
import com.example.jpa_hw.models.Product;
import com.example.jpa_hw.models.ProductOrder;
import com.example.jpa_hw.models.constant.OrderStatusEnum;
import com.example.jpa_hw.models.dto.request.OrderRequest;
import com.example.jpa_hw.models.dto.response.order.OrderResponseDTO;
import com.example.jpa_hw.models.dto.response.product.ProductOrderDTO;
import com.example.jpa_hw.repository.CustomerRepository;
import com.example.jpa_hw.repository.OrderRepository;
import com.example.jpa_hw.repository.ProductOrderRepository;
import com.example.jpa_hw.repository.ProductRepository;
import com.example.jpa_hw.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ProductOrderRepository productOrderRepository;
    @Override
    public OrderResponseDTO saveOrder(List<OrderRequest> orderRequests, Long customerId) {
    //get id customer from the parameter and set it ot order_tb table
    //to calculate total amount
        //find product details
        List<Long> productIds = orderRequests.stream()
                .map(OrderRequest::getProductId)
                .collect(Collectors.toList());

        //get all product qty and then fetch product to get all unit-price and sum it to be total price
        List<Long> productQty = orderRequests.stream()
                .map(OrderRequest::getQuantity)
                .toList();

        List<Product> listOfProduct = productRepository.findAllById(productIds);

        Map<Long, Product> productMap = listOfProduct.stream()
                .collect(Collectors.toMap(Product::getProductId, product -> product));

// Calculate total price
        Double totalPrice = orderRequests.stream()
                .mapToDouble(orderRequest -> {
                    Product product = productMap.get(orderRequest.getProductId());
                    if (product != null) {
                        return product.getUnitPrice() * orderRequest.getQuantity();
                    }
                    return 0.0;
                })
                .sum();

        //map product to productOrderDTO
        List<ProductOrderDTO> listOfProductDTO = listOfProduct.stream()
                .map(product -> ProductOrderDTO.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .unitPrice(product.getUnitPrice())
                        .description(product.getDescription())
                        .build())
                .collect(Collectors.toList());

//        Float totalAmount = productDetail.get().getUnitPrice() * orderRequests.getFirst().getQuantity();

        Optional<Customer> findCustomer = customerRepository.findById(customerId);
                Order orderDetail = Order.builder()
                        .orderId(null)
                        .orderDate(LocalDateTime.now())
                        .totalAmount(totalPrice.floatValue())
                        .customer(findCustomer.get())
                        .orderStatus(OrderStatusEnum.PENDING)
                        .build();

        Order saveOrder = orderRepository.save(orderDetail);

        OrderResponseDTO orderRes = OrderResponseDTO.builder()
                .orderId(saveOrder.getOrderId())
                .productOrders(listOfProductDTO)
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatusEnum.PENDING)
                .totalAmount(totalPrice.floatValue())
                .build();

        orderRequests.forEach(orderRequest -> {
            Product product = productMap.get(orderRequest.getProductId());
            if (product != null) {
                ProductOrder trackOrder = ProductOrder.builder()
                        .id(null)
                        .quantity(orderRequest.getQuantity())
                        .order(saveOrder)
                        .product(product)
                        .build();
                productOrderRepository.save(trackOrder);
            }
        });

        return orderRes;
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {

        Optional<Order> resutlOrder = orderRepository.findById(id);

        OrderResponseDTO orderRes = OrderResponseDTO.builder()
                .totalAmount(resutlOrder.get().getTotalAmount())
                .orderId(resutlOrder.get().getOrderId())
                .productOrders(resutlOrder.get().getProductOrders().stream()
                        .map(x -> ProductOrderDTO.builder()
                                .productId(x.getProduct().getProductId())
                                .description(x.getProduct().getDescription())
                                .productName(x.getProduct().getProductName())
                                .unitPrice(x.getProduct().getUnitPrice())
                                .build()).collect(Collectors.toList()))
                .orderStatus(resutlOrder.get().getOrderStatus())
                .orderDate(resutlOrder.get().getOrderDate())
                .build();

        return orderRes;
    }

    @Override
    public List<Object> getOrderByCustomerId(Long id) {
        List<?> orderByCustomer = orderRepository.findByCustomerCustomerId(id);
        return List.of(orderByCustomer);
    }

    @Override
    public OrderResponseDTO updateStatusByOrderId(Long id, OrderStatusEnum status) {
        Optional<Order> resultOrder = orderRepository.findById(id);
        Order updateOrder = Order.builder()
                .orderId(resultOrder.get().getOrderId())
                .productOrders(resultOrder.get().getProductOrders())
                .orderDate(resultOrder.get().getOrderDate())
                .orderStatus(status)
                .totalAmount(resultOrder.get().getTotalAmount())
                .build();
        Order gg = orderRepository.save(updateOrder);
        OrderResponseDTO map = OrderResponseDTO.builder()
                .orderId(gg.getOrderId())
                .orderDate(gg.getOrderDate())
                .orderStatus(gg.getOrderStatus())
                .totalAmount(gg.getTotalAmount())
                .productOrders(null)
                .build();
        return map;
    }
}
