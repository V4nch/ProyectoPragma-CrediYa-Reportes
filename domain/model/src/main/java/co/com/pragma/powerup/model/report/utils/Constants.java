package co.com.pragma.powerup.model.report.utils;

public class Constants {
    public static final String COUNT_REPORT_ID = "REPORT_COUNT_LOAN_APPROVED";
    public static final String AMOUNT_REPORT_ID = "REPORT_AMOUNT_LOAN_APPROVED";
    public static final String SUMMARY_GET_REPORT = "GET report";
    public static final String DESCRIPTION_GET_REPORT = "Allows getting a report";
    public static final String CODE_200 = "200";
    public static final String CODE_500 = "500";
    public static final String RESPONSE_REPORT_LISTED = "Report successfully listed";
    public static final String EXAMPLE_REPORT_LISTED_NAME = "ReportListResponseExample";
    public static final String EXAMPLE_REPORT_LISTED_VALUE = """
        {
          "Reports": [
            {
              "metrica": "REPORT_COUNT_LOAN_APPROVED",
              "valor": 5.0
            },
            {
              "metrica": "REPORT_AMOUNT_LOAN_APPROVED",
              "valor": 5.0
            }
          ]
        }
        """;
    public static final String RESPONSE_INTERNAL_ERROR_GET = "Unexpected server error";
    public static final String EXAMPLE_SERVER_ERROR_NAME_GET = "ServerErrorExample";
    public static final String EXAMPLE_SERVER_ERROR_VALUE_GET = """
        {
          "error": "ReportRepositoryException",
          "message": "Unexpected error while querying the repository."
        }
        """;
    public static final String REPORT_RECEIVED = "Report received";
    public static final String GENERATE_REPORT_SUCCESSFUL = "Generate report successful";
    public static final String GENERATE_REPORT_ERROR = "Generate report error";
    public static final String PATH_REPORT = "/api/v1/reportes";
    public static final String CONTENT_TYPE = "application/json";
    public static final String GET_NAME_FUNCTION = "getReport";
    public static final String API_CREDIYA = "API CrediYa";
    public static final String VERSION_1 = "1.0";
    public static final String LOAN_APP_DESCRIPTION = "API for managing loan applications in CrediYa";
    public static final String BEARER_AUTH = "BearerAuth";
    public static final String BEARER = "bearer";
    public static final String JWT = "JWT";
    public static final String AUTHORIZATION = "Authorization";
    public static final String SWAGGER_UI_HTML = "/swagger-ui.html";
    public static final String SWAGGER_UI_ALL = "/swagger-ui/**";
    public static final String V3_API_DOCS = "/v3/api-docs/**";
    public static final String WEBJARS = "/webjars/**";
    public static final String SWAGGER_INDEX = "/swagger-ui/index.html";
    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}
