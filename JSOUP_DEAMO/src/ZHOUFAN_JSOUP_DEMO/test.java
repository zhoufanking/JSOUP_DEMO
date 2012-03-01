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

		String WEB_ADDRESS = "http://www.cnblogs.com";
		// System.out.println(doc.html());
		ParseURL cnblogs = new ParseURL();
		// cnblogs.setURL(WEB_ADDRESS);

		try {
			cnblogs.Connect(WEB_ADDRESS);
		} catch (IOException e) {

			e.printStackTrace();
		}

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

		int pages = 1;
		Vector<String> item;
		Vector<String> item_summery;

		for (; pages < 3; pages++) {

			if (pages < 2) {
				item = cnblogs.getHeadLine();
				item_summery = cnblogs.getHeadLineSummery();
			} else {
				try {
					cnblogs.Connect(WEB_ADDRESS + "//p" + pages);
				} catch (IOException e) {

					e.printStackTrace();
				}
				item = cnblogs.getHeadLine();
				item_summery = cnblogs.getHeadLineSummery();
			}
			cnblogs.printContent(pages, item, item_summery);
			
		}
		System.out.println("done");
	}

}
