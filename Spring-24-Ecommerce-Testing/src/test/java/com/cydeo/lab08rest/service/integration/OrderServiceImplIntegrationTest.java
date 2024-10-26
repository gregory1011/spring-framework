package com.cydeo.lab08rest.service.integration;


import com.cydeo.lab08rest.entity.Cart;
import com.cydeo.lab08rest.entity.Customer;
import com.cydeo.lab08rest.entity.Order;
import com.cydeo.lab08rest.enums.PaymentMethod;
import com.cydeo.lab08rest.repository.CartRepository;
import com.cydeo.lab08rest.repository.CustomerRepository;
import com.cydeo.lab08rest.repository.OrderRepository;
import com.cydeo.lab08rest.service.CartService;
import com.cydeo.lab08rest.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class OrderServiceImplIntegrationTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CustomerRepository customerRepository;



    // we are testing placeOrder()
    @Test // payment type: TRANSFER
    public void should_create_order1() {
        Cart cart = cartRepository.findById(3L).get();
        Order orderBeforePlaceOrderMethod = orderRepository.findAllByCart(cart);
        BigDecimal resultOrder = orderService.placeOrder(PaymentMethod.TRANSFER, 3L, 56L);
        Order orderAfterPlaceOrderMethod = orderRepository.findAllByCart(cart);

        assertNull(orderBeforePlaceOrderMethod);
        assertNotNull(orderAfterPlaceOrderMethod);

        assertThat(resultOrder).isEqualTo(new BigDecimal("901.00"));
    }

    @Test // payment type: CREDIT_CARD
    public void should_create_order2() {
        Cart cart = cartRepository.findById(3L).get();
        Order orderBeforePlaceOrderMethod = orderRepository.findAllByCart(cart);
        BigDecimal resultOrder = orderService.placeOrder(PaymentMethod.CREDIT_CARD, 3L, 56L);
        Order orderAfterPlaceOrderMethod = orderRepository.findAllByCart(cart);

        assertNull(orderBeforePlaceOrderMethod);
        assertNotNull(orderAfterPlaceOrderMethod);

        assertThat(resultOrder).isEqualTo(new BigDecimal("891.00")); // - $10
    }

    @Test // payment type: CREDIT_CARD, without discount
    public void should_create_order_without_discount1() {
        Cart cart = cartRepository.findById(3L).get();
        Order orderBeforePlaceOrderMethod = orderRepository.findAllByCart(cart);

        assertNull(cart.getDiscount());

        BigDecimal discountAmount = cartService.applyDiscountToCartIfApplicableAndCalculateDiscountAmount("50 dollar", cart);
        assertThat(discountAmount).isEqualTo(new BigDecimal("50.00"));
        assertNotNull(cart.getDiscount());

        BigDecimal resultOrder = orderService.placeOrder(PaymentMethod.CREDIT_CARD, 3L, 56L);
        Order orderAfterPlaceOrderMethod = orderRepository.findAllByCart(cart);

        assertNull(orderBeforePlaceOrderMethod);
        assertNotNull(orderAfterPlaceOrderMethod);

        assertThat(resultOrder).isEqualTo(new BigDecimal("841.00")); // - $10
    }

    @Test // payment type: CREDIT_CARD, with discount
    public void should_not_pace_order_when_customer_does_not_exist() {

        Throwable throwable= catchThrowable( ()-> orderService.placeOrder(PaymentMethod.CREDIT_CARD, 1L, 4322314144L) ); // we are using no-existing customer id
        assertThat(throwable).isInstanceOf(RuntimeException.class);
    }

    @Test // payment type: CREDIT_CARD, with discount
    public void should_not_pace_order_when_cart_does_not_exist() {
        Customer customer = new Customer(); // we created a new customer and save it in database, now this customer has no carts assigned or 0 carts, or null;
        customerRepository.save(customer);
        Throwable throwable= catchThrowable( ()-> orderService.placeOrder(PaymentMethod.CREDIT_CARD, null, customer.getId()) ); // we are using non-existing cart id
        assertThat(throwable).isInstanceOf(RuntimeException.class);
        assertThat(throwable).hasMessage("Cart couldn't find or cart is empty");
    }


}
