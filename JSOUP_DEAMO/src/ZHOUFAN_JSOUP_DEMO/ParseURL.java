package ZHOUFAN_JSOUP_DEMO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
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

			loadFromCache(storage);
		} else {

			loadFromWeb();
			updateCache(storage);
		}
	}

	public void printContent(int pages, Vector<String> item,
			Vector<String> item_summery) {
		System.out.println("====================================");
		System.out.println("��"+ pages + "ҳ");
		for (int i = 0; i < item.size(); i++) {
			System.out.println(i + item.get(i));
			System.out.println(item_summery.get(i));
		}
		System.out.println("done");
	}


	private void loadFromCache(File file) {
		
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		
		do{
			System.out.println(input.nextLine());
		}	while(input.hasNextLine());
	}

	
	private void loadFromWeb() {

		try {

			Connect(WEB_ADDRESS);

		} catch (IOException e) {

			e.printStackTrace();
		}

		int pages = 1;

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

	private void updateCache(File file) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		for (int i = 0; i < item.size(); i++) {
			output.println(i + item.get(i));
			output.println(item_summery.get(i));
		}
	}

	// TODO need implement
	// close and free all recourses
	public void cleanAll() {

	}

	private Document doc;
	private String _URL;
	private Vector<String> item;
	private Vector<String> item_summery;
	private final String caches = "cache.txt";
	private final String WEB_ADDRESS = "http://www.cnblogs.com";

}
