package ml.puredark.hviewer.ruletester.http;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HViewerHttpClient {
    private static OkHttpClient mClient = new OkHttpClient.Builder()
                                                .connectTimeout(30, TimeUnit.SECONDS)
                                                .readTimeout(60, TimeUnit.SECONDS)
                                                .build();

    public static String get(String url, String cookies){
        if (url == null || !url.startsWith("http")) {
            Logger.d("HViewerHttpClient", "url = "+url);
            return null;
        }
        HRequestBuilder builder = new HRequestBuilder();
        if (cookies != null) {
            builder.addHeader("cookie", cookies);
        }
        Request request = builder
                .url(url)
                .build();
        try {
            Response response = mClient.newCall(request).execute();
            String contentType = response.header("Content-Type");
            String body = null;
            if (contentType == null || !contentType.contains("image")) {
                byte[] b = response.body().bytes();
                String charset = getCharset(new String(b));
                body = new String(b, charset);
            }
            response.close();
            return body;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String url, String paramsString, String cookies) {
        String[] paramStrings = paramsString.split("&");
        FormBody.Builder formBody = new FormBody.Builder();
        for (String paramString : paramStrings) {
            String[] pram = paramString.split("=");
            if (pram.length != 2) continue;
            formBody.add(pram[0], pram[1]);
        }
        RequestBody requestBody = formBody.build();
        return post(url, requestBody, cookies);
    }
    
    public static String post(String url, RequestBody requestBody, String cookies){
        if (url == null || !url.startsWith("http")) {
            Logger.d("HViewerHttpClient", "url = "+url);
            return null;
        }
        HRequestBuilder builder = new HRequestBuilder();
        if (cookies != null) {
            builder.addHeader("cookie", cookies);
        }
        Request request = builder
                .url(url)
                .post(requestBody)
                .build();
        try {
            Response response = mClient.newCall(request).execute();
            String contentType = response.header("Content-Type");
            String body = null;
            if (contentType == null || !contentType.contains("image")) {
                byte[] b = response.body().bytes();
                String charset = getCharset(new String(b));
                body = new String(b, charset);
            }
            response.close();
            return body;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得字符编码
     */
    public static String getCharset(String html) {
        Document doc = Jsoup.parse(html);
        Elements eles = doc.select("meta[http-equiv=Content-Type]");
        Iterator<Element> itor = eles.iterator();
        while (itor.hasNext())
            return matchCharset(itor.next().toString());
        return "utf-8";
    }

    /**
     * 获得页面字符
     */
    public static String matchCharset(String content) {
        String chs = "utf-8";
        Pattern p = Pattern.compile("(?<=charset=)(.+)(?=\")");
        Matcher m = p.matcher(content);
        if (m.find())
            return m.group();
        return chs;
    }

    // Pre-define error code
    public static class HttpError {
        // Error code constants
        public static final int ERROR_UNKNOWN   = 1000;  //未知错误
        public static final int ERROR_JSON      = 1001;  //JSON解析错误
        public static final int ERROR_NETWORK   = 1009;  //网络错误
        public static final int ERROR_WRONG_URL = 1011;  //URL格式错误

        private int errorCode;
        private String errorString = "";

        public HttpError(int errorCode) {
            this.errorCode = errorCode;
            switch (errorCode) {
                case ERROR_UNKNOWN:
                    errorString = "未知错误";
                    break;
                case ERROR_JSON:
                    errorString = "JSON解析错误";
                    break;
                case ERROR_NETWORK:
                    errorString = "网络错误，请重试";
                    break;
                case ERROR_WRONG_URL:
                    errorString = "URL格式错误";
                    break;
                default:
                    errorString = "未定义的错误码";
                    break;
            }
        }

        public int getErrorCode() {
            return this.errorCode;
        }

        public String getErrorString() {
            return this.errorString;
        }

        @Override
        public String toString() {
            return errorCode + " : " + errorString;
        }
    }
}
