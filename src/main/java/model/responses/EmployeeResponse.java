package model.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import model.Employee;

import java.util.List;

@Getter
@Setter
public class EmployeeResponse {
    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private Employee employee;
    @JsonProperty("message")
    private String message;
}
