<%@ page pageEncoding="utf-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>

<script>
// var checkList = [];
// $.ajax({
// 	type:"post",
// 	url:"testtest", 
// 	dataType:'json',
// 	data:checkList,
// 	error:function(){
// 		alert("ERROR::");
// 		//loading.hide();
// 	},
// 	success:function(data){
// 		//리스트 아래 붙일 HTML
// 		if(data.length==0)
// 			alert('결과가 없습니다');
// 		else{
// 			checkList = data;
// 			console.log(data); // 로그 찍기
// 			console.log(checkList); // 로그 찍기
// 		}
// 	}
// });
</script>
</head>
<body>

<table border="1" id="table">
		<tr>
			<c:forEach var="array" items="${array}">
				<td>${array }</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>