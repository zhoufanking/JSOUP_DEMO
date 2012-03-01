package ZHOUFAN_JSOUP_DEMO;

import java.io.IOException;
import java.util.Vector;

public class test {

	/**
	 * test the getlinks routine;
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		
		// System.out.println(doc.html());
		ParseURL cnblogs = new ParseURL();
		cnblogs.initContent();

		/*
		 * get all links in a web page Elements
		 * 
		 * links =mytool.getLinks(WEB_ADDRESS); int id = 0;
		 * 
		 * for( Element link : links ) { String relHref = link.attr("href");
		 * String absHref = link.attr("abs:href");
		 * 
		 * System.out.println(id++ + link.text().replace("\u00a0", ""));//\u2014
		 * unicode中的破折号
		 * 
		 * System.out.println(relHref); System.out.println(absHref);
		 * 
		 * }
		 */
		cnblogs.cleanAll();
		
	}
}
