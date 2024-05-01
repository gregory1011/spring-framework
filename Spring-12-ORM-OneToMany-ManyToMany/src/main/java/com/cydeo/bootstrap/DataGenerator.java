package com.cydeo.bootstrap;

import com.cydeo.entity.*;
import com.cydeo.enums.Status;
import com.cydeo.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataGenerator implements CommandLineRunner {


    // inject dependency
    private final PaymentRepo paymentRepo;
    private final MerchantRepo merchantRepo;
    private final CustomerRepo customerRepo;
    private final ItemRepo itemRepo;
    private final CartRepo cartRepo;


    @Override
    public void run(String... args) throws Exception {


        Payment payment1 = new Payment(LocalDate.of(2022,4,19),new BigDecimal("150000"), Status.Success);
        PaymentDetail paymentDetail1 = new PaymentDetail(new BigDecimal("140000"),new BigDecimal("10000"), LocalDate.of(2022,4,24));

        payment1.setPaymentDetail(paymentDetail1);

        Payment payment2 = new Payment(LocalDate.of(2022,4,25),new BigDecimal("100000"), Status.Failure);
        PaymentDetail paymentDetail2 = new PaymentDetail(new BigDecimal("90000"),new BigDecimal("5000"),LocalDate.of(2022,4,29));

        payment2.setPaymentDetail(paymentDetail2);

        Merchant merchant1 = new Merchant("AmazonSubMerchant","M123",new BigDecimal("0.25"),new BigDecimal("3.25"),5);
        payment1.setMerchant(merchant1);
        payment2.setMerchant(merchant1);

        Customer customer1 = new Customer("m_smith","Mike","Smith","msmith@cydeo.com","123 Main St, Quincy, MA 02168");
        payment1.setCustomer(customer1);
        payment2.setCustomer(customer1);

        merchantRepo.save(merchant1);
        customerRepo.save(customer1);

        paymentRepo.save(payment1);
        paymentRepo.save(payment2);

        // --------- cart and items ----------

        Item item1 = new Item("Milk","M01");
        Item item2 = new Item("Sugar","S01");
        Item item3 = new Item("Bread","B01");

        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        cart1.setItemList(Arrays.asList(item1, item2));
        cart2.setItemList(Arrays.asList(item1, item2, item3));

        itemRepo.save(item1);
        itemRepo.save(item2);
        itemRepo.save(item3);

        cartRepo.save(cart1);
        cartRepo.save(cart2);



    }


}
