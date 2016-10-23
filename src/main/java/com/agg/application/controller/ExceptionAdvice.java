package com.agg.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agg.application.model.Result;
import com.fasterxml.jackson.core.JsonProcessingException;

@ControllerAdvice
public class ExceptionAdvice {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result fieldValidation(Exception exception) throws JsonProcessingException {
		logger.error("Exception: ", exception);
		return new Result("Failure", exception.getMessage(), null);
	}

}
