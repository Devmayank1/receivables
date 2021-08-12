package com.inkreta.receivables.controller;

import com.inkreta.receivables.response.ApiError;
import com.inkreta.receivables.response.ResponseMessage;
import com.inkreta.receivables.service.ReceivablesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    if (Objects.isNull(resultData))
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(new ApiError("Result is null", null))); 
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(Long.valueOf(resultData.longValue())));
  }
  
  @ApiOperation(value = "Return the aggregated invoice value based on age buckets, payload not required!", response = List.class, responseContainer = " List<Map<String, Object>>")
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved age bucket invoice values"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/current/age-bucket/invoice"})
  public ResponseEntity<ResponseMessage> ageBucketWiseInvoiceValue() {
    List<Map<String, Object>> resultData = this.receivablesService.findCurrentAnalysis("findCurrentAnalysis");
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(resultData));
  }
  
  @ApiOperation(value = "Return the aggregated invoice value for over due invoices, payload not required!", notes = "Aggregated invoice value where over due status flag is Y and invoice status is open", response = Long.class)
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved current overdue invoice value"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/current/overdue"})
  public ResponseEntity<ResponseMessage> currentOverdue() {
    BigDecimal resultData = this.receivablesService.findCurrentOverdue("findCurrentOverdue");
    if (Objects.isNull(resultData))
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(new ApiError("Result is null", null))); 
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(Long.valueOf(resultData.longValue())));
  }
  
  @ApiOperation(value = "Provide the customer-wise aggregated invoice value, payload not required!", notes = "Aggregated invoice value based on age bucket and over due status flag for open invoices", response = Long.class)
  @ApiResponses({@ApiResponse(code = 200, message = "Successfully retrieved customer-wise invoice value"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  @GetMapping({"/customer/invoice-value/analysis"})
  public ResponseEntity<ResponseMessage> customerWiseInvoiceValue() {
    List<Map<String, Object>> resultData = this.receivablesService.findCustomerAnalysis("findCustomerAnalysis");
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(resultData));
  }
  
  @GetMapping({"/closed/overdue/percent"})
  public ResponseEntity<ResponseMessage> closedOverduePercent() {
    BigDecimal resultData = this.receivablesService.findCurrentOutstandings("findClosedOverduePercent");
    if (Objects.isNull(resultData))
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(new ApiError("Result is null", null))); 
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(Long.valueOf(resultData.longValue())));
  }
  
  @GetMapping({"/closed/average/ardays"})
  public ResponseEntity<ResponseMessage> getClosedAvgArDays() {
    BigDecimal resultData = this.receivablesService.findCurrentOutstandings("findClosedAvgArDays");
    if (Objects.isNull(resultData))
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(new ApiError("Result is null", null))); 
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(Long.valueOf(resultData.longValue())));
  }
  
  @GetMapping({"/closed/ontime/percent"})
  public ResponseEntity<ResponseMessage> getClosedOntimePercent() {
    BigDecimal resultData = this.receivablesService.findCurrentOutstandings("findClosedOntimePercent");
    if (Objects.isNull(resultData))
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(new ApiError("Result is null", null))); 
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(Long.valueOf(resultData.longValue())));
  }
  
  @GetMapping({"/customer/top-five/bucket-count"})
  public ResponseEntity<ResponseMessage> getTopFiveCustomerBucketByCount() {
    List<Map<String, Object>> resultData = this.receivablesService.findCustomerAnalysis("findTopFiveCustomerBucketByCount");
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(resultData));
  }
  
  @GetMapping({"/customer/top-five/bucket-value"})
  public ResponseEntity<ResponseMessage> getTopFiveCustomerBucketByValue() {
    List<Map<String, Object>> resultData = this.receivablesService.findCustomerAnalysis("findTopFiveCustomerBucketByValue");
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(resultData));
  }
  
  @GetMapping({"/age-bucket/analysis"})
  public ResponseEntity<ResponseMessage> getAgingBucketAnalysis() {
    List<Map<String, Object>> resultData = this.receivablesService.findCustomerAnalysis("findCustomerAgingBucketAnalysis");
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(resultData));
  }
}
