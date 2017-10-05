import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlUnorderedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
				WebClient webClient = new WebClient(BrowserVersion.CHROME);
				webClient.getOptions().setCssEnabled(false);
				webClient.getOptions().setUseInsecureSSL(false);
				webClient.getOptions().setJavaScriptEnabled(true);
				//webClient.setAjaxController(new NicelyResynchronizingAjaxController());
				HtmlPage htmlPage = webClient.getPage("http://www.sse.com.cn/assortment/stock/list/share/");
				webClient.waitForBackgroundJavaScript(30000);
				HtmlUnorderedList divKD=(HtmlUnorderedList)htmlPage.querySelector(".pagination");
				DomNodeList<HtmlElement> nodeKD=divKD.getElementsByTagName("li");  
				for(int m=0;m<5;m++)
				{  
					HtmlElement heLi=nodeKD.get(m); 
					htmlPage=(HtmlPage)heLi.click();
					webClient.waitForBackgroundJavaScript(30000);
					List<String> allname=htmlPage.getByXPath("//table[@id='table search_']/tbody/tr/td[2]/text()");
					//List<String> allname=htmlPage.getByXPath("//*[@id='tableData_']/div[2]/table/tbody//tr/td[2]/text()");
					for(int i=0;i<allname.size();i++)
					{
						int num=m*25+i;
						System.out.println("nameof"+num+allname.get(i));
					}
                 
				}  
				webClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
	}

}
