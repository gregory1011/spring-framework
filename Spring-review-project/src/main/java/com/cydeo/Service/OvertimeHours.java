package com.cydeo.Service;

import com.cydeo.Repository.HoursRep;

public class OvertimeHours implements HoursRep {

    @Override
    public Integer getHours() {
        return 15;
    }

}
