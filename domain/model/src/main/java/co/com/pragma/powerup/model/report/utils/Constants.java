package co.com.pragma.powerup.model.report.utils;

public class Constants {
    public static final String COUNT_REPORT_ID = "REPORT_COUNT_LOAN_APPROVED";
    public static final String AMOUNT_REPORT_ID = "REPORT_AMOUNT_LOAN_APPROVED";
    public static final String ROLE_ADMIN = "hasRole('ADMIN')";
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
}
