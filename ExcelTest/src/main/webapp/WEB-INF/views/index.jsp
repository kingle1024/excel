<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:url var="bootstrap" value="/resources/css/bootstrap.min.css" />
<link href="${ bootstrap }" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- 
http://sararing.tistory.com/120
-> �����͸� �Ⱥ����� ������ ���� �� �� ����??

ajax json �޾ƿ���
 -->
<script>
// var checkList = [];
// $.ajax({
// 	type:"post",
// 	url:"getJsonByMap", 
// 	dataType:'json',
// 	data:checkList,
// 	error:function(){
// 		alert("ERROR::");
// 		//loading.hide();
// 	},
// 	success:function(data){
// 		//����Ʈ �Ʒ� ���� HTML
// 		if(data.length==0)
// 			alert('����� �����ϴ�');
// 		else{
// 			checkList = data;
// 			console.log(data); // �α� ���
// 			console.log(checkList); // �α� ���
// 		}
// 	}
// });
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
	<input type="button" id="searchButton" value="��������">
	<input type="button" id="searchButton1" value="�����ÿ�">
<script>
$("#searchButton").click(function(){
	var values = []; //ArrayList ���� ���� ������ ����
	 $.post(
		"showJsonData", // ���� url
	     function(jsonObject) {
			console.log(jsonObject); // �α� ���	
	    	var innerHTML =""; 
			alert("����̽�");
			values = jsonObject.result_list;
			innerHTML +="<div class='container'>";
			innerHTML +="<table class='table table-striped'><tr><td>��ȣ</td><td>����</td><td>arr</td>";
			$.each(values, function( index, value){ // ���		
				innerHTML +="<tr>";
				innerHTML +="<td><input type='text' value='"+value.idx+"'></td>";
				innerHTML +="<td><input type='text' value='"+value.title+"'></td>";
				innerHTML +="<td><input type='text' value='"+value.arr+"'></td>";
				innerHTML +="</tr>";
			});
			innerHTML +="</table>";
			innerHTML +="</div>";
			$('#come').append(innerHTML); // ȭ�鿡 ǥ��
        }
    );
});
</script>
<script>
$("#searchButton1").click(function(){
	var values = []; //ArrayList ���� ���� ������ ����
	 $.post(
		"showJsonData1", // ���� url
	     function(jsonObject) {
			console.log(jsonObject); // �α� ���	
			
	    	var innerHTML =""; 
			values = jsonObject;
// 			alert(values);
			innerHTML +="<div class='container'>";
			innerHTML +="<form>"
			innerHTML +=
			"<table class='table table-striped'>"+
			"<thead>"+
				"<tr>"+
					"<th width='100px'>���<br>(��Ϲ�ȣ)</th>"+
					"<th width='100px'>����</th>"+
					"<th>�̸�</th>"+
					"<th width='100px'>��������Ʈ</th>"+
					"<th width='100px'>���� ������</th>"+
					"<th width='130px'>checkbox</th>"+
				"</tr>"+
			"</thead>";
			
			$.each(values, function(index, value){ // ���
// 				alert(index);
				innerHTML +="<tbody>";
				innerHTML +="<tr>";
					$.each(value, function(indexx, valuee){
						innerHTML +="<td>"+valuee+"</td>";
						
						//if(valuee.)
						/*
						
						*/
					})				
				innerHTML +="</tr>";
			});
			innerHTML +="</tbody>";
			innerHTML +="</table>";
			innerHTML +="<input type='submit' class='btn btn-primary' value='�����ϱ�' onclick=''>";
			innerHTML +="</form>";
			innerHTML +="</div'>";
			$('#come1').append(innerHTML); // ȭ�鿡 ǥ��
        }
    );
});
</script>
<script type="text/javascript">
    function add_item(obj){
        // pre_set �� �ִ� ������ �о�ͼ� ó��..
        var div = document.createElement('div');
//         div.innerHTML = document.getElementById('pre_set').innerHTML;
//         document.getElementById('field').appendChild(div);
//         document.getElementById('dummy').removeChild(obj.parentNode);
        document.getElementById("dummy").style = "display:none";
        document.getElementById("pre_set").style = "display:hidden";
    }
    function remove_item(obj){
        // obj.parentNode �� �̿��Ͽ� ����
//         document.getElementById('field').removeChild(obj.parentNode);
        // display:none���� ����
    	document.getElementById("dummy").style = "display:hidden";
    	document.getElementById("pre_set").style = "display:none";
    }

</script>

	<br><br><br>
	<div id="come"></div>
	<div id="come1"></div>
	<div id="pre_set" style="display:none">
    	<input type="text" name="" value="" style="width:200px"> <input type="button" value="���" onclick="remove_item(this)">
	</div>
 

	
	<br><br><br>

    <script>
    $(document).ready(function() {
        $('#submitBtn').click(function() {
            var data = new FormData();
            $.each($('#attachFile')[0].files, function(i, file) {          
                data.append('file-' + i, file);
            });
             
            $.ajax({
                url: 'upload',
                type: "post",
                dataType: "text",
                data: data,
                // cache: false,
                processData: false,
                contentType: false,
                success: function(data, textStatus, jqXHR) {
                    alert(data);
                }, error: function(jqXHR, textStatus, errorThrown) {}
            });
        });
    });
</script>
    
    <form id="excelUploadForm" name="excelUploadForm" enctype="multipart/form-data" method="post" 
                                action= "${pageContext.request.contextPath}/user/excelUploadAjax">
    <div class="contents">
        <div>÷�������� �Ѱ��� ��� �����մϴ�.</div>
 
        <dl class="vm_name">
                <dt class="down w90">÷�� ����</dt>
                <dd><input id="excelFile" type="file" name="excelFile" /></dd>
        </dl>        
    </div>
            
    <div class="bottom">
        <button type="button" id="addExcelImpoartBtn" class="btn" onclick="check()" ><span>�߰�</span></button> 
    </div>
</form> 
<script>
function checkFileType(filePath) {
    var fileFormat = filePath.split(".");
    if (fileFormat.indexOf("xlsx") > -1) {
        return true;
    } else {
        return false;
    }

}

function check() {
    var file = $("#excelFile").val();
    if (file == "" || file == null) {
        alert("������ �������ּ���.");
        return false;
    } else if (!checkFileType(file)) {
        alert("���� ���ϸ� ���ε� �����մϴ�.");
        return false;
    }

    if (confirm("���ε� �Ͻðڽ��ϱ�?")) {
        var options = {
            success : function(data) {
                alert("��� �����Ͱ� ���ε� �Ǿ����ϴ�.");
            },
            type : "POST"
        };
        $("#excelUploadForm").ajaxSubmit(options);
    }
}

</script>
<form>
    <input type="file" name="fileUpload" id="fileUpload">
</form>
    
</body>
</html>