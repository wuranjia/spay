package com.wrj.spay.exception;

import com.lufax.jersey.exception.InvokeRemoteServiceException;

public class RemoteInvokeException extends RuntimeException {
    private Integer status;
    private String url;
    private Object parameter;
    private String host;

    public RemoteInvokeException(Integer status, String host, String url, Object parameter) {
        super("invoke host [" + host + "] url [" + url + "] with parameter [" + parameter + "] return status is [" + status + "]");
        this.status = status;
        this.url = url;
        this.parameter = parameter;
        this.host = host;
    }

    public RemoteInvokeException(String hostURI, String url, String msg, InvokeRemoteServiceException e) {
        super(msg, e);
        this.host = hostURI;
        this.url = url;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }


}
