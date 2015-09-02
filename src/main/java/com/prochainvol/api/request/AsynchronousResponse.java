package com.prochainvol.api.request;

import java.io.InputStream;

public class AsynchronousResponse {
    private InputStream body;

    public AsynchronousResponse(InputStream body) {
        this.body = body;
    }

    public InputStream getBody() {
        return body;
    }
}
