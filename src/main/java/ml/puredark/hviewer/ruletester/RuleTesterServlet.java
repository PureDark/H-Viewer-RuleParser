package ml.puredark.hviewer.ruletester;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ml.puredark.hviewer.ruletester.beans.Collection;
import ml.puredark.hviewer.ruletester.beans.Rule;
import ml.puredark.hviewer.ruletester.beans.Site;
import ml.puredark.hviewer.ruletester.http.HViewerHttpClient;
import ml.puredark.hviewer.ruletester.http.Logger;

import com.google.gson.Gson;

public class RuleTesterServlet extends HttpServlet {

	public RuleTesterServlet() {
		super();
	}
	
	@Override
	public void destroy() {
		super.destroy(); 
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		String siteJson = request.getParameter("site");
		if("getList".equals(action)){
			String targetUrl = request.getParameter("targetUrl");
			if(!TextUtils.isEmpty(siteJson) && !TextUtils.isEmpty(targetUrl)){
				Gson gson = new Gson();
				Site site = gson.fromJson(siteJson, Site.class);
				List<Collection> collections = getCollections(site, targetUrl);
				String output = gson.toJson(collections);
				out.println(output);
			}
		}else if("getDetail".equals(action)){
			String collectionJson = request.getParameter("collection");
			if(!TextUtils.isEmpty(collectionJson) && !TextUtils.isEmpty(collectionJson)){
				Gson gson = new Gson();
				Site site = gson.fromJson(siteJson, Site.class);
				Collection collection = gson.fromJson(collectionJson, Collection.class);
				collection = getCollectionDetail(site, collection);
				String output = gson.toJson(collection);
				out.println(output);
			}
		}
		
		out.flush();
		out.close();
	}
	
	private List<Collection> getCollections(Site site, String targetUrl) {
        final Rule rule = site.indexRule;
        final String url = targetUrl;
        Logger.d("getCollections", url);
        String html = HViewerHttpClient.get(url, site.cookie);
        List<Collection> collections = new ArrayList<Collection>();
        collections = RuleParser.getCollections(collections, html, rule, url);
        return collections;
    }
	
	private Collection getCollectionDetail(Site site, Collection collection) {
        final String url = site.getGalleryUrl(collection.idCode, 1);
        Logger.d("getCollectionDetail", url);
        String html = HViewerHttpClient.get(url, site.cookie);
        collection = RuleParser.getCollectionDetail(collection, html, site.galleryRule, url);
        return collection;
    }


}
