package com.mycompany.plz;

import java.io.File;
import java.util.List;
import java.util.Map;

public class UserService implements UserServiceImpl{

	@Override
	public void excelUpload(File destFile) throws Exception {
		// TODO Auto-generated method stub
		 ExcelReadOption excelReadOption = new ExcelReadOption();
	        excelReadOption.setFilePath(destFile.getAbsolutePath());
	        excelReadOption.setOutputColumns("A","B","C","D","E","F");
	        excelReadOption.setStartRow(2);
	        List<Map<String, String>>excelContent =ExcelRead.read(excelReadOption);
	        
	        for(Map<String, String> article: excelContent){
	            System.out.println(article.get("A"));
	            System.out.println(article.get("B"));
	            System.out.println(article.get("C"));
	            System.out.println(article.get("D"));
	            System.out.println(article.get("E"));
	            System.out.println(article.get("F"));
	        }


	}

	
	
}
