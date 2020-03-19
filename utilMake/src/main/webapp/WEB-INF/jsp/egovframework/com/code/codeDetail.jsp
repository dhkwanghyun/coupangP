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

    	})
    </script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">

<form id="dataForm" name="dataForm" method="post">
    <div id="content_pop">
    	<!-- 타이틀 -->
    	<div id="title">
    		<ul>
    			<li><img src="<c:url value='/images/egovframework/example/title_dot.gif'/>" alt=""/>
    				코드상세/수정
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
	    			<td class="tbtd_content">
						<input type="text" id="codeParent" name="codeParent" value="<c:out value="${codeVO.codeParent}"/>" readonly="readonly"></input>
						<input type="text" id="pCodeName" name="pCodeName" value="<c:out value="${codeVO.codeParentNm}"/>" readonly="readonly"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="tbtd_caption">순서</td>
	    			<td class="tbtd_content">
						<input type="text" id="codeOrder" name="codeOrder" value="<c:out value="${codeVO.codeOrder}"/>" ></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="tbtd_caption">코드값</td>
	    			<td class="tbtd_content">
						<input type="text" id="codeValue" name="codeValue" value="<c:out value="${codeVO.codeValue}"/>" readonly="readonly"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="tbtd_caption">코드이름</td>
	    			<td class="tbtd_content">
						<input type="text" id="codeName" name="codeName" value="<c:out value="${codeVO.codeName}"/>"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="tbtd_caption">사용유무</td>
	    			<td class="tbtd_content">
						<input type="radio" id="codeUseYn" name="codeUseYn" value="Y" <c:if test="${codeVO.codeUseYn eq 'Y'}">checked="checked"</c:if>>Y</input>
						<input type="radio" id="codeUseYn" name="codeUseYn" value="N" <c:if test="${codeVO.codeUseYn eq 'N'}">checked="checked"</c:if>>N</input>
	    			</td>
	    		</tr>
    		</tbody>
    	</table>
     	</div>
    	<div id="sysbtn">
    		<ul>
    			<li>
                    <span class="btn_blue_l">
                        <a href="">수정</a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                    <span class="btn_blue_l">
                        <a href="">삭제</a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                    <span class="btn_blue_l">
                        <a href="/code/codeList.do">목록</a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
            </ul>
    	</div>
    </div>
</form>

</body>
</html>