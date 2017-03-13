package ml.puredark.hviewer.ruletester.http;

import okhttp3.Request;

public class HRequestBuilder extends Request.Builder {

    public HRequestBuilder(){
        super();
        this.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
    }
	
    @Override
    public HRequestBuilder url(String url) {
        super.url(url);
        return this;
    }
}
