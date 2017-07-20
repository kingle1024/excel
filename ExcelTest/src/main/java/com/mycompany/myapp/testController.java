package com.mycompany.myapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// http://hellogk.tistory.com/14
// http://daydreamer-92.tistory.com/42
@Controller
public class testController {
	@RequestMapping("/test")
	public String test(Model model) throws IOException{
		FileInputStream fis=new FileInputStream("C:\\Spring\\qq.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		int rowindex=0;
		int columnindex=0;
		List listA = new ArrayList();
		//��Ʈ �� (ù��°���� �����ϹǷ� 0�� �ش�)
		//���� �� ��Ʈ�� �б����ؼ��� FOR���� �ѹ��� �����ش�
		XSSFSheet sheet=workbook.getSheetAt(0);
		//���� ��
		int rows=sheet.getPhysicalNumberOfRows();
		for(rowindex=1;rowindex<rows;rowindex++){ 
		    //�����д´�
			System.out.println("�ο� ��"+rows);
		    XSSFRow row=sheet.getRow(rowindex);
		    if(row !=null){
		        //���� ��
		        int cells=row.getPhysicalNumberOfCells();
		        System.out.println("����:"+cells);
		        for(columnindex=0;columnindex<=cells;columnindex++){ // 16
		        	if(rowindex==2 && columnindex==0){
		        		listA.add("</tr><tr>");
		        	}
		        	if(rowindex==3 && columnindex==0){
		        		listA.add("</tr><tr>");
		        	}
		            //������ �д´�
		            XSSFCell cell=row.getCell(columnindex);
		            String value="";
		            
		            //���� ���ϰ�츦 ���� ��üũ
		            if(cell==null){
		                continue;
		            }else{
		                //Ÿ�Ժ��� ���� �б�
		                switch (cell.getCellType()){
		                case XSSFCell.CELL_TYPE_FORMULA:
		                    value=cell.getCellFormula();
		                    break;
		                case XSSFCell.CELL_TYPE_NUMERIC:
		                    value=cell.getNumericCellValue()+"";
		                    break;
		                case XSSFCell.CELL_TYPE_STRING:
		                    value=cell.getStringCellValue()+"";
		                    break;
		                case XSSFCell.CELL_TYPE_BLANK:
		                    value=cell.getBooleanCellValue()+"";
		                    break;
		                case XSSFCell.CELL_TYPE_ERROR:
		                    value=cell.getErrorCellValue()+"";
		                    break;
		                }
		            }
		            if(value.equals("data")){
		            	rows= columnindex;
		            	cells = 0;
		            	continue;
		            }
		            System.out.println("�� �� ���� :"+value);
		            if(value.equals("")){
		            	value = (String) listA.get(listA.size()-1);
		            }
		            listA.add(value);
		        }
		    }
		}
		System.out.println(listA);
		model.addAttribute("listA",listA);
		return "index";
	}
	
	@RequestMapping(value= "/testIndex", method=RequestMethod.POST)
	public String testIndex(){
		
		return "testtest";
	}
	
	@RequestMapping(value= "/testtest", method=RequestMethod.POST)
	public String testtest(Model model) throws IOException{
		FileInputStream fis=new FileInputStream("C:\\Spring\\qq.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		int rowindex=0;
		int columnindex=0;
		List listA = new ArrayList();
		//��Ʈ �� (ù��°���� �����ϹǷ� 0�� �ش�)
		//���� �� ��Ʈ�� �б����ؼ��� FOR���� �ѹ��� �����ش�
		XSSFSheet sheet=workbook.getSheetAt(0);
		//���� ��
		int rows=sheet.getPhysicalNumberOfRows();
		for(rowindex=1;rowindex<rows;rowindex++){ 
		    //�����д´�
			System.out.println("�ο� ��"+rows);
		    XSSFRow row=sheet.getRow(rowindex);
		    if(row !=null){
		        //���� ��
		        int cells=row.getPhysicalNumberOfCells();
		        System.out.println("����:"+cells);
		        for(columnindex=0;columnindex<=cells;columnindex++){ // 16
		        	if(rowindex==2 && columnindex==0){
		        		listA.add("</tr><tr>");
		        	}
		        	if(rowindex==3 && columnindex==0){
		        		listA.add("</tr><tr>");
		        	}
		            //������ �д´�
		            XSSFCell cell=row.getCell(columnindex);
		            String value="";
		            
		            //���� ���ϰ�츦 ���� ��üũ
		            if(cell==null){
		                continue;
		            }else{
		                //Ÿ�Ժ��� ���� �б�
		                switch (cell.getCellType()){
		                case XSSFCell.CELL_TYPE_FORMULA:
		                    value=cell.getCellFormula();
		                    break;
		                case XSSFCell.CELL_TYPE_NUMERIC:
		                    value=cell.getNumericCellValue()+"";
		                    break;
		                case XSSFCell.CELL_TYPE_STRING:
		                    value=cell.getStringCellValue()+"";
		                    break;
		                case XSSFCell.CELL_TYPE_BLANK:
		                    value=cell.getBooleanCellValue()+"";
		                    break;
		                case XSSFCell.CELL_TYPE_ERROR:
		                    value=cell.getErrorCellValue()+"";
		                    break;
		                }
		            }
		            if(value.equals("data")){
		            	rows= columnindex;
		            	cells = 0;
		            	continue;
		            }
		            System.out.println("�� �� ���� :"+value);
		            if(value.equals("")){
		            	value = (String) listA.get(listA.size()-1);
		            }
		            listA.add(value);
		        }
		    }
		}
		
		model.addAttribute("listA",listA);
		return "index";
	}
}
