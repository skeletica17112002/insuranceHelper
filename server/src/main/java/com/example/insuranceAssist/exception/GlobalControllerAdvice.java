package com.example.insuranceAssist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerAdvice {

    // Handle AgentNotFoundException
    @ExceptionHandler(AgentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAgentNotFound(AgentNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle ClaimNotFoundException
    @ExceptionHandler(ClaimNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleClaimNotFound(ClaimNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle ClaimStatusNotFoundException
    @ExceptionHandler(ClaimStatusNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleClaimStatusNotFound(ClaimStatusNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle ClientNotFoundException
    @ExceptionHandler(ClaimTypeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleClaimTypeNotFound(ClaimTypeNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle ClientNotFoundException
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleClientNotFound(ClientNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle DependentNotFoundException
    @ExceptionHandler(DependentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleDependentNotFound(DependentNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle HospitalAlreadyExistsException
    @ExceptionHandler(HospitalAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleHospitalAlreadyExists(HospitalAlreadyExistsException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handle HospitalNotFoundException
    @ExceptionHandler(HospitalNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleHospitalNotFound(HospitalNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Handle PolicyClaimCoverageNotEnoughException
    @ExceptionHandler(PolicyClaimCoverageNotEnoughException.class)
    public ResponseEntity<Map<String, Object>> handlePolicyClaimCoverageNotEnough(PolicyClaimCoverageNotEnoughException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handle PolicyNotFoundException
    @ExceptionHandler(PolicyNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePolicyNotFound(PolicyNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle PolicyTypeNotFoundException
    @ExceptionHandler(PolicyTypeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePolicyTypeNotFound(PolicyTypeNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle RelationNotFoundException
    @ExceptionHandler(RelationNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleRelationNotFound(RelationNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle RoleNotFoundException
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleStatusTypeNotFound(RoleNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle StatusTypeNotFoundException
    @ExceptionHandler(StatusTypeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleStatusTypeNotFound(StatusTypeNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException ex){
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        return buildErrorResponse("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Build ErrorResponse
    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status", status.value());
        errorBody.put("error", status.getReasonPhrase());
        errorBody.put("message", message);
        return new ResponseEntity<>(errorBody, status);
    }
}
