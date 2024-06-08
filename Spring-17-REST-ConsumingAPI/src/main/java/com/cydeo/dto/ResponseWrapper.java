package com.cydeo.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ResponseWrapper {

    // this is custom class we want to include in our custom response body
    // this is additionally info for custom response body

    private boolean success;
    private String message;
    private Integer code;
    private Object data;

    // we create 2 different constructors for specific use
    public ResponseWrapper(String message, Object data) {
        this.success = true;
        this.message = message;
        this.code = HttpStatus.OK.value();
        this.data = data;
    }

    public ResponseWrapper(String message) {
        this.success = true;
        this.message = message;
        this.code = HttpStatus.OK.value();
    }


}
