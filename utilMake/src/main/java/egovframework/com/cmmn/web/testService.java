package egovframework.com.cmmn.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import egovframework.com.cmmn.util.CodeUtil;
import egovframework.com.code.service.CodeService;
import egovframework.com.code.vo.CodeVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import net.sf.json.JSONArray;

@Component
public class testService implements InitializingBean {

	/** EgovSampleService */
	@Resource(name = "codeService")
	private CodeService codeService;

	@Override
	public void afterPropertiesSet() throws Exception {
		/*
		try {

			EgovMap egovMap = new EgovMap();
			List<CodeVO> sortCodeList = codeService.selectCodeList(egovMap);
			CodeUtil codeUtil = CodeUtil.getInstance();
			codeUtil.setCodeUtilStr(sortCodeList(sortCodeList));

			System.out.println("init() Code : "+codeUtil.getCodeUtilStr().size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}


}
