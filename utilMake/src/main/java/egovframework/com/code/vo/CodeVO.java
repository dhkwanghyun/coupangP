/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.com.code.vo;

import egovframework.com.sample.vo.SampleDefaultVO;

/**
 * @Class Name : SampleVO.java
 * @Description : SampleVO Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public class CodeVO extends SampleDefaultVO {
	private String codeValue="";
	private String codeName="";
	private String codeParent="";
	private String codeOrder="";
	private String level="";
	private String codeParentNm="";
	private String codeUseYn="";

	private String srchCode="";


	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeParent() {
		return codeParent;
	}
	public void setCodeParent(String codeParent) {
		this.codeParent = codeParent;
	}
	public String getCodeOrder() {
		return codeOrder;
	}
	public void setCodeOrder(String codeOrder) {
		this.codeOrder = codeOrder;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSrchCode() {
		return srchCode;
	}
	public void setSrchCode(String srchCode) {
		this.srchCode = srchCode;
	}
	public String getCodeParentNm() {
		return codeParentNm;
	}
	public void setCodeParentNm(String codeParentNm) {
		this.codeParentNm = codeParentNm;
	}
	public String getCodeUseYn() {
		return codeUseYn;
	}
	public void setCodeUseYn(String codeUseYn) {
		this.codeUseYn = codeUseYn;
	}





}
