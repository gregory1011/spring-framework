package com.cydeo.lab08rest.service.unit;

import com.cydeo.lab08rest.entity.*;
import com.cydeo.lab08rest.enums.CartState;
import com.cydeo.lab08rest.enums.PaymentMethod;
import com.cydeo.lab08rest.repository.*;
import com.cydeo.lab08rest.service.CartService;
import com.cydeo.lab08rest.service.impl.OrderServiceImpl;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplUnitTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CartService cartService;

    //payment repository was not mocked in the recordings, but we should mock it to prevents test failings
    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void should_throw_exception_when_the_customer_does_not_exist(){

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        Throwable throwable = catchThrowable(() -> orderService.placeOrder(PaymentMethod.TRANSFER, 134L, 1L));
        assertThat(throwable).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void should_throw_exception_when_cart_list_of_the_customer_is_null(){
        Customer customer = new Customer();
        customer.setId(1L);

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(cartRepository.findAllByCustomerIdAndCartState(customer.getId(), CartState.CREATED)).thenReturn(null);
        Throwable throwable = catchThrowable(() -> orderService.placeOrder(PaymentMethod.TRANSFER, 134L, customer.getId()));
        assertThat(throwable).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void should_throw_exception_when_cart_list_of_the_customer_size_is_zero(){
        Customer customer = new Customer();
        customer.setId(1L);

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(cartRepository.findAllByCustomerIdAndCartState(customer.getId(), CartState.CREATED)).thenReturn(new ArrayList<>());
        Throwable throwable = catchThrowable(() -> orderService.placeOrder(PaymentMethod.TRANSFER,134L,customer.getId()));
        assertThat(throwable).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void should_place_order_without_discount(){

        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(5));
        product.setRemainingQuantity(60);

        Customer customer = new Customer();
        customer.setId(1L);

        Cart cart = new Cart();
        cart.setId(1L);
        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(4);
        cartItem.setProduct(product);
        cartItem.setCart(cart);

        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);

        //when
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(cartRepository.findAllByCustomerIdAndCartState(customer.getId(), CartState.CREATED)).thenReturn(cartList);
        when(cartItemRepository.findAllByCart(cart)).thenReturn(cartItemList);

        //then
        BigDecimal results = orderService.placeOrder(PaymentMethod.TRANSFER, cart.getId(), customer.getId());
        assertThat(results).isEqualTo(BigDecimal.valueOf(20));
        assertThat(product.getRemainingQuantity()).isEqualTo(56);

//        Product product = new Product();
//        product.setPrice(BigDecimal.valueOf(5));
//        product.setRemainingQuantity(60);
//
//        Customer customer = new Customer();
//        customer.setId(1L);
//
//        Cart cart = new Cart();
//        cart.setId(1L);
//        List<Cart> cartList = new ArrayList<>();
//        cartList.add(cart);
//
//        CartItem cartItem = new CartItem();
//        cartItem.setQuantity(4);
//        cartItem.setProduct(product);
//        cartItem.setCart(cart);
//
//        List<CartItem> cartItemList = new ArrayList<>();
//        cartItemList.add(cartItem);
//
//        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
//        when(cartRepository.findAllByCustomerIdAndCartState(customer.getId(), CartState.CREATED)).thenReturn(cartList);
//        when(cartItemRepository.findAllByCart(cart)).thenReturn(cartItemList);

//        BigDecimal result = orderService.placeOrder(PaymentMethod.TRANSFER, cart.getId(),customer.getId());
//        assertThat(result).isEqualTo(BigDecimal.valueOf(20));
//        assertThat(product.getRemainingQuantity()).isEqualTo(56);
    }

    @Test
    public void should_place_order_with_discount(){
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(5));
        product.setRemainingQuantity(60);

        Customer customer = new Customer();
        customer.setId(1L);

        Discount discount = new Discount();
        discount.setName("discount");

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setDiscount(discount);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(4);
        cartItem.setProduct(product);
        cartItem.setCart(cart);

        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(cartRepository.findAllByCustomerIdAndCartState(customer.getId(), CartState.CREATED)).thenReturn(cartList);
        when(cartItemRepository.findAllByCart(cart)).thenReturn(cartItemList);
        when(cartService.applyDiscountToCartIfApplicableAndCalculateDiscountAmount(cart.getDiscount().getName(), cart)).thenReturn(BigDecimal.TEN);

        BigDecimal result = orderService.placeOrder(PaymentMethod.TRANSFER, cart.getId(),customer.getId());
        assertThat(result).isEqualTo(BigDecimal.valueOf(10));
        assertThat(product.getRemainingQuantity()).isEqualTo(56);
    }

    @Test
    public void should_place_order_with_discount_and_extra_credit_cart_discount(){
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(5));
        product.setRemainingQuantity(60);

        Customer customer = new Customer();
        customer.setId(1L);

        Discount discount = new Discount();
        discount.setName("discount");

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setDiscount(discount);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(10);
        cartItem.setProduct(product);
        cartItem.setCart(cart);

        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(cartRepository.findAllByCustomerIdAndCartState(customer.getId(), CartState.CREATED)).thenReturn(cartList);
        when(cartItemRepository.findAllByCart(cart)).thenReturn(cartItemList);
        when(cartService.applyDiscountToCartIfApplicableAndCalculateDiscountAmount
                (cart.getDiscount().getName(),cart)).thenReturn(BigDecimal.valueOf(5));  // another $5 discount

        BigDecimal result = orderService.placeOrder(PaymentMethod.CREDIT_CARD, cart.getId(),customer.getId()); // credit card applies $10 discount
        assertThat(result).isEqualTo(BigDecimal.valueOf(35));
        assertThat(product.getRemainingQuantity()).isEqualTo(50);
    }

    @Test
    public void should_not_place_order_because_item_is_removed_from_cart(){
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(5));
        product.setRemainingQuantity(5);

        Customer customer = new Customer();
        customer.setId(1L);

        Cart cart = new Cart();
        cart.setId(1L);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(10);
        cartItem.setProduct(product);
        cartItem.setCart(cart);

        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(cartRepository.findAllByCustomerIdAndCartState(customer.getId(), CartState.CREATED)).thenReturn(cartList);
        when(cartItemRepository.findAllByCart(cart)).thenReturn(cartItemList);

        BigDecimal result = orderService.placeOrder(PaymentMethod.CREDIT_CARD, cart.getId(),customer.getId());
        assertThat(result).isEqualTo(BigDecimal.ZERO); // we assert if we can buy 10 quantity , we cannot because we only have 5 items in stock. return 0
        assertThat(product.getRemainingQuantity()).isEqualTo(5);//  return 5 because we couldn't make the purchase 10 items , we only gave 5 items in stock.
    }

    // homework
    // what about 2 item in the cart, one of them out of stock and the other can be processed

    @Test
    public void should_place_order_with_removing_items_when_one_of_them_out_of_stock_and_the_other_can_be_processed(){
        Product product = new Product();
        product.setId(1L);
        product.setPrice(BigDecimal.ONE);
        product.setRemainingQuantity(50);

        Product product2 = new Product();
        product2.setId(1L);
        product2.setPrice(BigDecimal.ONE);
        product2.setRemainingQuantity(12);

        Customer customer = new Customer();
        customer.setId(1L);

        Cart cart = new Cart();
        cart.setCartState(CartState.CREATED);
        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setQuantity(40);
        cartItem.setProduct(product);

        CartItem cartItem2 = new CartItem();
        cartItem2.setCart(cart);
        cartItem2.setQuantity(15);
        cartItem2.setProduct(product2);

        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);
        cartItemList.add(cartItem2);

        when(cartRepository.findAllByCustomerIdAndCartState(customer.getId(),CartState.CREATED)).thenReturn(cartList);
        when(cartItemRepository.findAllByCart(cart)).thenReturn(cartItemList);
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        BigDecimal paidPrice = orderService.placeOrder(PaymentMethod.TRANSFER,cart.getId(), customer.getId());
        AssertionsForClassTypes.assertThat(paidPrice).isEqualTo(BigDecimal.valueOf(40));
        AssertionsForClassTypes.assertThat(product2.getRemainingQuantity()).isEqualTo(12);
        AssertionsForClassTypes.assertThat(product.getRemainingQuantity()).isEqualTo(10);
    }
}