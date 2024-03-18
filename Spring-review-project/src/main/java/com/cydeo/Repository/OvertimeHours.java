
package com.cydeo.Repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("OTH")
public class OvertimeHours implements HoursRep {

    @Override
    public Integer getHours() {
        return 15;
    }
}
