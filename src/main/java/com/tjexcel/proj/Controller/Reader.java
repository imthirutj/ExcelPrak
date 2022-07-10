package com.tjexcel.proj.Controller;

import java.io.File;
import java.util.*; 
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tjexcel.proj.DAO.ExcelService;
import com.tjexcel.proj.Entity.ExcelEntity;
import com.tjexcel.proj.Repo.ExcelRepo;

@Controller
public class Reader {
	@Autowired 
	private ExcelService excelService;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	
	
	@GetMapping("/caller")
public String Caller() throws IOException {
		
		Reader rd = new Reader();
		
	System.out.println("welcome");
	
	  //Creating a File object for directory
    File directoryPath = new File("D:\\Files\\Folder1");
    //List of all files and directories
    File filesList[] = directoryPath.listFiles();
    System.out.println("List of  specified directory:");
    for(File file : filesList) {
     
  	  if(!file.isFile()) {
       System.out.println("File path: "+file.getAbsolutePath());
       
       FilesReader(file.getAbsolutePath());
  	  }
      
       System.out.println(" ");
    }
	 
    return "index";
	}

@GetMapping("/Reader")
	public String FilesReader(String inp) throws IOException {
		

		
		
		
		// File directoryPath = new File("D:\\Files\\Folder1");
		File directoryPath = new File(inp);
		 File filesList[] = directoryPath.listFiles();
 
		
		 
		 List<String> FFList= new ArrayList();
	      System.out.println("List of files and directories in the specified directory:");
	      for(File file : filesList) {
	    	  if(file.isFile()) {
	         System.out.println("File name: "+file.getName());
	         System.out.println("File path: "+file.getAbsolutePath());
	         System.out.println("Size :"+file.getTotalSpace());
	        FFList.add(file.getAbsolutePath());
	        
	        System.out.println("Reading this "+ file.getAbsolutePath());
    	    Excelreader(file.getAbsolutePath(),file.getName());
	    	  System.out.println( "Finished this "+ file.getAbsolutePath()+"\n");
	      }
	      }
	      System.out.println(" ===end");
	      
	      //
//	      for(String li:FFList) {
//	    	  System.out.println("Reading this "+ li);
//	    	  Excelreader(li);
//	    	  System.out.println( "Finished this "+ li+"\n");
//	      }
	      
		return "index";
	}
	
	
	public  void Excelreader(String li,String nam) throws IOException {
		FileInputStream inputStream = new FileInputStream(li);
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet= workbook.getSheetAt(0);
		
		int rows=sheet.getLastRowNum();
		int cols=sheet.getRow(1).getLastCellNum();
		
		System.out.println("No of rows: "+rows+"\nNo of columns: "+cols);
		
		List<String> totCost=Arrays.asList("TOTALCOST","TOTALPAID","FLATFEE");
		List<String> clNumber=Arrays.asList("CLAIMNUMBER");
		List<String> vend=Arrays.asList("VENDOR","ASSIGNED TO","FIRST PASS VENDOR","BILL TO VENDOR");
		List<String> checkNo= Arrays.asList("CHECKNO");
		List<String> checkDATE= Arrays.asList("CHECKDATEPAID");
		List<String> INDICATOR= Arrays.asList("INDICATOR","FLAT_FEE_INDICATOR");

		
		int ITotalCost=100,IClaimNumber=100, IVendor=100;
		ExcelEntity e= null;;
		
		List<Integer> DefIntOrder=new ArrayList<Integer>();
		List<Integer> IntOrder=new ArrayList<Integer>();
	
		Map<Integer, Integer> m= new HashMap<Integer, Integer>();
		
		for(int r=0;r<1;r++) {
			XSSFRow row=sheet.getRow(r);
			for(int c=0;c<cols;c++) {
				XSSFCell cell=row.getCell(c);
				String val=cell.getStringCellValue();
				if(totCost.contains(val.toUpperCase())) {
					System.out.println("COLLLLL: "+c);
					m.put(1, c);
					
				}
				
				else if(clNumber.contains(val.toUpperCase())) {
					m.put(2, c);
					
				}
				else if(vend.contains(val.toUpperCase())) {
					m.put(3, c);
					
				}
				else if(checkNo.contains(val.toUpperCase())) {
					m.put(4, c);
					
				}
				else if(checkDATE.contains(val.toUpperCase())) {
					m.put(5, c);
					
				}
				else if(INDICATOR.contains(val.toUpperCase())) {
					m.put(6, c);
					
				}
			}
			
			
		}
		
		 for(Map.Entry ma:m.entrySet()){  
			   System.out.println("MAPPP:::"+ma.getKey()+" "+ma.getValue()); 
			   IntOrder.add((Integer) ma.getValue());
			  }  
		
		Object[][] Data= new Object[rows][cols];
		
			for(int r=0;r<=rows;r++) {
				e= new ExcelEntity();
				XSSFRow row=sheet.getRow(r+1);
				if(row==null) {break;}
				
				for(int c=0;c<IntOrder.size();c++) {
					
					System.out.println("HEy hetre: "+IntOrder.get(c));
					XSSFCell cell=row.getCell(IntOrder.get(c));
					
				
					
					
					switch(cell.getCellType()) {
					
					case STRING:{
						String val =cell.getStringCellValue();
						System.out.println("The value is =>"+c+" is " +val);
						if(c==0) {
							e.setTOTALCOST(val);
							System.out.println("GET IS "+e.getTOTALCOST());
						}
						else if(c==1) {
							e.setCLAIMNUMBER(val);
							System.out.println("GET IS "+e.getCLAIMNUMBER());
						}
						else if(c==2) {
							e.setVENDOR(val);
							System.out.println("GET IS "+e.getVENDOR());
						}
						else if(c==3) {
							e.setCHECKNO(val);
							System.out.println("GET IS "+e.getCHECKNO());
						}
						else if(c==4) {
						//	cell.getStringCellValue(); 
							DateFormat formater = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
							Date date;
							try {
								date = formater.parse(val);
								formater.format(date);
								e.setCHECKDATEPAID(date);
								System.out.println(e.getCHECKDATEPAID());
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
							System.out.println("GET IS "+e.getCHECKDATEPAID());
						}
						else if(c==5) {
							e.setINDICATOR(val);
							System.out.println("GET IS "+e.getINDICATOR());
						}
						
						
						
						
						break;
					}
					case NUMERIC:{
						String val = cell.getRawValue();
						System.out.println("The value is Numeric  =>"+c+" is "+cell.getRawValue());
						if(c==0) {
							e.setTOTALCOST(val);
							System.out.println("GET IS "+e.getTOTALCOST());
						}
						else if(c==1) {
							e.setCLAIMNUMBER(val);
							System.out.println("GET IS "+e.getCLAIMNUMBER());
						}
						else if(c==2) {
							e.setVENDOR(val);
							System.out.println("GET IS "+e.getVENDOR());
						}
						else if(c==3) {
							e.setCHECKNO(val);
							System.out.println("GET IS "+e.getCHECKNO());
						}
						else if(c==4) {
//							
//								e.setCHECKDATEPAID(cell.getDateCellValue());
//							
//							
//							System.out.println("GET IS "+e.getCHECKDATEPAID());
						}
						else if(c==5) {
							e.setINDICATOR(val);
							System.out.println("GET IS "+e.getINDICATOR());
						}
						break;
					}
					
					default:
						break;
					}
					System.out.print(" | ");
					
					
				}
				e.setFILENAME(nam);
				excelService.savez(e);
				System.out.println("COST:"+e.getTOTALCOST()+" CLAIMNUMBER:"+e.getCLAIMNUMBER()+" Vendor:"+e.getVENDOR());
				System.out.println("===ROW FINISHED");
				
				}
			
			
		
		
		
		workbook.close();


		
		
	}
	
	
}
