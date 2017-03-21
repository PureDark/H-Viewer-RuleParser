package ml.puredark.hviewer.ruletester.beans;

import java.lang.reflect.Field;
import java.util.List;

public class Collection{
    public int cid;
    public String idCode = "";
    public String title = "", uploader = "", cover = "", category = "", datetime = "";
    public String description;
    public float rating;
    public String referer;
    public List<Tag> tags;
    public List<Picture> pictures;
    public List<Video> videos;
    public List<Comment> comments;

    public Collection(int cid) {
        this.cid = cid;
    }

    public Collection(int cid, String idCode, String title, String uploader, String cover, String category,
                      String datetime, String description, float rating, String referer, List<Tag> tags,
                      List<Picture> pictures, List<Comment> comments) {
        this.cid = cid;
        this.idCode = idCode;
        this.title = title;
        this.uploader = uploader;
        this.cover = cover;
        this.category = category;
        this.datetime = datetime;
        this.description = description;
        this.rating = rating;
        this.referer = referer;
        this.tags = tags;
        this.pictures = pictures;
        this.comments = comments;
    }

    public void fillEmpty(Collection collection) {
        Field[] fs = Collection.class.getDeclaredFields();
        try {
            for (Field f : fs) {
                f.setAccessible(true);
                Object v1 = f.get(this);
                Object v2 = f.get(collection);
                if (v1 == null || "".equals(v1))
                    f.set(this, v2);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
