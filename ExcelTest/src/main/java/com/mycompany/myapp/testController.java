package com.mycompany.myapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.vo.workExcel;
// http://hellogk.tistory.com/14
// http://daydreamer-92.tistory.com/42
@Controller
public class testController {
	
	@RequestMapping(value="/uploadTest", method=RequestMethod.POST)
	public void uploadTest(@ModelAttribute workExcel workExcel){
		System.out.println();
		System.out.println("하이염!");
	}
	
	@RequestMapping(value="/test")
	public String test(Model model) throws IOException{
		FileInputStream fis=new FileInputStream("C:\\Spring\\workBook.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		int rowindex=0;
		int columnindex=0;
		List listA = new ArrayList();
		ArrayList<String> headerList = new ArrayList<String>();
		ArrayList<String> array = new ArrayList<String>();
		
		ArrayList<ArrayList<String>> mGroupList = null;
	    ArrayList<String> mChildList = null;
	    
	    HashMap map = new HashMap();
	    
	    mGroupList = new ArrayList<ArrayList<String>>();
        mChildList = new ArrayList<String>();

		//시트 수 (첫번째에만 존재하므로 0을 준다)
		//만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
		XSSFSheet sheet=workbook.getSheetAt(0);
		//행의 수
		int rows=sheet.getPhysicalNumberOfRows();
		int startRowindex = 9;
		XSSFRow row;
		
		
		/*
		 * get header
		 * 
		 */
		for(rowindex=8; rowindex<9; rowindex++){ 
			row=sheet.getRow(rowindex);
		    if(row !=null){ 
		        //셀의 수
		        int cells=row.getPhysicalNumberOfCells();
		        mChildList = new ArrayList<String>();
		        for(columnindex=0; columnindex<cells; columnindex++){
		            //셀값을 읽는다
		            XSSFCell cell=row.getCell(columnindex);
		            String value="";
		            //셀이 빈값일경우를 위한 널체크
		            
		            
		            if(cell==null){
		                continue;
		            }else{
		                //타입별로 내용 읽기
		                switch (cell.getCellType()){
			                case XSSFCell.CELL_TYPE_FORMULA:{
			                	SimpleDateFormat fommatter = new SimpleDateFormat("HH:mm");
			                	value = fommatter.format(cell.getDateCellValue())+"";
			                    break;
			                }
			                case XSSFCell.CELL_TYPE_NUMERIC:{
			                	if( HSSFDateUtil.isCellDateFormatted(cell)){ // 시간 형식
		                			SimpleDateFormat fommatter = new SimpleDateFormat("HH:mm");
			                		value = fommatter.format(cell.getDateCellValue())+"";
			                	} else {
			                		double ddata = cell.getNumericCellValue();
			                		if ( HSSFDateUtil.isValidExcelDate(ddata) ){ // 날짜 형식
			                		SimpleDateFormat fommatter = new SimpleDateFormat("yyyy-MM-dd");
			                		value = fommatter.format(cell.getDateCellValue())+""; 
				                	} else {
				                		value = String.valueOf(ddata);
				                	}
			                	}
			                    break;
			                }
			                case XSSFCell.CELL_TYPE_STRING:
			                    value=cell.getStringCellValue()+"";
			                    break;
			                case XSSFCell.CELL_TYPE_BLANK:
	//		                    value=cell.getBooleanCellValue()+"";
			                    value=cell.toString()+"null";
			                    break;
			                case XSSFCell.CELL_TYPE_ERROR:
			                    value=cell.getErrorCellValue()+"#5번#";
			                    break;
		                }
		            }
		            headerList.add(value);
		            mChildList.add(value);
		        }
		        mGroupList.add(mChildList);
		        headerList.add("</td><tr>");
		    }
		}

		/*
		 * 컬럼명 가져오기
		 * 
		 */
		for(rowindex=7; rowindex<8; rowindex++){
			row = sheet.getRow(rowindex);
			if(row != null){
				int cells = row.getPhysicalNumberOfCells();
				for(columnindex=0; columnindex<cells; columnindex++){
					XSSFCell cell = row.getCell(columnindex);
					String value="";
					if(cell==null){
						continue;
					}else{
						value=cell.getStringCellValue()+"";
						array.add(value);
					}
				}
			}
		}
		System.out.print("array값:");
		for(int i=0; i<array.size(); i++){
			System.out.print(" "+array.get(i));
		}
		System.out.println("");
		
		/*
		 * 본문 내용 가져오기
		 * 
		 */
		rows=sheet.getPhysicalNumberOfRows();
		for(rowindex=startRowindex; rowindex<rows;rowindex++){ 
		    //행을읽는다
			row=sheet.getRow(rowindex);
//		    row.getRowStyle().setHidden(false); // 숨긴 셀을 가져올지 말지 설정하는 것 같음
		    if(row !=null){ 
		        //셀의 수
		        int cells=row.getPhysicalNumberOfCells();
		        System.out.println(rows);
		        for(columnindex=0; columnindex<cells-1; columnindex++){
		            //셀값을 읽는다
		            XSSFCell cell=row.getCell(columnindex);
		            String value="";
		            if(cell==null){ // 셀이 끝나면 continue;
		                continue;
		            }else{
		                //타입별로 내용 읽기
		                switch (cell.getCellType()){
			                case XSSFCell.CELL_TYPE_FORMULA:{
			                	SimpleDateFormat fommatter = new SimpleDateFormat("HH:mm");
			                	value = fommatter.format(cell.getDateCellValue())+"";
			                    break;
			                }
			                case XSSFCell.CELL_TYPE_NUMERIC:{
			                	if( HSSFDateUtil.isCellDateFormatted(cell)){ // 시간 형식
		                			SimpleDateFormat fommatter = new SimpleDateFormat("HH:mm");
			                		value = fommatter.format(cell.getDateCellValue())+"";
			                	} else {
			                		double ddata = cell.getNumericCellValue();
			                		if ( HSSFDateUtil.isValidExcelDate(ddata) ){ // 날짜 형식
			                		SimpleDateFormat fommatter = new SimpleDateFormat("yyyy-MM-dd");
			                		value = fommatter.format(cell.getDateCellValue())+""; 
				                	} else {
				                		value = String.valueOf(ddata);
				                	}
			                	}
			                    break;
			                }
			                case XSSFCell.CELL_TYPE_STRING:
			                    value=cell.getStringCellValue()+"";
			                    break;
			                case XSSFCell.CELL_TYPE_BLANK:
//			                    value=cell.getBooleanCellValue()+"";
			                    value=cell.toString()+"오류";
			                    break;
			                case XSSFCell.CELL_TYPE_ERROR:
			                    value=cell.getErrorCellValue()+"#5번#";
			                    break;
		                }
		            }
		            if(value.equals("finish")) break; 
		            if(value.equals("오류")){
		            	listA.add("<font color='red'>오류</font><button>수정</button>");
		            }else{
		            	listA.add("<input type='text' name='"+array.get(columnindex)+"["+(rowindex-startRowindex)+"]"+"' value='"+value+"' >");
		            }
		        }
		        listA.add("</td><tr>");	
		    }
		}
		System.out.println(listA);
		model.addAttribute("listA",listA);
		model.addAttribute("headerList", headerList);
	    System.out.println("출력해보자");
	    /*
	     *  먼저 아래가 null인데 위에가 null이 아니면 rowspan
	     */
		for(int i=1; i<mGroupList.size(); i++){
			for(int j=0; j<mChildList.size(); j++){
                if(mGroupList.get(i).get(j).equals("null")){
                	mGroupList.get(i).set(j, mGroupList.get(i-1).get(j));
                }
            }
        }
		/*
		 * 그 다음 옆에 있는 값이 null이면 colspan
		 */
		for(int i=0; i<mGroupList.size(); i++){
			for(int j=1; j<mChildList.size(); j++){
				if(mGroupList.get(i).get(j).equals("null")){
					mGroupList.get(i).set(j, mGroupList.get(i).get(j-1));
				}
			}
		}
		model.addAttribute("testList",mGroupList);
		
		System.out.println(mGroupList);
		
		return "index";
	}
	
	@RequestMapping(value= "/testIndex", method=RequestMethod.GET)
	public String testIndex(Model model){
		FileInputStream file = null;
	    Boolean flag = true;
	    String value="";
	    ArrayList<String> array = new ArrayList<String>();
	    ArrayList<String> rows = new ArrayList<String>();
	    try {
	        // here uploadFolder contains the path to the Login 3.xlsx file
	        file = new FileInputStream("C:\\Spring\\workBook.xlsx");

	        //Create Workbook instance holding reference to .xlsx file
	        XSSFWorkbook workbook = new XSSFWorkbook(file);

	        //Get first/desired sheet from the workbook
	        XSSFSheet sheet = workbook.getSheetAt(0);

	        //Iterate through each rows one by one
	        Iterator<Row> rowIterator = sheet.iterator();
	        while (rowIterator.hasNext()) {
	        	Row row = rowIterator.next();
		        //For each row, iterate through all the columns
		        Iterator<Cell> cellIterator = row.cellIterator();

		        outer:
		        while (cellIterator.hasNext()) {
		            Cell cell = cellIterator.next();
	
		            //will iterate over the Merged cells
		            for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
		                CellRangeAddress region = sheet.getMergedRegion(i); //Region of merged cells
		                int colIndex = region.getFirstColumn(); //number of columns merged
		                int rowNum = region.getFirstRow();      //number of rows merged
		                //check first cell of the region
		                if (rowNum == cell.getRowIndex() && colIndex == cell.getColumnIndex()) {
		                    System.out.println(sheet.getRow(rowNum).getCell(colIndex).getStringCellValue());
		                    value+=sheet.getRow(rowNum).getCell(colIndex).getStringCellValue();
		                    array.add(value);
		                    continue outer;
		                }
		            }
		            //the data in merge cells is always present on the first cell. All other cells(in merged region) are considered blank
		            if (cell.getCellType() == Cell.CELL_TYPE_BLANK || cell == null) {
		                continue;
		            }
		            
		            value+=cell.getStringCellValue();
		            array.add(value);
		        }
		    }
		}catch(Exception e){
	    	
	    }
	    model.addAttribute("array",array);
	    for(int i=0; i<array.size(); i++){
	    	System.out.println(array.get(i));
	    }
		return "testest";
	}
	
	@RequestMapping(value= "/testtest", method=RequestMethod.POST)
	public String testtest(Model model) throws IOException{
		FileInputStream fis=new FileInputStream("C:\\Spring\\qq.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		int rowindex=0;
		int columnindex=0;
		List listA = new ArrayList();
		//시트 수 (첫번째에만 존재하므로 0을 준다)
		//만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
		XSSFSheet sheet=workbook.getSheetAt(0);
		//행의 수
		int rows=sheet.getPhysicalNumberOfRows();
		for(rowindex=1;rowindex<rows;rowindex++){ 
		    //행을읽는다
			System.out.println("로우 수"+rows);
		    XSSFRow row=sheet.getRow(rowindex);
		    if(row !=null){
		        //셀의 수
		        int cells=row.getPhysicalNumberOfCells();
		        System.out.println("셀수:"+cells);
		        for(columnindex=0;columnindex<=cells;columnindex++){ // 16
		            //셀값을 읽는다
		            XSSFCell cell=row.getCell(columnindex);
		            String value="";
		            
		            //셀이 빈값일경우를 위한 널체크
		            if(cell==null){
		                continue;
		            }else{
		                //타입별로 내용 읽기
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
		            System.out.println("각 셀 내용 :"+value);
		            if(value.equals("")){
		            	value = (String) listA.get(listA.size()-1);
		            }
		            listA.add(value+"???");
	           	}
		        listA.add("</td><tr><td>아놔 ㅡㅡ");
		    }
		}
		
		model.addAttribute("listA",listA);
		return "index";
	}
}
