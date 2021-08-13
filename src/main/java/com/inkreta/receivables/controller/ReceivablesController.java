package com.inkreta.receivables.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inkreta.receivables.response.ApiError;
import com.inkreta.receivables.response.ResponseMessage;
import com.inkreta.receivables.service.ReceivablesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.inkreta.receivables.constant.ReceivableConstants.JSON_PROCESSING_FAILED;
import static com.inkreta.receivables.constant.ReceivableConstants.RESULT_IS_NULL;;

@CrossOrigin
@RestController
@RequestMapping("/")
public class ReceivablesController {
  @Autowired
  private ReceivablesService receivablesService;
  
  @ApiOperation(value = "Provide the current outstanding value, payload not required!", notes = "Aggregated invoice value where over due status flag is N", response = Long.class)
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved current outstanding value"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/current/outstandings"})
  public ResponseEntity<ResponseMessage> currentOutStanding() {
    BigDecimal resultData = this.receivablesService.findCurrentOutstandings("findCurrentOutstandings");
    return nullCheckOnKPIResult(resultData);
  }
  private ResponseEntity<ResponseMessage> nullCheckOnKPIResult(BigDecimal kpiData) {
		if(Objects.isNull(kpiData)){
          return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(new ApiError(RESULT_IS_NULL,null)));
      }else {
      	return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(kpiData.longValue()));
      }
	}
  @ApiOperation(value = "Return the aggregated invoice value based on age buckets, payload not required!", response = List.class, responseContainer = " List<Map<String, Object>>")
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved age bucket invoice values"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/current/age-bucket/invoice"})
  public ResponseEntity<ResponseMessage> ageBucketWiseInvoiceValue() {
    
    String errorMessage = null;
	String exceptionMsg = null;
	List<Map<String, Object>> responseList=null;
	try {
		responseList = this.receivablesService.findCurrentAnalysis("findCurrentAnalysis");
	} catch (Exception e) {
		errorMessage = JSON_PROCESSING_FAILED;exceptionMsg = e.getMessage();
	} 
	return nullCheckOnResponse(errorMessage, exceptionMsg, responseList);
  }
  private ResponseEntity<ResponseMessage> nullCheckOnResponse(String errorMessage, String exceptionMsg, List<Map<String, Object>> responseList) {
		if (responseList==null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(new ApiError(errorMessage,exceptionMsg)));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(responseList));
	}
  @ApiOperation(value = "Return the aggregated invoice value for over due invoices, payload not required!", notes = "Aggregated invoice value where over due status flag is Y and invoice status is open", response = Long.class)
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved current overdue invoice value"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/current/overdue"})
  public ResponseEntity<ResponseMessage> currentOverdue() {
    BigDecimal resultData = this.receivablesService.findCurrentOverdue("findCurrentOverdue");
    return nullCheckOnKPIResult(resultData);
  }
  
  @ApiOperation(value = "Provide the customer-wise aggregated invoice value, payload not required!", notes = "Aggregated invoice value based on age bucket and over due status flag for open invoices", response = Long.class)
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved customer-wise invoice value"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/customer/invoice-value/analysis"})
  public ResponseEntity<ResponseMessage> customerWiseInvoiceValue() {
    String errorMessage = null;
	String exceptionMsg = null;
	List<Map<String, Object>> responseList=null;
	try {
		responseList = this.receivablesService.findCustomerAnalysis("findCustomerAnalysis");
	} catch (Exception e) {
		errorMessage = JSON_PROCESSING_FAILED;exceptionMsg = e.getMessage();
	} 
	return nullCheckOnResponse(errorMessage, exceptionMsg, responseList);
  }
  
  @ApiOperation(value = "Provides the aggregated closed over due percent, payload not required!",  response = Long.class)
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved closed over due percent"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/closed/overdue/percent"})
  public ResponseEntity<ResponseMessage> closedOverduePercent() {
    BigDecimal resultData = this.receivablesService.findCurrentOutstandings("findClosedOverduePercent");
    return nullCheckOnKPIResult(resultData);
  }

  @ApiOperation(value = "Provides the aggregated closed average ardays, payload not required!", response = Long.class)
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved closed average ardays value"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/closed/average/ardays"})
  public ResponseEntity<ResponseMessage> getClosedAvgArDays() {
    BigDecimal resultData = this.receivablesService.findCurrentOutstandings("findClosedAvgArDays");
    return nullCheckOnKPIResult(resultData);
  }
  
  @ApiOperation(value = "Provide the aggregated ontime precent value, payload not required!", response = Long.class)
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved ontime percent value"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/closed/ontime/percent"})
  public ResponseEntity<ResponseMessage> getClosedOntimePercent() {
    BigDecimal resultData = this.receivablesService.findCurrentOutstandings("findClosedOntimePercent");
    return nullCheckOnKPIResult(resultData);
  }
  
  @ApiOperation(value = "Provide the customer's top five bucket by count , payload not required!", response = Long.class)
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved top five bucket count values"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/customer/top-five/bucket-count"})
  public ResponseEntity<ResponseMessage> getTopFiveCustomerBucketByCount() {
    String errorMessage = null;
	String exceptionMsg = null;
	List<Map<String, Object>> responseList=null;
	try {
		responseList = this.receivablesService.findCustomerAnalysis("findTopFiveCustomerBucketByCount");
	} catch (Exception e) {
		errorMessage = JSON_PROCESSING_FAILED;exceptionMsg = e.getMessage();
	} 
	return nullCheckOnResponse(errorMessage, exceptionMsg, responseList);
  }
  
  @ApiOperation(value = "Provide the customer's top five bucket by value , payload not required!", response = Long.class)
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved top five bucket value"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/customer/top-five/bucket-value"})
  public ResponseEntity<ResponseMessage> getTopFiveCustomerBucketByValue() {
    String errorMessage = null;
	String exceptionMsg = null;
	List<Map<String, Object>> responseList=null;
	try {
		responseList = this.receivablesService.findCustomerAnalysis("findTopFiveCustomerBucketByValue");
	} catch (Exception e) {
		errorMessage = JSON_PROCESSING_FAILED;exceptionMsg = e.getMessage();
	} 
	return nullCheckOnResponse(errorMessage, exceptionMsg, responseList);
  }
  
  @ApiOperation(value = "Provide the age bucket analysis value, payload not required!" , response = Long.class)
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved aging bucket analysis value"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/age-bucket/analysis"})
  public ResponseEntity<ResponseMessage> getAgingBucketAnalysis() {
    String errorMessage = null;
	String exceptionMsg = null;
	List<Map<String, Object>> responseList=null;
	try {
		responseList = this.receivablesService.findCustomerAnalysis("findCustomerAgingBucketAnalysis");
	} catch (Exception e) {
		errorMessage = JSON_PROCESSING_FAILED;exceptionMsg = e.getMessage();
	} 
	return nullCheckOnResponse(errorMessage, exceptionMsg, responseList);
  }
  
  @ApiOperation(value = "Provide the customer data analysis to generate scatter chart, payload not required!" , response = Long.class)
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved customer analysis data"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/customer/data/analysis"})
  public ResponseEntity<ResponseMessage> getCustomerData() {
    String errorMessage = null;
	String exceptionMsg = null;
	List<Map<String, Object>> responseList=null;
	try {
		responseList = this.receivablesService.findCustomerAnalysis("findCustomerDataAnalysis");
	} catch (Exception e) {
		errorMessage = JSON_PROCESSING_FAILED;exceptionMsg = e.getMessage();
	} 
	return nullCheckOnResponse(errorMessage, exceptionMsg, responseList);
  }
}
