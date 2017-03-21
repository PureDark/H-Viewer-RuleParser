package ml.puredark.hviewer.ruletester.beans;

import java.util.List;
import java.util.Map;

import ml.puredark.hviewer.ruletester.RuleParser;


public class Site  {
    public final static String FLAG_NO_COVER = "noCover";
    public final static String FLAG_NO_TITLE = "noTitle";
    public final static String FLAG_NO_RATING = "noRating";
    public final static String FLAG_NO_TAG = "noTag";
    public final static String FLAG_WATERFALL_AS_LIST = "waterfallAsList";
    public final static String FLAG_WATERFALL_AS_GRID = "waterfallAsGrid";
    public final static String FLAG_SECOND_LEVEL_GALLERY = "secondLevelGallery";
    public final static String FLAG_REPEATED_THUMBNAIL = "repeatedThumbnail";
    public final static String FLAG_SINGLE_PAGE_BIG_PICTURE = "singlePageBigPicture";
    public final static String FLAG_PRELOAD_GALLERY = "preloadGallery";
    public final static String FLAG_ONE_PIC_GALLERY = "onePicGallery";
    public final static String FLAG_EXTRA_INDEX_INFO = "extraIndexInfo";
    public final static String FLAG_JS_NEEDED_ALL = "jsNeededAll";
    public final static String FLAG_JS_NEEDED_INDEX = "jsNeededIndex";
    public final static String FLAG_JS_NEEDED_GALLERY = "jsNeededGallery";
    public final static String FLAG_JS_NEEDED_PICTURE = "jsNeededPicture";
    public final static String FLAG_JS_SCROLL = "jsScroll";
    public final static String FLAG_IFRAME_GALLERY = "iframeGallery";
    public final static String FLAG_POST_ALL = "postAll";
    public final static String FLAG_POST_INDEX = "postIndex";
    public final static String FLAG_POST_GALLERY = "postGallery";
    public final static String FLAG_POST_PICTURE = "postPicture";


    public int sid, gid;
    public String title = "";
    public String indexUrl = "", galleryUrl = "", searchUrl = "", loginUrl = "";
    public List<Category> categories;
    public Rule indexRule, galleryRule, searchRule, extraRule;
    public List<String> tagSource;
    public TagRule tagRule;
    public int versionCode;

    @Deprecated
    public Selector picUrlSelector;

    public String cookie = "";
    public String flag = "";
    public int index;

    public Site() {
    }

    public Site(int sid, String title, String indexUrl, String galleryUrl, String searchUrl, String loginUrl,
                Rule indexRule, Rule galleryRule, Rule searchRule, Rule extraRule, String flag) {
        this.sid = sid;
        this.title = title;
        this.indexUrl = indexUrl;
        this.galleryUrl = galleryUrl;
        this.searchUrl = searchUrl;
        this.loginUrl = loginUrl;
        this.indexRule = indexRule;
        this.galleryRule = galleryRule;
        this.searchRule = searchRule;
        this.extraRule = extraRule;
        this.flag = flag;
    }
   

    public boolean hasFlag(String flag) {
        if (this.flag == null)
            return false;
        else
            return this.flag.contains(flag);
    }

    public String getListUrl(String url, int page, String keyword, List<Collection> collections) {
        Object[] array = (collections != null) ? collections.toArray() : null;
        return RuleParser.parseUrl(url, page, "", keyword, array);
    }

    public String getGalleryUrl(String idCode, int page, List<Picture> pictures) {
        return getGalleryUrl(galleryUrl, idCode, page, pictures);
    }

    public String getGalleryUrl(String inUrl, String idCode, int page, List<Picture> pictures) {
        Object[] array = (pictures != null) ? pictures.toArray() : null;
        return RuleParser.parseUrl(inUrl, page, idCode, "", array);
    }
    
}
