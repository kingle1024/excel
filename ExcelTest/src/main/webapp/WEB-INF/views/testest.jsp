<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<script>
var checkList = [];
$.ajax({
	type:"post",
	url:"testtest", 
	dataType:'json',
	data:checkList,
	error:function(){
		alert("ERROR::");
		//loading.hide();
	},
	success:function(data){
		//����Ʈ �Ʒ� ���� HTML
		if(data.length==0)
			alert('����� �����ϴ�');
		else{
			checkList = data;
			console.log(data); // �α� ���
			console.log(checkList); // �α� ���
		}
	}
});
</script>
</head>
<body>

<table border="1" id="table">
		<tr>
			<c:forEach var="obj" items="${listA}">
				<td>${obj }</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>