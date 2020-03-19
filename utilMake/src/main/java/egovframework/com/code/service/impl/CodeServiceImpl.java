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
package egovframework.com.code.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmmn.util.CodeUtil;
import egovframework.com.code.service.CodeService;
import egovframework.com.code.vo.CodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : Sample Business Implement Class
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

@Service("codeService")
public class CodeServiceImpl extends EgovAbstractServiceImpl implements CodeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeServiceImpl.class);

	// TODO mybatis 사용
	@Resource(name="codeMapper")
	private CodeMapper codeDAO;

	@Override
	@PostConstruct
	public void selectCodeList() {
		EgovMap egovMap = new EgovMap();
		List<CodeVO> sortCodeList = codeDAO.selectCodeList(egovMap);
		CodeUtil codeUtil = CodeUtil.getInstance();
		codeUtil.setCodeUtilStr(sortCodeList);
		System.out.println("intit() = "+sortCodeList.size());
	}

	@Override
	public int codeRegisterInsert(CodeVO codeVO) {
		return codeDAO.codeRegisterInsert(codeVO);
	}

	@Override
	public CodeVO selectCodeMenuInfo(CodeVO codeVO) {
		return codeDAO.selectCodeMenuInfo(codeVO);
	}

	@Override
	public List<CodeVO> selectCodeMenuList(CodeVO codeVO) {
		return codeDAO.selectCodeMenuList(codeVO);
	}

	@Override
	public int selectCodeMenuListTotCnt(CodeVO codeVO) {
		return codeDAO.selectCodeMenuListTotCnt(codeVO);
	}


}
