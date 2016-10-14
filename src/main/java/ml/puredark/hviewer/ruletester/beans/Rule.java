package ml.puredark.hviewer.ruletester.beans;

public class Rule {
    public Selector item, idCode, title, uploader, cover, category, datetime, rating, tags, description,
    pictureUrl, pictureThumbnail, pictureHighRes;

	@Deprecated
	public Selector commentItem, commentAvatar, commentAuthor, commentDatetime, commentContent;
	
	public TagRule tagRule;
	public CommentRule commentRule;

    public Rule() {
    }

}
