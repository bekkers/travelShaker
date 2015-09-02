package com.prochainvol.api.request;

import java.net.URL;
import java.util.concurrent.Callable;

public class AsynchronousRequest implements Callable<AsynchronousResponse> {
    private URL url;

    public AsynchronousRequest(URL url) {
        this.url = url;
    }

    @Override
    public AsynchronousResponse call() throws Exception {
        return new AsynchronousResponse(url.openStream());
    }
}
