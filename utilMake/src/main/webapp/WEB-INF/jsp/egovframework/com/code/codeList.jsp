<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="utag"   uri="/WEB-INF/tld/utag.tld" %>
<%
  /**
  * @Class Name : egovSampleList.jsp
  * @Description : Sample List 화면
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="title.sample" /></title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
	<script type="text/javaScript" language="javascript" defer="defer">
		function fn_codeDetail(codeValue) {
        	document.listForm.codeValue.value = codeValue;
           	document.listForm.action = "<c:url value='/code/codeDetail.do'/>";
           	document.listForm.submit();
        }

		/* pagination 페이지 링크 function */
        function fn_egov_link_page(pageNo){
        	document.listForm.pageIndex.value = pageNo;
        	document.listForm.action = "<c:url value='/code/codeList.do'/>";
           	document.listForm.submit();
        }
	</script>
</head>

<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
	<form:form commandName="codeVO" id="listForm" name="listForm" method="get">
	<input type="hidden" name="codeValue" />
	<div id="content_pop">
		<!-- 타이틀 -->
	    <div id="title">
	    	<ul>
	    		<li><img src="<c:url value='/images/egovframework/example/title_dot.gif'/>" alt=""/>
	    			코드등록
	    		</li>
	    	</ul>
	    </div>
		<div id="table">
	    	<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="카테고리ID, 케테고리명, 사용여부, Description, 등록자 표시하는 테이블">
	        	<caption style="visibility:hidden">카테고리ID, 케테고리명, 사용여부, Description, 등록자 표시하는 테이블</caption>
	        		<colgroup>
	        			<col width="40"/>
	        			<col width="50"/>
	        			<col width="150"/>
	        			<col width="80"/>
	        		</colgroup>
	        		<tr>
	        			<th align="center">부모코드</th>
	        			<th align="center">코드값</th>
	        			<th align="center">코드이름</th>
	        			<th align="center">코드순서</th>
	        		</tr>
	        	<c:forEach var="item" items="${codeList}" varStatus="status">
	            	<tr>
	            		<td align="center" class="listtd"><c:out value="${item.codeParent}"/></td>
	            		<td align="center" class="listtd">
	            			<a href="javascript:fn_codeDetail('<c:out value="${item.codeValue}"/>');">
	            				<c:out value="${item.codeValue}"/>
	            			</a>
	            		</td>
	            		<td align="center" class="listtd"><c:out value="${item.codeName}"/></td>
	            		<td align="center" class="listtd"><c:out value="${item.codeOrder}"/></td>
	            	</tr>
	        	</c:forEach>
	        </table>
	    </div>
	    <div id="paging">
        		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
        		<form:hidden path="pageIndex" />
        	</div>
	    <div id="sysbtn">
	    	<ul>
	    		<li></li>
	    		<li>
	        	    <span class="btn_blue_l">
	            	    <a href="/code/codeRegister.do">코드등록</a>
	                	<img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
	                </span>
	            </li>
	        </ul>
	    </div>
	</div>
	</form:form>
</body>
</html>
