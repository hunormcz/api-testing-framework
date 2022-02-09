package model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee{
      @JsonProperty("id")
    private Integer id;
    @JsonProperty("employee_name")
    private String name;
    @JsonProperty("employee_salary")
    private Integer salary;
    @JsonProperty("employee_age")
    private Integer age;
    @JsonProperty("profile_image")
    private String image;

    public Employee(){}

    public Employee(String name, Integer salary, Integer age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
}
