package fileRename;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class IndexFileRun {

	static String [] arrOfStr;
	static ArrayList<String> sec=new ArrayList<>();
	static ArrayList<String> secDec=new ArrayList<>();
	static List<XWPFTableRow> tableRow;
	static List<XWPFTableRow> tableRow1;
	static List<XWPFTable> tableList;
	static int count=0;
	static int count1=1;
	static String mainContent="";
	static String sectionContent="";
	static String htmlStarting="<!DOCTYPE html>\r\n" + 
			"<html>\r\n" + 
			"   <head>\r\n" + 
			"      <script src=\"js/jquery-1.8.0.js\"></script>\r\n" + 
			"      <script src=\"js/jquery.roundabout.js\"></script>\r\n" + 
			"      <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"/>\r\n" + 
			"      <link href=\"video-js.css\" rel=\"stylesheet\"/>\r\n" + 
			"      <script src=\"js/video.js\"></script>\r\n" + 
			"      <script src=\"js/jquery.cookie.js\"></script>\r\n" + 
			"      <script src=\"js/bootstrap.min.js\"></script>\r\n" + 
			"      <script src=\"javascript.js\"></script>\r\n" + 
			"      <title>Packt Video player</title>\r\n" + 
			"   </head>\r\n" + 
			"   <body>\r\n" + 
			"      <img class=\"logo\" src=\"images/logo.png\"/>\r\n" + 
			"      <div class=\"banner\">\r\n" + 
			"         <ul id =\"nav\">\r\n" + 
			"            <li>\r\n" + 
			"               <div class=\"icon orange\" style=\"background-position: 24px -56px;\" data-horiz=\"24px\"></div>\r\n" + 
			"               <a href=\"#\" id=\"main-menu\" class=\"navig\">Main menu </a>\r\n" + 
			"            </li>\r\n" + 
			"            <li>\r\n" + 
			"               <div id=\"credits-icon\" class=\"icon\" style=\"background-position: 0px 0px;\" data-horiz=\"0px\"></div>\r\n" + 
			"               <a href=\"credits.html\" id=\"credits\" class=\"navig\">Credits </a>\r\n" + 
			"            </li>\r\n" + 
			"            <li>\r\n" + 
			"               <div class=\"icon\" style=\"background-position: 54px 0px;\" data-horiz=\"54px\"></div>\r\n" + 
			"               <a href=\"overview.html\" id=\"course\" class=\"navig\"> Course Overview </a>\r\n" + 
			"            </li>\r\n" + 
			"            <!--<li><div class=\"icon\" style=\"background-position: 54px 0px;\" data-horiz=\"54px\"></div><a href=\"Videos/Packt.m3u\" id=\"playlist\" class=\"navig\"> Playlist </a></li>-->\r\n" + 
			"         </ul>\r\n" + 
			"      </div>\r\n" + 
			"      <div class=\"gradient\">\r\n" + 
			"         <img src=\"images/gradient.png\" style=\"width: 100%; background-repeat: repeat-x;\">\r\n" + 
			"      </div>\r\n" + 
			"      <div id=\"wrapper\">\r\n" + 
			"         <div class=\"intro\">\r\n" + 
			"            <div class=\"inner\">\r\n" + 
			"               <h1>";
	static String htmlstartEnd="</h1>\r\n" + 
			"            </div>\r\n" + 
			"         </div>\r\n" + 
			"         <div class=\"right\">\r\n" + 
			"            <div id=\"main_next\" class=\"next\"></div>\r\n" + 
			"         </div>\r\n" + 
			"         <div class=\"left\">\r\n" + 
			"            <div id=\"main_prev\" class=\"prev\"></div>\r\n" + 
			"         </div>\r\n" + 
			"         <div class=\"right-bottom\">\r\n" + 
			"            <div id=\"button-next\" class=\"next-bottom\"></div>\r\n" + 
			"         </div>\r\n" + 
			"         <div class=\"left-bottom\">\r\n" + 
			"            <div id=\"button-prev\" class=\"prev-bottom\"></div>\r\n" + 
			"         </div>\r\n" + 
			"         <div class=\"back\">\r\n" + 
			"         </div>\r\n" + 
			"         <div>";
	static String htmlMidEnding="</div> ";
	static String htmlEnding="		 <div id=\"video-wrapper\">\r\n" + 
			"            <div id=\"video-player\" class=\"video\">\r\n" + 
			"            </div>\r\n" + 
			"            <a href=\"#\" id=\"largerqt\">View Larger</a>\r\n" + 
			"            <h1></h1>\r\n" + 
			"            <span></span>\r\n" + 
			"            <a href=\"#\" class=\"watch-next-video btn btn-primary  navigation\" style=\"left: 550px;\" data-next=\"\" data-video=\"\" data-prev=\"\">Next</a>\r\n" + 
			"            <a href=\"#\" class=\"watch-prev-video btn btn-primary  navigation\" style=\"left: 420px;\"  data-prev=\"\" data-video=\"\" data-poster=\"\">Prev</a>\r\n" + 
			"            <div class=\"btn back-video \"><strong>Close</strong>\r\n" + 
			"            </div>\r\n" + 
			"         </div>\r\n" + 
			"      </div>\r\n" + 
			"      <div class=\"quicktime-full\">\r\n" + 
			"         <div class=\"full-player\">\r\n" + 
			"         </div>\r\n" + 
			"      </div>\r\n" + 
			"   </body>\r\n" + 
			"</html>";
	public IndexFileRun() {
		// TODO Auto-generated constructor stub
	}

	public String  runfile(String titleName,String path)
	{
		int c=0;
		String sb="";
		try {
			FileInputStream fis = new FileInputStream(path+".docx");
			XWPFDocument xdoc=new XWPFDocument(OPCPackage.open(fis));
			Iterator<IBodyElement> bodyElementIterator = xdoc.getBodyElementsIterator();
			/*while(bodyElementIterator.hasNext()) {
				
			  IBodyElement element = bodyElementIterator.next();
		          if("TABLE".equalsIgnoreCase(element.getElementType().name())) {
			      tableList =  element.getBody().getTables();
//			     
			     break;
			     
			  }
		       
		      
		     }*/
			// code for paragraph start
			
			while(bodyElementIterator.hasNext())
			{
				IBodyElement element = bodyElementIterator.next();
				if("paragraph".equalsIgnoreCase(element.getElementType().name())) {
				     List<XWPFParagraph> para=element.getBody().getParagraphs();
				     for(XWPFParagraph prag:para)
				     {
				    	 String check=prag.getText().toString().trim();
				    	 //check=check.replaceAll("\\s+","").trim();
				    	 if(check.length()>0)
				    	 {
				    		 System.out.println(check);
				    		 sb+=check;
				    		 sb+="####";
				    	 }
				    	
				     }
				     break;
				}
			}
			
	          
		    } catch(Exception ex) {
			System.out.println(ex.toString());
		    } 
		//******************spliting into parts in string **********************************
		arrOfStr = sb.split("####");
		//************* assign values in array list ***************************8
		 for(int i=0;i<arrOfStr.length-1;i+=2)
	      {
	    	 //System.out.println("SECTION "+arrOfStr[i]);
	    	 // System.out.println("DESCP "+arrOfStr[i+1]);
	    	  System.out.println(arrOfStr[i]);
	    	  sec.add(arrOfStr[i]);
	    	  secDec.add(arrOfStr[i+1]);
	      }
		
		// ****************** calling tables for Main Section **************************************
	  
		/*for (XWPFTable table: tableList){
		       // System.out.println("Total Number of Rows of Table:"+table.getNumberOfRows());
					
		        	 tableRow=table.getRows();
		        	{
		        		mainContent+="\n<li> \n <img src=\"images/image.jpg\" class=\"image-holder\"/>";
		        		mainContent+="\n<h1>"+sec.get(count)+"</h1> \n";
		        		mainContent+="<p>"+secDec.get(count)+"\n <ul>\n";
		        			int r=tableRow.size();
		        			//System.out.println(r);
		        			for(int i=1;i<r;i++)
		        			{
		        				
		        				int cou=tableRow.get(i).getTableICells().size();
		        				mainContent+="<li>"+tableRow.get(i).getCell(1).getText().trim()+"</li>\n";
		        			}
		        			
		        			mainContent+="</ul> \n </p>\n";
		        			mainContent+="<div id=\"#content-"+(count+1)+"\" class=\"btn btn-primary browse\">Browse Videos</div>\n </li>";
		        	}
		        	count++;
  	  
		     }*/
		
		
	  
		String dCon=" <div id=\"content-main\">\r\n <ul class=\"move\">"+mainContent+"</ul>\r\n </div>";
		
		//************************ data for Clip section ***************************************
		
		  
		/*	for (XWPFTable table1: tableList){
			       // System.out.println("Total Number of Rows of Table:"+table.getNumberOfRows());
			
			        	 tableRow1=table1.getRows();
			        	{
			        		sectionContent+="<div class=\"chapter\" id=\"content-"+count1+"\" style=\"position: relative; top: -500px; display: none;\">\r\n" + 
	        						"               <ul class=\"move\">\r\n";
			        			int r=tableRow1.size();
			        			//System.out.println(r);
			        			for(int i=1;i<r;i++)
			        			{
			        				String courseId=tableRow1.get(i).getCell(0).getText();
			        				//System.out.println("course Id"+courseId);
			        				courseId=courseId.replace(".", "_");
			        				//System.out.println("course Id"+courseId);
			        				sectionContent+="                  <li>\r\n" + 
	        						"                     <img src=\"images/image.jpg\" class=\"image-holder\"/> \n";
			        				
			        				sectionContent+="<h1>"+tableRow1.get(i).getCell(0).getText() + " "+tableRow1.get(i).getCell(1).getText()+"</h1>\n";
			        				sectionContent+="<span>"+tableRow1.get(i).getCell(2).getText();
			        				int cou=tableRow1.get(i).getTableICells().size();
			        				if(tableRow1.get(i).getCell(3).getText().length()>1)
			        				{
			        					sectionContent+="\n <ul>";
			        				for(int j=3;j<cou;j++)
				        			{	
				        				
				        				String cellValue=tableRow1.get(i).getCell(j).getText();
				        				if(cellValue.length()>0)
				        				{
				        					sectionContent+="\n<li>";
				        					sectionContent+=cellValue;
				        					sectionContent+="</li>";
				        					//System.out.print(tableRow.get(i).getCell(j).getText());
				        				}
				        			}
			        				sectionContent+="\n</ul>";
			        				}
			        				sectionContent+="</span> \n ";
			        				sectionContent+="<div id=\"video"+courseId+"\" class=\"btn btn-small btn-primary watch\" data-video=\"videos/video1_1.mp4\" data-poster=\"images/videoImage.jpg\">Watch Video</div>\r\n" + 
			        						"                     <a href=\"videos/video"+courseId+".mp4\" class=\"btn btn-small btn-primary download\" target=\"_blank\">Download</a>\r\n" + 
			        						"                     <div class=\"last-hidden\">Last Viewed</div>\n";
			        				sectionContent+="</li>\n";
			        			}
			        			sectionContent+="</ul>  \n </div>\n";
			        			System.out.println();count1+=1;
			        	}
			        	
	  	  
			     } */
		
		
	//System.out.println(htmlStarting+"\n"+dCon+sectionContent+"\n"+htmlMidEnding+"\n"+htmlEnding);
	String king=htmlStarting+titleName+htmlstartEnd+"\n"+dCon+sectionContent+"\n"+htmlMidEnding+"\n"+htmlEnding;
	king=king.trim();
	
	return king;
		
	}
}
