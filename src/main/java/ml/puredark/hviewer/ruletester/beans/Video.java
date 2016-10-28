package ml.puredark.hviewer.ruletester.beans;


public class Video {
    public int vid;
    public String thumbnail, content;

    public Video(int vid, String thumbnail, String content) {
        this.vid = vid;
        this.thumbnail = thumbnail;
        this.content = content;
    }
    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof Video)) {
            Video item = (Video) obj;
            return equals(item.thumbnail, thumbnail) && equals(item.content, content);
        }
        return false;
    }

    public boolean equals(Object obj1, Object obj2) {
        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }

    @Override
    public String toString(){
        return "vid="+vid+"\n"+
                "thumbnail="+thumbnail+"\n"+
                "content="+content+"\n";
    }
}
