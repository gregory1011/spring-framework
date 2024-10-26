package com.cydeo.lab08rest.service.integration;


import com.cydeo.lab08rest.entity.*;
import com.cydeo.lab08rest.enums.CartState;
import com.cydeo.lab08rest.repository.*;
import com.cydeo.lab08rest.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class CartServiceImplIntegrationTest {

    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;


    // ---- 1st method from CartService
    @Test() // 1. without existing cart
    public void shouldAddItemToCart1() {
        Customer customer = new Customer();
        customer.setEmail("sam@cydeo.com");
        customerRepository.save(customer);

        boolean result = cartService.addToCart(customer, 1L, 10);
        assertTrue(result);

        List<Cart> cartList = cartRepository.findAllByCustomerIdAndCartState(customer.getId(), CartState.CREATED);
        assertThat(cartList.size()).isEqualTo(1);

        Product product = productRepository.findById(1L).get();
        CartItem cartItem = cartItemRepository.findAllByCartAndProduct(cartList.get(0), product);
        assertNotNull(cartItem);
    }

    @Test // 2. with existing cart from data.sql
    public void shouldAddItemToCart2() {
        //this example involves actual data from data.sql
        Customer customer = customerRepository.findById(40L).get(); // the id= 40 comes from data.sql: this 40 customer has one product in cart

        boolean result = cartService.addToCart(customer, 1L, 10); // this customer has 1 product in cart from data.sql
        assertTrue(result);

        List<Cart> cartList = cartRepository.findAllByCustomerIdAndCartState(customer.getId(), CartState.CREATED);
        assertThat(cartList.size()).isEqualTo(1);

        Product product = productRepository.findById(1L).get();
        CartItem cartItem = cartItemRepository.findAllByCartAndProduct(cartList.get(0), product);
        assertNotNull(cartItem);
    }

    @Test // 3. negative case scenario
    public void shouldNotAddItemToCartWhenNotEnoughItemsInStock() {
        //this example involves actual data from data.sql
        Customer customer = customerRepository.findById(40L).get(); // the id= 40 comes from data.sql: this 40 customer has one product in cart

        Exception ex= catchException(() -> cartService.addToCart(customer, 1L, 450));// this product id has 425 items left in stock, only. We get 450 then it should throw an exception
        assertThat(ex).isInstanceOf(RuntimeException.class);
        assertEquals("Not enough stock", ex.getMessage());
    }

 // --- 2nd method from CartService
    @Test  // 1. scenario when discount is applicable: AMOUNT_BASED_DISCOUNT
    public void shouldApplyDiscountToCartExiting1(){
        Cart cart = cartRepository.findById(3L).get();
        BigDecimal result = cartService.applyDiscountToCartIfApplicableAndCalculateDiscountAmount("50 dollar", cart); // 50 dollar comes from data.sql , when we use Enums
        assertNotNull(cart.getDiscount());
        assertThat(result).isEqualTo(new BigDecimal("50.00"));
    }

    @Test  // 1. scenario when discount is applicable: RATE_BASED_DISCOUNT
    public void shouldApplyDiscountToCartExisting2(){
        Cart cart = cartRepository.findById(3L).get();
        BigDecimal result = cartService.applyDiscountToCartIfApplicableAndCalculateDiscountAmount("%25", cart); // 50 dollar comes from data.sql , when we use Enums
        assertNotNull(cart.getDiscount());
        assertThat(result).isEqualTo(new BigDecimal("225.2500"));
    }

    @Test  // 1. scenario when discount is applicable: with non-existing customer AMOUNT_BASED
    public void shouldApplyDiscountToCartNonExistingCustomer3(){
        Customer customer = new Customer();
        customer.setEmail("sam@cydeo.com");
        customerRepository.save(customer);
        cartService.addToCart(customer, 1L, 10);

        List<Cart> cartList = cartRepository.retrieveCartListByCustomer(customer.getId());
        assertThat(cartList.size()).isEqualTo(1); // one customer
        assertNull(cartList.get(0).getDiscount());

        cartService.applyDiscountToCartIfApplicableAndCalculateDiscountAmount("50 dollar", cartList.get(0)); // apply discount
        assertNotNull(cartList.get(0).getDiscount()); // assert discount isn't null
    }

    @Test  // 1. negative scenario when discount is applicable but not used (return null) : with non-existing customer
    public void shouldApplyDiscountToCartNonExistingCustomer4(){
        Customer customer = new Customer();
        customer.setEmail("sam@cydeo.com");
        customerRepository.save(customer);
        cartService.addToCart(customer, 1L, 1);

        List<Cart> cartList = cartRepository.retrieveCartListByCustomer(customer.getId());
        assertThat(cartList.size()).isEqualTo(1); // one customer
        assertNull(cartList.get(0).getDiscount());

        cartService.applyDiscountToCartIfApplicableAndCalculateDiscountAmount("50 dollar", cartList.get(0)); // apply discount
        assertNull(cartList.get(0).getDiscount()); // assert discount is null, because min amount is less than req and the discount is not applicable
    }

}
