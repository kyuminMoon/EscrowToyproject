package com.tistory.kmmoon.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler(UserGuideException.class)
  public ResponseEntity<CommonResponse<Void>> userGuideException(UserGuideException exception) {
    return new ResponseEntity<>(CommonResponse.fail(exception.getMessage()), HttpStatus.BAD_REQUEST);
  }

}
