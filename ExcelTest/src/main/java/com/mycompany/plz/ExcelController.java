package com.mycompany.plz;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public class ExcelController {
	
	UserService userService;
	
	 @ResponseBody
	    @RequestMapping(value = "/excelUploadAjax", method = RequestMethod.POST)
	    public ModelAndView excelUploadAjax(MultipartHttpServletRequest request)  throws Exception{
	        MultipartFile excelFile =request.getFile("excelFile");
	        System.out.println("���� ���� ���ε� ��Ʈ�ѷ�");
	        if(excelFile==null || excelFile.isEmpty()){
	            throw new RuntimeException("���������� ���� �� �ּ���.");
	        }
	        
	        File destFile = new File("C:\\"+excelFile.getOriginalFilename());
	        try{
	            excelFile.transferTo(destFile);
	        }catch(IllegalStateException | IOException e){
	            throw new RuntimeException(e.getMessage(),e);
	        }
	        
	        //Service �ܿ��� ������ �ڵ� 
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
	        
	        //userService.excelUpload(destFile); //���� �κ��� �����Ѵ�.
	        
	        //FileUtils.forceDelete(destFile.getAbsolutePath());
	        
	        ModelAndView view = new ModelAndView();
	        view.setViewName("/index");
	        return view;
	    }


}
