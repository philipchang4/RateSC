package RateSC.RateSCAPI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PopulateDataCSCIClasses {
	
	public static void main(String[] args) {
		final String url= "https://classes.usc.edu/term-20203/classes/csci/";
				
		try {
			Document document= Jsoup.connect(url).get();
			Elements temp= document.select("a.courselink");
			//System.out.println(temp.outerHtml());
			for (Element id: temp) {
				String fullText= id.text();
				int end= fullText.indexOf('(');
				int start= fullText.indexOf("CSCI");
				String classString= fullText.substring(start, end-1);
				DataModify.getData().addRatedObject(classString, "3");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
