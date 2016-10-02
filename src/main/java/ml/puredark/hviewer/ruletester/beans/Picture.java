package ml.puredark.hviewer.ruletester.beans;

public class Picture {
    public int pid;
    public String thumbnail, url, pic, highRes;
    public String referer;

    public Picture(int pid, String url, String thumbnail, String highRes, String referer) {
        this.pid = pid;
        this.url = url;
        this.thumbnail = thumbnail;
        this.highRes = highRes;
        this.referer = referer;
    }
}
