package ml.puredark.hviewer.ruletester.http;

import okhttp3.Request;

public class HRequestBuilder extends Request.Builder {
    @Override
    public HRequestBuilder url(String url) {
        super.url(url);
        return this;
    }
}
