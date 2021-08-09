package com.luizcasagrande.productapi.exception.advice;

import com.luizcasagrande.shoppingclient.dto.ErrorDTO;
import com.luizcasagrande.shoppingclient.exception.CategoryNotFoundException;
import com.luizcasagrande.shoppingclient.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.List;

@ControllerAdvice(basePackages = "com.luizcasagrande.productapi.controller")
public class ProductControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDTO handleProductNotFound() {
        var errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Produto não encontrado.");
        errorDTO.setTimestamp(new Date());
        return errorDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorDTO handleCategoryNotFound() {
        var errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Categoria não encontrada.");
        errorDTO.setTimestamp(new Date());
        return errorDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        var errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        BindingResult result = ex.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();

        var sb = new StringBuilder("Valor inválido para os campos:");
        for (FieldError error : errors) {
            sb.append(" ");
            sb.append(error.getField());
        }

        errorDTO.setMessage(sb.toString());
        errorDTO.setTimestamp(new Date());
        return errorDTO;
    }
}
