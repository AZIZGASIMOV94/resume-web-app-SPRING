package com.mycompany.dto;

public class ResponseDTO {
    private Integer errorCode;
    private String errorMessage;
    private String successMessage;
    private Object ibj;


    private ResponseDTO(){

    }

    public static ResponseDTO of(Object obj){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setIbj(obj);
        return responseDTO;
    }

    public static ResponseDTO of(Object obj, String successMessage){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setIbj(obj);
        responseDTO.setSuccessMessage(successMessage);
        return responseDTO;
    }
    public static ResponseDTO of(Object obj, Integer errorCode, String errorMessage){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setIbj(obj);
        responseDTO.setErrorCode(errorCode);
        responseDTO.setErrorMessage(errorMessage);
        return responseDTO;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public Object getIbj() {
        return ibj;
    }

    public void setIbj(Object ibj) {
        this.ibj = ibj;
    }
}
