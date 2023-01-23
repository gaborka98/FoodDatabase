package me.agronaut.fooddatabase.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ApiError implements Serializable {
    private final List<String> error;
    private final LocalDateTime timestamp;
    private final HttpStatus status;
    private final String message;
}
