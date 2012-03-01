package ZHOUFAN_JSOUP_DEMO;

import java.io.IOException;
import java.util.Vector;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test {

	/**
	 * test the getlinks routin;
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String WEB_ADDRESS = "http://www.cnblogs.com";
		// System.out.println(doc.html());
		ParseURL cnblogs = new ParseURL();
		//cnblogs.setURL(WEB_ADDRESS);

		try {
			cnblogs.Connect(WEB_ADDRESS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		 * System.out.println(id++ + link.text().replace("\u00a0", ""));
		 * 
		 * System.out.println(relHref); System.out.println(absHref);
		 * 
		 * }
		 */
		
		int pages = 1;
		Vector<String> item;
		Vector<String> item_summery;

		for (; pages < 3; pages++) {
			System.out.println("====================================");
			System.out.println("第" + pages + "页");
			if (pages < 2) {
				item = cnblogs.getHeadLine();
				item_summery = cnblogs.getHeadLineSummery();
			} else {
				try {
					cnblogs.Connect(WEB_ADDRESS + "//p" + pages);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				item = cnblogs.getHeadLine();
				item_summery = cnblogs.getHeadLineSummery();
			}
			for (int i = 0; i < item.size(); i++) {
				System.out.println(i+item.get(i));
				System.out.println(item_summery.get(i));
				//System.out.println();
			}
		}
		System.out.println("done");
	}

}
