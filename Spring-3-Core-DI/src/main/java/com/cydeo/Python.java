package com.cydeo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@AllArgsConstructor // it helps  -> constructor injection
@Component
public class Python {

    OfficeHours officeHours;  // officeHours reverence class


//    // constructor injection
//    @Autowired // -> autowiring is not needed, unless we have more than one constructor
//    public Python(OfficeHours officeHours) {
//        this.officeHours = officeHours;
//    }

    public void getTeachingHours(){
        System.out.println( "Weekly teaching hours : " + (20 + officeHours.getHours()) ); // 20 + 5 = 25
    }


}
