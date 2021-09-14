package filePac;
import java.io.*;



public class SharePointFileCheck {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Console con=System.console();
		BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));  
		System.out.println("Enter the required folder");
		String Folder=obj.readLine();
		File path=new File("C:\\Users\\premachandras\\OneDrive - Packt\\"+Folder);
		Boolean check=path.exists();
		if(check == false)
		{
			System.out.println("Foler is not available on you local onedrive \n Please sysc the folder using following steps: \n 1.Goto online onddrive"
					+ "\n 2. If the folder is shared by anyone then goto shraed with me \n 3.open the folder \n 4. above you get sync click on it the folder is sysc to you ondrive");
			
		}
		else
		{
			System.out.println("Enter the file Name with type");
			String fileName=obj.readLine();
			File filePath=new File("C:\\Users\\premachandras\\OneDrive - Packt\\"+Folder+"\\"+fileName);
			Boolean pathCheck=filePath.exists();
			if(pathCheck==false)
			{
				System.out.println("File is not available.\n Could you confirem the file name and type");
			}
			else
			System.out.println("File is available on local macine");
		}
	}

}
