package ZHOUFAN_JSOUP_DEMO;

import java.io.IOException;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseURL {

	public ParseURL() {
		doc = null;
		_URL = null;
	}
	
//	
//	public void setURL(String httpAddress){
//		_URL = httpAddress;
//	}
	
	 public boolean Connect(String httpAddress) throws IOException {
		 if(httpAddress.equals(null)){
			 System.out.println("target web address missed!");
			 return false;
		 }
		 _URL = httpAddress;
		 
		 try {
				doc = Jsoup.connect(_URL).userAgent("Mozilla").timeout(3000).get();
			} catch (IOException e) {
				
				throw e;
			}
		return true;
	    }

	/**
	 * @param args
	 */
	public Elements getLinks() {
		// Document doc = null;
//		try {
//			doc = Jsoup.connect(_URL).userAgent("Mozilla").get();
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
		// System.out.println(doc.title());

		Elements links = doc.select("a[href]");

		return links;

	}

	public Vector<String> getHeadLine() {
		Elements news = null;
		news = doc.getElementsByClass("titlelnk");
		
		Vector<String> newsVec  = new Vector<String>(); 
		

		if (news != null) {
			// System.out.println(news.text());

			for (Element item : news) {
				newsVec.add(item.text().replace("\u2014", "-")); //\u2014  unicode中的空格
			}
		}
		return newsVec;
	}
	
	public Vector<String> getHeadLineSummery(){
		Elements summeries = null;
		summeries = doc.getElementsByClass("post_item_summary");
		
		Vector<String> summeryVec = new Vector<String>();
		
		if (summeries != null) {
			// System.out.println(news.text());

			for (Element summery : summeries) {
				summeryVec.add(summery.text().replace("\u2014", "-"));	
			}
		}
		return summeryVec;
	}
	
	public void printContent(int pages, Vector<String> item, Vector<String>item_summery){
		System.out.println("====================================");
		System.out.println("第" + pages + "页");
		for (int i = 0; i < item.size(); i++) {
			System.out.println(i+item.get(i));
			System.out.println(item_summery.get(i));
		}
	}

	private Document doc;
	private String _URL;

}
