package com.cydeo.bootstrap;

import com.cydeo.entity.Customer;
import com.cydeo.entity.Merchant;
import com.cydeo.entity.Payment;
import com.cydeo.entity.PaymentDetail;
import com.cydeo.enums.Status;
import com.cydeo.repository.CustomerRepo;
import com.cydeo.repository.MerchantRepo;
import com.cydeo.repository.PaymentRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DataGenerator implements CommandLineRunner {


    // inject dependency
    private final PaymentRepo paymentRepo;
    private final MerchantRepo merchantRepo;
    private final CustomerRepo customerRepo;


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



    }


}
