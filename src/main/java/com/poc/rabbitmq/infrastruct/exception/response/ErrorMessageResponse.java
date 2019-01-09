package com.poc.rabbitmq.infrastruct.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class ErrorMessageResponse {

    private final String message;

}
