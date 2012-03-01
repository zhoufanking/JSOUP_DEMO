package ZHOUFAN_JSOUP_DEMO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParesString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String html = "<html><head><title>First parse</title></head>"
				  + "<body><p>Parsed HTML into a doc.</p></body></html>";
		Document doc = Jsoup.parse(html);
		
		System.out.println(doc.title());
		System.out.println(doc.html());

	}

}
