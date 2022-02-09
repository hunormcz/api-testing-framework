package model.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import model.Employee;
import java.util.List;

@Getter
@Setter
public class EmployeesResponse {
    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private List<Employee> employees;
    @JsonProperty("message")
    private String message;
}
