package com.mycompany.myapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycompany.vo.Book;
import com.mycompany.vo.ListVo;
import com.mycompany.vo.ObjectVo;
import com.mycompany.vo.Product;

import net.sf.json.JSONArray;

// http://roqkffhwk.tistory.com/120
@Controller
public class JsonController {
	@RequestMapping(value="/getJsonByMap" ,method = RequestMethod.POST)
	public @ResponseBody Map<String , Object> getJsonByMap(HttpServletRequest request, Model model) {
	    Map<String, Object> jsonObject = new HashMap<String, Object>();
	    Map<String, Object> jsonSubObject = null;
	    ArrayList<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
	         
	    //1��° ������
	    jsonSubObject = new HashMap<String, Object>();
	    jsonSubObject.put("idx", 1);
	    jsonSubObject.put("title", "�����Դϴ�");
	    jsonSubObject.put("create_date", new Date());
	    jsonList.add(jsonSubObject);
	    
	    //2��° ������
	    jsonSubObject = new HashMap<String, Object>();
	    jsonSubObject.put("idx", 2);
	    jsonSubObject.put("title", "�ι�°�����Դϴ�");
	    jsonSubObject.put("create_date", new Date());
	    jsonList.add(jsonSubObject);
	         
//	    jsonObject.put("success", true);
//	    jsonObject.put("total_count", 10);
	    jsonObject.put("result_list", jsonList);
	    
	    List<Product> productList = new ArrayList<Product>();
	    Product product1 = new Product(1, "Hot Chicken", "C-1", "��ġŲ");
	    Product product2 = new Product(1, "Cheese Chicken", "C-2", "ġ�� ���� ġŲ");
	    Product product3 = new Product(1, "Honey Chicken", "C-3", "��� ���� ġŲ");
	    productList.add(product1);
	    productList.add(product2);
	    productList.add(product3);
	    
	    JSONArray jsonArray = new JSONArray();
	    model.addAttribute("list", productList); 
	    model.addAttribute("jsonList", jsonArray.fromObject(productList));
	    
	    model.addAttribute("jsonList", jsonArray.fromObject(jsonList));
	    
	    System.out.println(jsonObject);
	    
	    
	    
	    return jsonObject;
	}

	/**
	 * VO����� �̿��� JSON API ��Ʈ�ѷ�
	 * @return
	 */
	@RequestMapping(value="/getJsonByVO")
	public @ResponseBody ObjectVo getJsonByVO() {
	    Calendar cal = Calendar.getInstance( );
	    ArrayList<ListVo> list = new ArrayList<ListVo>();
	    ListVo vo = null;
	    ObjectVo objectVO = new ObjectVo();
	    
	    //1��° ������
	    vo = new ListVo();
	    vo.setIdx(1);
	    vo.setTitle("VO����� �����Դϴ�");
	    vo.setCreate_date(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH) + 1)+"-"+cal.get(Calendar.DAY_OF_MONTH));
	    list.add(vo);
	    
	    //2��° ������
	    vo = new ListVo();
	    vo.setIdx(2);
	    vo.setTitle("VO����� �����Դϴ�2");
	    vo.setCreate_date(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH) + 1)+"-"+cal.get(Calendar.DAY_OF_MONTH));
	    list.add(vo);
	         
	    //�̷��� ������� ��µ�
	    objectVO.setSuccess(true);
	    objectVO.setTotal_count(10);	    
	    objectVO.setList(list);
	    
	    System.out.println(objectVO);
	    
	    return objectVO;
	}
	
	//http://huskdoll.tistory.com/282
	@RequestMapping(value="ttttttt", method=RequestMethod.POST)
	@ResponseBody
	public Object getBookInfo(@RequestParam Map<String,Object> map) {
		System.out.println("1");
	    String searchCd = (String) map.get("searchCd"); //�˻��ڵ�
	    //������ �ش� �κ��� ���񽺿��� ó�� �ؾ� ������ �׳� Controller���� ó��
	    //�˻��ڵ带 �������� ���� ���� �;� �ϴµ� ������ ���� ���������� ���� ����
	 
	    List<Book> bookList = new ArrayList<Book>();
	    Book book = new Book();
	    System.out.println("2");	     
	    book.setName("ȫ�浿��");
	    book.setPrice(100);
	     
	    bookList.add(book);
	     
	    book = new Book();
	    System.out.println("3");
	    book.setName("���������");
	    book.setPrice(300);
	     
	    bookList.add(book);
	    System.out.println("4");
	     
	    Map<String, Object> retVal = new HashMap<String, Object>();
	    System.out.println("5");
	    retVal.put("bookList", bookList); //bookList�� Ű�� bookList�� ���� �־��ݴϴ�. (������ bookListŰ�� ����
	    System.out.println("6");
	    retVal.put("code", "OK");
	    System.out.println("7");
	    
	    
	    //
	    Map<String, Object> jsonObject = new HashMap<String, Object>();
	    Map<String, Object> jsonSubObject = null;
	    ArrayList<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
	  //1��° ������
	    jsonSubObject = new HashMap<String, Object>();
	    jsonSubObject.put("idx", 1);
	    jsonSubObject.put("title", "�����Դϴ�");
	    jsonSubObject.put("create_date", new Date());
	    jsonList.add(jsonSubObject);
	    
	    //2��° ������
	    jsonSubObject = new HashMap<String, Object>();
	    jsonSubObject.put("idx", 2);
	    jsonSubObject.put("title", "�ι�°�����Դϴ�");
	    jsonSubObject.put("create_date", new Date());
	    jsonList.add(jsonSubObject);
	         
//	    jsonObject.put("success", true);
//	    jsonObject.put("total_count", 10);
	    jsonObject.put("result_list", jsonList);
	    
	    System.out.println(jsonObject);
	    
//	    return jsonObject;
	    
	    
	    return retVal;
	     
	}
	
	@RequestMapping(value="showJsonData", method=RequestMethod.POST)
	@ResponseBody
	public Object getget(@RequestParam Map<String,Object> map) {
		Map<String, Object> jsonSubObject = null;
		Map<String, Object> jsonObject = new HashMap<String, Object>();
	    ArrayList<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
	    int arr[] = null;
		
		jsonSubObject = new HashMap<String, Object>();
	    jsonSubObject.put("idx", 5);
	    jsonSubObject.put("title", "�����Դϴ�");
	    arr = new int[3];
	    arr[0] =1;
	    arr[1] =2;
	    arr[2] =3;
	    jsonSubObject.put("arr", arr);
	    jsonList.add(jsonSubObject); 
	    
	    jsonSubObject = new HashMap<String, Object>();
	    jsonSubObject.put("idx", 2);
	    jsonSubObject.put("title", "�ι�°�����Դϴ�");
	    arr = new int[3];
	    arr[0] = 4;
	    arr[1] = 5;
	    arr[2] = 6;
	    jsonSubObject.put("arr", arr);
	    jsonList.add(jsonSubObject);
	    
	    jsonObject.put("result_list", jsonList);
	    
	    System.out.println(jsonObject);

	  
		
		return jsonObject;
	}
	
	@RequestMapping(value="showJsonData1", method=RequestMethod.POST)
	@ResponseBody
	public Object get(@RequestParam Map<String,Object> map) {
		ArrayList<Object> mChildList = new ArrayList<Object>();
		ArrayList<Object> subChild = null;
	    ArrayList<ArrayList<Object>> mGroupList = new ArrayList<ArrayList<Object>>();
	  
	    subChild = new ArrayList<Object>();
	    subChild.add("1");
//	    subChild.add("<input type='text' value='1' style='color:red;' class='form-control' >");
	    subChild.add("���");
	    subChild.add("<input type='text' value='test1' style='color:red;' class='form-control' >");
	    subChild.add("<select class='form-control'>"
	    			+ "	<option>1</option>"
	    			+ " <option>2</option>"
	    			+"</select>");
	    subChild.add("<div id='dummy'>"
	    			+ "	<font color='red'>��������<br>"
	    			+ "	<input type='button' value='����' class='btn btn-default' id='byButton' onclick='add_item(this)'></font><br>"
	    			+ "</div>"
	    			+ "<div id='pre_set' style='display:none'>"
	    			+ "	<input type='text' name='�����ؾߵ���' id='�����ؾߵ���' style='width:100px'>"
	    			+ " <input type='button' value='Ȯ��' class='btn btn-default btn-sm' onclick=''>"
	    			+ "	<input type='button' value='���' class='btn btn-default btn-sm' onclick='remove_item(this)'>"
	    			+ "</div>"
	    			+ "<div id='field'></div>");
	    subChild.add("<div class='checkbox'>"
	    			+ "	<label>"
	    			+ "		<input type='checkbox' name='vehicle' value='Bike'> I have a bike<br> "
	    			+ "		<input type='checkbox' name='vehicle' value='Car' checked> I have a car"
	    			+ "</label>"
	    			+ "</div>");
	    
	    mChildList.add(subChild);
	    
	    subChild = new ArrayList<Object>();
	    subChild.add("2");
	    subChild.add("�븮");
//	    subChild.add("<input type='text' class='form-control' value='2' style='color:blue;'>");
	    subChild.add("<input type='text' class='form-control' value='test2' style='color:blue;'>");
	    subChild.add("<select class='form-control'><option>1</option><option>2</option></select>");
	    subChild.add("<div id='dummy'><font color='red'>��������<br><input type='button' value='����' onclick='add_item()'></div></font><br><div id='pre_set' style='display:none'>"+
	    			"<input type='text' name='' value='' style='width:200px'> <input type='button' value='����' onclick='remove_item(this)'></div><div id='field'></div>");
	    subChild.add("<div class='checkbox'><label><input type='checkbox' name='vehicle' value='Bike'> I have a bike<br> <input type='checkbox' name='vehicle' value='Car' checked> I have a car</label></div><br>");
	    
	    mChildList.add(subChild);
	    
	    subChild = new ArrayList<Object>();
	    subChild.add("3");
	    subChild.add("����");
//	    subChild.add("<input type='text' class='form-control' value='3' style='color:green;'>");
	    subChild.add("<input type='text' class='form-control' value='test3' style='color:green;'>");
	    subChild.add("<select class='form-control'><option>1</option><option>2</option></select>");
	    subChild.add("<font color='red'>��������<br><input type='button' value='�߰�' onclick='add_item()'></font>");
	    subChild.add("<div class='checkbox'><label><input type='checkbox' name='vehicle' value='Bike'> I have a bike<br> <input type='checkbox' name='vehicle' value='Car' checked> I have a car</label></div><br>");
	    
	    mChildList.add(subChild); // ������ �� 

	    subChild = new ArrayList<Object>();
	    subChild.add("4");
	    subChild.add("����");
//	    subChild.add("<input type='text' class='form-control' value='4' style='color:yellow;'>");
	    subChild.add("<input type='text' class='form-control' value='test2' style='color:yellow;'>");
	    subChild.add("<select class='form-control'><option>1</option><option>2</option></select>");
	    subChild.add("<font color='red'>��������<br><input type='button' value='�߰�' onclick='add_item()'></font>");
	    subChild.add("<div class='checkbox'><label><input type='checkbox' name='vehicle' value='Bike'> I have a bike<br> <input type='checkbox' name='vehicle' value='Car' checked> I have a car</label></div><br>");
	    mChildList.add(subChild); // ������ ��
	    
	    mGroupList.add(mChildList);
	    
	    System.out.println(mChildList);
		return mChildList;
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(MultipartHttpServletRequest req, 
	    HttpServletResponse res) {
	 
	    Iterator<String> itr = req.getFileNames();
	    MultipartFile mpf = req.getFile(itr.next());
	    String originFileName = mpf.getOriginalFilename();
	 
	 
	    // ... ����
	 
	     
	    return "index";
	}

	
}
