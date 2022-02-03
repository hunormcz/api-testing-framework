package apiCommon;

import utils.ConfigFileReader;

public class ApiEndpoints {
    public static final String BASE_URL = ConfigFileReader.getInstance().getBaseUrl();
    ;
    public static final String EMPLOYEE_ENDPOINT = "employee/";
    public static final String EMPLOYEES_ENDPOINT = "employees";
    public static final String UPDATE_ENDPOINT = "update/";
    public static final String DELETE_ENDPOINT = "delete/";
    public static final String CREATE_ENDPOINT = "create";
}
