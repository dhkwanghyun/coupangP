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
package egovframework.com.sample.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.common.service.CommonService;
import egovframework.com.sample.service.EgovSampleService;
import egovframework.com.sample.vo.SampleDefaultVO;
import egovframework.com.sample.vo.SampleVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
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
public class EgovSampleController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name="commonService")
	private CommonService commonService;

	/**
	 * 글 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/egovSampleList.do")
	public String selectSampleList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> sampleList = sampleService.selectSampleList(searchVO);
		model.addAttribute("resultList", sampleList);

		int totCnt = sampleService.selectSampleListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "sample/egovSampleList";
	}

	/**
	 * 글 등록 화면을 조회한다.
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/fileDorpTest.do", method = RequestMethod.GET)
	public String fileDorpTest(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		//CodeVO codeVO = CodeVO.getInstance();
		//JsonArray codeJson = new JsonParser().parse(codeVO.getCodeStr()).getAsJsonArray();
		//System.out.println("egovSampleList : "+codeJson.get(0));
		//JsonObject data = (JsonObject) codeJson.get(0);
		//System.out.println(data.get("codeValue"));
		return "sample/uploadAjax";
	}

	@RequestMapping(value = "/uploadAjax.ajax", method = RequestMethod.POST)
	public ModelAndView fileAjax(SampleVO sampleVO,MultipartFile file,Model model) throws Exception {
		Map<String,Object> hashMap = new HashMap<>();
		hashMap.put("orgFileName", file.getOriginalFilename());
		hashMap.put("fileSize", file.getSize());
		hashMap.put("orgFileType", file.getContentType());

		ModelAndView modelAndView = new ModelAndView("jsonView",hashMap);
		return modelAndView;
	}

	/**
	 * 글 등록 화면을 조회한다.
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addSample.do", method = RequestMethod.GET)
	public String addSampleView(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("sampleVO", new SampleVO());
		return "sample/egovSampleRegister";
	}

	/**
	 * 글을 등록한다.
	 * @param sampleVO - 등록할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addSample.do", method = RequestMethod.POST)
	public String addSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO,
						    HttpServletRequest files,
							//@ModelAttribute안 붙여 있으면 스프링 알아서 매핑 form:form commandName가 일치
							SampleVO sampleVO,
							BindingResult bindingResult,
							Model model,
							SessionStatus status)
			throws Exception {

		// Server-Side Validation
		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "sample/egovSampleRegister";
		}

		if(sampleService.fileTestInsert(sampleVO,files)){

		}
		//sampleService.insertSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

	/**
	 * 글 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping("/updateSampleView.do")
	public String updateSampleView(@RequestParam("selectedId") String id, @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		SampleVO sampleVO = new SampleVO();
		sampleVO.setId(id);
		// 변수명은 CoC 에 따라 sampleVO
		model.addAttribute(selectSample(sampleVO, searchVO));
		List<?> list = sampleService.selectFileList(sampleVO);
		model.addAttribute("list",list);

		return "sample/egovSampleRegister";
	}

	/**
	 * 글을 조회한다.
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	public SampleVO selectSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
		return sampleService.selectSample(sampleVO);
	}

	/**
	 * 글을 수정한다.
	 * @param sampleVO - 수정할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/updateSample.do")
	public String updateSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO, BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {

		/*EgovMap egovMap = new EgovMap();
		List<EgovMap> sampCodeList = commonService.selectSampleCodeList(egovMap);
		JSONArray jsonArray = JSONArray.fromObject(sampCodeList);
		System.out.println("mybeanList - " + jsonArray);
		CodeVO codeVO = CodeVO.getInstance();
		codeVO.setCodeStr(jsonArray.toString());*/

		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "sample/egovSampleRegister";
		}

		sampleService.updateSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

	/**
	 * 글을 삭제한다.
	 * @param sampleVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/deleteSample.do")
	public String deleteSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO, SessionStatus status) throws Exception {
		sampleService.deleteSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

}
