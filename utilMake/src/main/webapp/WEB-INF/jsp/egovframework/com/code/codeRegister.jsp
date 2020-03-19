<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : egovSampleRegister.jsp
  * @Description : Sample Register 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2009.02.01            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.02.01
  *
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">

<head>
    <%@ include file="/WEB-INF/jsp/egovframework/com/cmmn/include/include-header.jspf" %>
    <title>menuRegister</title>
    <script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
    <script type="text/javaScript" language="javascript" defer="defer">
    	$(document).ready(function(){
    		codeGroupSelect("",0);
    	})

	    function fn_code_insert() {
	       	document.dataForm.action = "<c:url value='/code/codeRegister_C.do'/>";
	       	document.dataForm.submit();
	    }

	    function codeGroupSelect(codeValue,selectIndex){
	    	$("#srchCode").val(codeValue);
			var sendData = $('#dataForm').serialize();
			$.ajax({
				type: "POST",
				url : "/code/codeGroupList.ajax",
				data: sendData,
				async: true,
				success : function(data, status, xhr) {
					console.log("sucscess");
					var jsonList = JSON.parse(data.jsonList);
					if(jsonList.length>0){
						var optionStr ="";
						optionStr +="<option value=''>선택</option>";
						for(i=0;i < jsonList.length;i++){
							optionStr +="<option value='"+jsonList[i].codeValue+"'>"+jsonList[i].codeName+"</option>";
						};

						selectIndex++;
						var selectStr ="<select class='csClass' id='codeSelect"+selectIndex+"' onchange='selectChange(this,"+selectIndex+","+jsonList[jsonList.length-1].codeOrder+")'>";
						selectStr += optionStr;
						selectStr += "</select>";
						$("#parentCode").append(selectStr);

						//$("#codeOrder").val(Number(jsonList[jsonList.length-1].codeOrder)+1);
						//$("#codeParent").val($("#codeSelect"+selectIndex).val());
						//$("#pCodeName").val($("option:selected","#codeSelect"+selectIndex).text());
						//$("#codeSelect"+selectIndex).trigger('change');
					}



				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(data);
					//alert(jqXHR.responseText);
				}
			});
		}

	    function selectChange(e,selectIndex,order){
	    	console.log($(e).val());
	    	console.log(Number(order)+1);
	    	console.log($(".csClass").size());
	    	for(var i=selectIndex;i<=$(".csClass").size();i++){
	    		console.log(i);
	    		$('select').remove('#codeSelect'+(i+1));
	    	}
	    	if($(e).val() != ""){
	    		$("#codeOrder").val(Number(order)+1);
				$("#codeParent").val($(e).val());
				$("#pCodeName").val($("option:selected",e).text());
		    	codeGroupSelect($(e).val(),selectIndex);
	    	}else{
	    		if(selectIndex==1){
		    		$("#codeOrder").val(Number(order)+1);
					$("#codeParent").val("");
					$("#pCodeName").val("");
	    		}else{
	    			$("#codeSelect"+(selectIndex-1)).trigger('change');
	    		}
	    	}
	    }

    </script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">

<form id="dataForm" name="dataForm" method="post">
    <div id="content_pop">
    	<!-- 타이틀 -->
    	<div id="title">
    		<ul>
    			<li><img src="<c:url value='/images/egovframework/example/title_dot.gif'/>" alt=""/>
    				코드등록
    			</li>
    		</ul>
    	</div>
    	<!-- // 타이틀 -->
    	<div id="table">
    	<table width="100%" border="1" cellpadding="0" cellspacing="0" style="bordercolor:#D3E2EC; bordercolordark:#FFFFFF; BORDER-TOP:#C2D0DB 2px solid; BORDER-LEFT:#ffffff 1px solid; BORDER-RIGHT:#ffffff 1px solid; BORDER-BOTTOM:#C2D0DB 1px solid; border-collapse: collapse;">
    		<colgroup>
    			<col width="150"/>
    			<col width="?"/>
    		</colgroup>
    		<tbody >
	    		<tr>
	    			<td class="tbtd_caption">부모코드</td>
	    			<td id="parentCode" class="tbtd_content">

	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="tbtd_caption">부모코드</td>
	    			<td class="tbtd_content">
						<input type="text" id="codeParent" name="codeParent" value=""></input>
						<input type="text" id="pCodeName" name="pCodeName" value=""></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="tbtd_caption">순서</td>
	    			<td class="tbtd_content">
						<input type="text" id="codeOrder" name="codeOrder" value="" readonly="readonly"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="tbtd_caption">코드값</td>
	    			<td class="tbtd_content">
						<input type="text" id="codeValue" name="codeValue" value=""></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="tbtd_caption">코드이름</td>
	    			<td class="tbtd_content">
						<input type="text" id="codeName" name="codeName" value=""></input>
	    			</td>
	    		</tr>
    		</tbody>
    	</table>
     	</div>
    	<div id="sysbtn">
    		<ul>
    			<li></li>
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_code_insert();"><spring:message code="button.create" /></a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
            </ul>
    	</div>
    </div>
    <input type="hidden" id="srchCode" name="srchCode" value=""/>
</form>

</body>
</html>