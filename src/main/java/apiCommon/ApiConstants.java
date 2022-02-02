package apiCommon;

import utils.ConfigFileReader;

public class ApiConstants {
    public static final String BASE_URL = ConfigFileReader.getInstance().getBaseUrl();
    ;
    public static final String EMPLOYEE_ENDPOINT = "employee/";
    public static final String EMPLOYEES_ENDPOINT = "employees";
    public static final String UPDATE_ENDPOINT = BASE_URL + "update/";
    public static final String DELETE_ENDPOINT = BASE_URL + "delete/";
    public static final String CREATE_ENDPOINT = BASE_URL + "create";
}
