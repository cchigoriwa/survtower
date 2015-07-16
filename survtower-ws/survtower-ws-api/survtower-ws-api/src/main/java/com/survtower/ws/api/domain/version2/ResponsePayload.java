package com.survtower.ws.api.domain.version2;

/**
 *
 * @author charlesc
 * @param <T>
 */
public abstract class ResponsePayload {

    private ResponseHead responseHead;

    public ResponseHead getResponseHead() {
        return responseHead;
    }

    public void setResponseHead(ResponseHead responseHead) {
        this.responseHead = responseHead;
    }

}
