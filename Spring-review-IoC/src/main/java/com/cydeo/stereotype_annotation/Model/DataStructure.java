package com.cydeo.stereotype_annotation.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class DataStructure {

@NonNull
   private final ExtraHours extraHours;

    public void getTotalHours(){
        System.out.println("Total hours " + (30 + extraHours.getHours()));
    }


}
