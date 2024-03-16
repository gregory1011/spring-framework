
package com.cydeo.repository;

import org.springframework.stereotype.Component;

@Component
public class RegularHours implements HoursRep{

    @Override
    public Integer getHours() {
        return 40;
    }

}
