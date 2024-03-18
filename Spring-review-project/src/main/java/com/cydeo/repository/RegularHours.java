package com.cydeo.Repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class RegularHours implements HoursRep{

    @Override
    public Integer getHours() {
        return 40;
    }
}
