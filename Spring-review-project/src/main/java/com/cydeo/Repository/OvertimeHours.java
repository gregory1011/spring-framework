package com.cydeo.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OvertimeHours implements HoursRep {

    @Qualifier("overtime")
    @Override
    public Integer getHours() {
        return 15;
    }

}
