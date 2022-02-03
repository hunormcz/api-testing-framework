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
//    @JsonAlias({"employee_name", "name"})
    private String name;
    @JsonProperty("employee_salary")
//    @JsonAlias({"employee_salary", "salary"})
    private Integer salary;
    @JsonProperty("employee_age")
//    @JsonAlias({"employee_age", "age"})
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
