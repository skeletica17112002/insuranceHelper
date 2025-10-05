package com.example.insuranceAssist.exception;

public class PolicyClaimCoverageNotEnoughException extends Exception{

    public PolicyClaimCoverageNotEnoughException(){}

    public PolicyClaimCoverageNotEnoughException(String message){
        super(message);
    }

}
