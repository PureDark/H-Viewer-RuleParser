package ml.puredark.hviewer.ruletester.beans;

public class Tag {
    public int tid;
    public String title = "";
    public String url;

    public Tag(int tid, String title) {
        this.tid = tid;
        this.title = title;
    }
    
    public Tag(int tid, String title, String url) {
        this.tid = tid;
        this.title = title;
        this.url = url;
    }

}
