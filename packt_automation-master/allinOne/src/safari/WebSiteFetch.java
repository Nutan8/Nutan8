package safari;

import java.io.IOException;

import javax.lang.model.element.Element;

import org.apache.poi.hslf.util.SystemTimeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebSiteFetch {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String url = "https://www.packtpub.com/application-development/reactive-swift-4-programming-video";
        Document document = Jsoup.connect(url).get();
        Elements divTags =document.select("div.book-info-isbn13");
        String x=divTags.text();
        System.out.println(x.substring(x.length()-13, x.length()));
        Elements desc=document.select("div .book-info-bottom-indetail-text p");
        String kkr=desc.toString();
        kkr=kkr.replaceAll("style=\"color: #fa8d11;\"","");
      kkr= kkr.replaceAll("target=\"blank\"", "");
     kkr=kkr.replaceAll("<span id=\"description\" class=\"sugar_field\">", "");
     System.out.println(kkr.replaceAll("<div class=\"book-info-bottom-indetail-text\" itemprop=\"description\"> ", ""));
      
        
       // String epic="https://www.packtpub.com/node/33349/edit";
      
        
	}

}
