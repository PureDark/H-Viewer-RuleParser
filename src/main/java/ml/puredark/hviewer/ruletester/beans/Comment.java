package ml.puredark.hviewer.ruletester.beans;

public class Comment {
    public int cid;
    public String avatar, author, datetime, content;
    public String referer;

    public Comment(int cid, String avatar, String author, String datetime, String content, String referer) {
        this.cid = cid;
        this.avatar = avatar;
        this.author = author;
        this.datetime = datetime;
        this.content = content;
        this.referer = referer;
    }
}
