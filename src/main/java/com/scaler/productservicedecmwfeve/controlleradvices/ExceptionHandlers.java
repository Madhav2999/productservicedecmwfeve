package com.scaler.productservicedecmwfeve.controlleradvices;

import com.scaler.productservicedecmwfeve.Dtos.ArithmeticExceptionDto;
import com.scaler.productservicedecmwfeve.Dtos.ExceptionDto;
import com.scaler.productservicedecmwfeve.Exceptions.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers
{
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> handleArithmeticException()
    {
        ArithmeticExceptionDto arithmeticExceptionDto = new ArithmeticExceptionDto();
        arithmeticExceptionDto.setMessage("arithmetic exception has occured");
        return new ResponseEntity<>(arithmeticExceptionDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBounds()
    {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ExceptionDto> handleProductNotExistException(ProductNotExistException productNotExistException)
    {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(productNotExistException.getMessage());
        return new ResponseEntity<>(exceptionDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
