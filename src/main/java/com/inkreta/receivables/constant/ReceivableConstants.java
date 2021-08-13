package com.inkreta.receivables.constant;

public class ReceivableConstants {
    public static final String AGE_BUCKET_INVOICE_VALUE = "findCurrentAnalysis";
    public static final String CURRENT_OUTSTANDINGS = "findCurrentOutstandings";
    public static final String CURRENT_OVERDUE = "findCurrentOverdue";
    public static final String CUSTOMER_WISE_INVOICE_VALUE = "findCustomerAnalysis";
    public static final String RESULT_IS_NULL = "Result is null";
	public static final String JSON_PROCESSING_FAILED = "Json Processing failed!";
	public static final String JSON_MAPPING_FAILED = "Json Mapping failed!";
	public static final String CURRENT_OVERDUE_PERCENT = "findClosedOverduePercent";
	public static final String CLOSED_AVG_AR_DAYS = "findClosedAvgArDays";
	public static final String CLOSED_ONE_TIME_PERCENT = "findClosedOntimePercent";
	public static final String TOP_FIVE_BUCKET_COUNT = "findTopFiveCustomerBucketByCount";
	public static final String TOP_FIVE_BUCKET_VALUE = "findTopFiveCustomerBucketByValue";
	public static final String CUSTOMER_AGING_BUCKET_ANALYSIS = "findCustomerAgingBucketAnalysis";
	public static final String CUSTOMER_DATA_ANALYSIS = "findCustomerDataAnalysis";
    private ReceivableConstants(){
        /**
         * Instance not allowed
         */
    }
    
    
}
