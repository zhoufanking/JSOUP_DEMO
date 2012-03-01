package ZHOUFAN_JSOUP_DEMO;

import java.io.File;
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

	public boolean Connect(String httpAddress) throws IOException {
		if (httpAddress.equals(null)) {
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
		// try {
		// doc = Jsoup.connect(_URL).userAgent("Mozilla").get();
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		// }
		// System.out.println(doc.title());

		Elements links = doc.select("a[href]");

		return links;

	}

	public Vector<String> getHeadLine() {
		Elements news = null;
		news = doc.getElementsByClass("titlelnk");

		Vector<String> newsVec = new Vector<String>();

		if (news != null) {
			// System.out.println(news.text());

			for (Element item : news) {
				newsVec.add(item.text().replace("\u2014", "-")); // \u2014
																	// unicode中的空格
			}
		}
		return newsVec;
	}

	public Vector<String> getHeadLineSummery() {
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

	public void initContent() {
		// if cached file exists, load the initial content from the file;
		// else create a cache file, fetch the web pages print out the contents,
		// and then store the contents to the cache file.
		File storage = new File("./" + caches);
		if (storage.exists() || storage.length() != 0) {

			loadFromCache();
		} else {

			loadFromWeb();
			updateCache();
		}
	}

	public void printContent(int pages, Vector<String> item,
			Vector<String> item_summery) {
		System.out.println("====================================");
		System.out.println("第" + pages + "页");
		for (int i = 0; i < item.size(); i++) {
			System.out.println(i + item.get(i));
			System.out.println(item_summery.get(i));
		}
		System.out.println("done");
	}

	// TODO need implement
	private void loadFromCache() {

	}

	// TODO need implement
	private void loadFromWeb() {

		try {

			Connect(WEB_ADDRESS);

		} catch (IOException e) {

			e.printStackTrace();
		}

		int pages = 1;
		Vector<String> item;
		Vector<String> item_summery;

		for (; pages < 3; pages++) {

			if (pages == 1) {

				item = getHeadLine();
				item_summery = getHeadLineSummery();

			} else {

				try {

					Connect(WEB_ADDRESS + "//p" + pages);

				} catch (IOException e) {

					e.printStackTrace();

				}

				item = getHeadLine();

				item_summery = getHeadLineSummery();
			}

			printContent(pages, item, item_summery);
		}
	}

	// TODO need implement
	private void updateCache() {

	}

	// TODO need implement
	// close and free all recourses
	public void cleanAll() {

	}

	private Document doc;
	private String _URL;
	private final String caches = "cache.txt";
	private final String WEB_ADDRESS = "http://www.cnblogs.com";

}
