package safari;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SafariMetaData {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file=new File("\\\\\\\\192.168.0.200\\\\Products\\\\Work\\\\Dynamic\\\\Video Distribution Project\\\\Udemy Automation\\\\V11347.xlsx");

		FileInputStream fls=new FileInputStream(file);
		 int se=1;

		XSSFWorkbook wb=new XSSFWorkbook(fls);

		XSSFSheet sheet1= wb.getSheetAt(0);

		int rownum=sheet1.getLastRowNum();

		StringBuilder data=new StringBuilder();
		StringBuilder data1=new StringBuilder();

		DataFormatter formatter = new DataFormatter();

		HashMap<String,String> hm=new HashMap<String,String>();  

		for(int i=0;i<=rownum;i++)

		{

			// for put coloum 1 value to hash map

			String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(0));

			// for puting coloum 2 value to hash map

			String kk1=formatter.formatCellValue(sheet1.getRow(i).getCell(1));				

			String a=kk;

			String b=kk1;

			// maping the excel value to hash map

			hm.put(a,b);

	}
		

		for(int j=1;j<30;j++)

		{

			if(hm.get("Section "+j)==null)

			{

				break;

			}

			else

			{

					
					data1.append("<part id=\"P"+j+"\" seq=\""+se+"\">\n");
					String secname=hm.get("Section "+j);
					  secname=secname.replaceAll("&", "&amp;");
					  secname=secname.replaceAll("\"", "&quot;");
					data1.append("<title>Chapter "+j+" : "+secname.trim()+"</title>\n");
					//data1.append("<title>"+hm.get("Section "+j).trim()+"</title>\n");
					se+=1;

				for(int k=1;k<30;k++)

				{

					if(hm.get(j+"."+k)==null)

					{

						break;

					}

					else

					{

						String kkr=hm.get(j+"."+k);

						//data.append(kkr+"\n ---\n");

						for(int l=1;l<2;l++)

						{

							if(hm.get("Clip Section "+j+"."+k+":"+l)==null)

							{

								break;

							}

							else

							{

								//System.out.println(hm.get(j+"."+k+":"+l));
								String title=hm.get(j+"."+k);
								title=title.replaceAll("&", "&amp;");
								title=title.replaceAll("\"", "&quot;");
								String Desc=hm.get("Clip Section "+j+"."+k+":"+l);
								Desc=Desc.replaceAll("&", "&amp;");
								Desc=Desc.replaceAll("&", "&amp;");
								data.append("<clip linkid=\"video"+j+"_"+k+"\" preview=\"no\" title=\""+title.trim()+"\"> \n <description>"+Desc.trim()+"</description>\n </clip>");


								//data.append("<clip linkid=\"video"+j+"_"+k+"\" preview=\"no\" title=\""+hm.get(j+"."+k).trim()+"\"> \n <description>"+hm.get("Clip Section "+j+"."+k+":"+l).trim()+"</description>\n </clip>");

								data1.append("<chapter id=\"video"+j+"_"+k+"\" seq=\""+se+"\">\n");
								
								data1.append("<title>"+title.trim()+"</title>\n");
								//data1.append("<title>"+hm.get(j+"."+k).trim()+"</title>\n");
								data1.append("<section role=\"clip-description\">"+Desc.trim()+"</section>\n<para/>\n</chapter>");
								//data1.append("<section role=\"clip-description\">"+hm.get("Clip Section "+j+"."+k+":"+l).trim()+"</section>\n<para/>\n</chapter>");
								
								se+=1;

							}

							data.append("\n");
							data1.append("\n");

						}

					}

				}
				data1.append("</part> \n");
				

			}

			

		}

		System.out.println(data);
		System.out.println("_________________________________________________________________________________________");
		System.out.println("\n");
		
		System.out.println(data1);
	}
	

}
