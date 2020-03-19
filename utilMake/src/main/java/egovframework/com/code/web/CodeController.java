package egovframework.com.code.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;

import egovframework.com.cmmn.util.CodeUtil;
import egovframework.com.code.service.CodeService;
import egovframework.com.code.vo.CodeVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;



/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
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

@Controller
public class CodeController {

	/** EgovSampleService */
	@Resource(name = "codeService")
	private CodeService codeService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;


	@RequestMapping(value = "/code/codeList.do")
	public String codeList(@ModelAttribute("codeVO") CodeVO codeVO,ModelMap model) throws Exception {
		/** EgovPropertyService.sample */
		codeVO.setPageUnit(propertiesService.getInt("pageUnit"));
		codeVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(codeVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(codeVO.getPageUnit());
		paginationInfo.setPageSize(codeVO.getPageSize());

		codeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		codeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		codeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CodeVO> codeList = codeService.selectCodeMenuList(codeVO);
		model.addAttribute("codeList", codeList);
		int totCnt = codeService.selectCodeMenuListTotCnt(codeVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		return "code/codeList";
	}

	@RequestMapping(value = "/code/codeRegister.do")
	public String codeRegister(ModelMap model) throws Exception {
		return "code/codeRegister";
	}


	@RequestMapping(value = "/code/codeGroupList.ajax" , method = RequestMethod.POST)
	public ModelAndView reserveInsert(@ModelAttribute("codeVO") CodeVO codeVO, ModelMap model) throws Exception {
		/*CodeUtil codeUtil = CodeUtil.getInstance();
		System.out.println("codeUtil SIZE : "+codeUtil.getCodeUtilStr().size());
		List<CodeVO> codeList = codeUtil.getCodeUtilStr();

		List<CodeVO> codeGroupList = new ArrayList<CodeVO>();
		for(int i=0;i<codeList.size();i++){
			if(codeVO.getSrchCode().equals("")||codeVO.getSrchCode()==null){
				if(codeList.get(i).getCodeParent().equals(codeVO.getSrchCode())){
					codeGroupList.add(codeList.get(i));
				}
			}else{
				if(codeList.get(i).getCodeParent().equals(codeVO.getSrchCode())){
					codeGroupList.add(codeList.get(i));
				}
			}
		}*/
		JsonArray codeListToJson = CodeUtil.codeListToJson(codeVO.getSrchCode(), false);
		System.out.println("codeGroupList Size : "+codeListToJson);
		ModelAndView modelAndView = new ModelAndView("jsonView","jsonList",codeListToJson.toString());
		return modelAndView;
	}

	@RequestMapping(value = "/code/codeRegister_C.do" , method = RequestMethod.POST)
	public String codeRegisterInsert(@ModelAttribute("codeVO") CodeVO codeVO, ModelMap model) throws Exception {
		int a = codeService.codeRegisterInsert(codeVO);
		codeService.selectCodeList();
		return "";
	}

	@RequestMapping(value = "/code/codeDetail.do")
	public String codeDetail(@ModelAttribute("codeVO") CodeVO codeVO,ModelMap model) throws Exception {
		codeVO = codeService.selectCodeMenuInfo(codeVO);
		model.addAttribute("codeVO", codeVO);
		return "code/codeDetail";
	}



}
