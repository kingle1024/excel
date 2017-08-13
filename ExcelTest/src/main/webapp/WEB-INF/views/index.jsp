<%@ page pageEncoding="utf-8" session="false" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:url var="bootstrap" value="/resources/css/bootstrap.min.css" />
<link href="${ bootstrap }" rel="stylesheet">
<title>엑셀 업로드 미리보기</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- 
http://sararing.tistory.com/120
-> 데이터를 안보내고 페이지 갔다 올 순 없나??
ajax json 받아오기
 -->
 <script>
$(document).ready(function(){
	$("#ReadingInfoSelectBtn").click(function(){
		var formData = $("#formname1").serialize();
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/uploadTest",
			cache : false,
			data : formData,
			success : function(json, status){
				alert('성공 했습니다');
			},
			error : function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});
});
</script>
<script>
$.fn.rowspan = function(colIdx, isStats) {       
    return this.each(function(){      
        var that;     
        $('tr', this).each(function(row) {      
            $('td:eq('+colIdx+')', this).filter(':visible').each(function(col) {
                if ($(this).html() == $(that).html() && (!isStats || isStats && $(this).prev().html() == $(that).prev().html()) ) {            
                    rowspan = $(that).attr("rowspan") || 1;
                    rowspan = Number(rowspan)+1;
                    $(that).attr("rowspan",rowspan);
                    // do your action for the colspan cell here            
                    $(this).hide();
                    //$(this).remove(); 
                    // do your action for the old cell here
                } else {            
                    that = this;         
                }          
                // set the that if not already set
                that = (that == null) ? this : that;      
            });     
        });    
    });  
}; 
$.fn.colspan = function(rowIdx) {  
    return this.each(function(){  
    	var that;  
    	$('tr', this).filter(":eq("+rowIdx+")").each(function(row) { 
    		$(this).find('td').filter(':visible').each(function(col) {
    			if( $(this).html() == $(that).html() ) {   //
    				colspan = $(that).attr("colSpan");
    				if(colspan == undefined){
   						$(that).attr("colSpan",1);  
   						colspan = $(that).attr("colSpan");
    				}  
    				colspan = Number(colspan)+1;  
    				$(that).attr("colSpan",colspan);  
    				$(this).hide(); // .remove();
    			}else{  
    				that = this; // 같은 이름의 값을 합쳐준다(비어있지 않은 값)
    			}  
    			that = (that == null) ? this : that; // set the that if not already set
			});  
    	});  
 });  
} 
</script>
</head>
<body>
<form name="formname1" method="post">
	<table border="1" id="table" >
		<c:forEach var="headerList" items="${testList }">
		<tr>
			<c:forEach var="listL" items="${headerList }">
				<td width="100px">${listL }</td>
			</c:forEach>
		</tr>
		</c:forEach>
		<tr>
			<c:forEach var="obj" items="${listA}">
				<td width="100px"> ${ obj } </td>
			</c:forEach>
		</tr>
	</table>
	<input type="hidden" value="test" name="test">
	<input type="button"  id="ReadingInfoSelectBtn" value="제출">
</form>

	<input type="button" id="searchButton" value="눌러보쇼">
	<input type="button" id="searchButton1" value="누르시오">
<script type="text/javascript">  
	$(document).ready(function() {  
	/*
		아래 주석처리된 것을 사용하면 헤더를 그릴 수 있음(근데 테이블을 두개로 가져가야 함. 왜냐하면 하나로 가져가면 밑에 값들도 통합이 되버려서)		
	*/
// 		$('table tbody tr:visible').each(function(row) {  
// 			$('#table').colspan(row);
// 			$('#table').rowspan(row);
// 		})  
	});  
</script>
<script>
$("#searchButton").click(function(){
	var values = []; //ArrayList 값을 받을 변수를 선언
	 $.post(
		"showJsonData", // 실행 url
	     function(jsonObject) {
			console.log(jsonObject); // 로그 찍기	
	    	var innerHTML =""; 
			alert("실행됫슈");
			values = jsonObject.result_list;
			innerHTML +="<div class='container'>";
			innerHTML +="<table class='table table-striped'><tr><td>번호</td><td>제목</td><td>arr</td>";
			$.each(values, function( index, value){ // 출력		
				innerHTML +="<tr>";
				innerHTML +="<td><input type='text' value='"+value.idx+"'></td>";
				innerHTML +="<td><input type='text' value='"+value.title+"'></td>";
				innerHTML +="<td><input type='text' value='"+value.arr+"'></td>";
				innerHTML +="</tr>";
			});
			innerHTML +="</table>";
			innerHTML +="</div>";
			$('#come').append(innerHTML); // 화면에 표시
        }
    );
});
</script>
<script>
$("#searchButton1").click(function(){
	var values = []; //ArrayList 값을 받을 변수를 선언
	 $.post(
		"showJsonData1", // 실행 url
	     function(jsonObject) {
			console.log(jsonObject); // 로그 찍기	
			
	    	var innerHTML =""; 
			values = jsonObject;
// 			alert(values);
			innerHTML +="<div class='container'>";
			innerHTML +="<form>"
			innerHTML +=
			"<table class='table table-striped'>"+
			"<thead>"+
				"<tr>"+
					"<th width='100px'>사번<br>(등록번호)</th>"+
					"<th width='100px'>직위</th>"+
					"<th>이름</th>"+
					"<th width='100px'>나눔포인트</th>"+
					"<th width='100px'>교육 참여도</th>"+
					"<th width='130px'>checkbox</th>"+
				"</tr>"+
			"</thead>";
			
			$.each(values, function(index, value){ // 출력
				innerHTML +="<tbody>";
				innerHTML +="<tr>";
					$.each(value, function(indexx, valuee){
						innerHTML +="<td>"+valuee+"</td>";
					})				
				innerHTML +="</tr>";
			});
			innerHTML +="</tbody>";
			innerHTML +="</table>";
			innerHTML +="<input type='submit' class='btn btn-primary' value='제출하기' onclick=''>";
			innerHTML +="</form>";
			innerHTML +="</div'>";
			$('#come1').append(innerHTML); // 화면에 표시
        }
    );
});
</script>
<script type="text/javascript">
    function add_item(obj){
        // pre_set 에 있는 내용을 읽어와서 처리..
        var div = document.createElement('div');
//         div.innerHTML = document.getElementById('pre_set').innerHTML;
//         document.getElementById('field').appendChild(div);
//         document.getElementById('dummy').removeChild(obj.parentNode);
        document.getElementById("dummy").style = "display:none";
        document.getElementById("pre_set").style = "display:hidden";
    }
    function remove_item(obj){
        // obj.parentNode 를 이용하여 삭제
//         document.getElementById('field').removeChild(obj.parentNode);
        // display:none으로 변경
    	document.getElementById("dummy").style = "display:hidden";
    	document.getElementById("pre_set").style = "display:none";
    }

</script>
	<br><br><br>
	<div id="come"></div>
	<div id="come1"></div>
	<div id="pre_set" style="display:none">
    	<input type="text" name="" value="" style="width:200px"> <input type="button" value="등록" onclick="remove_item(this)">
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
        <div>첨부파일은 한개만 등록 가능합니다.</div>
 
        <dl class="vm_name">
                <dt class="down w90">첨부 파일</dt>
                <dd><input id="excelFile" type="file" name="excelFile" /></dd>
        </dl>        
    </div>
            
    <div class="bottom">
        <button type="button" id="addExcelImpoartBtn" class="btn" onclick="check()" ><span>추가</span></button> 
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
        alert("파일을 선택해주세요.");
        return false;
    } else if (!checkFileType(file)) {
        alert("엑셀 파일만 업로드 가능합니다.");
        return false;
    }

    if (confirm("업로드 하시겠습니까?")) {
        var options = {
            success : function(data) {
                alert("모든 데이터가 업로드 되었습니다.");
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