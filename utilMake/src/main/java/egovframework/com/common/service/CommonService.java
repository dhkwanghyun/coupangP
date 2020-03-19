package egovframework.com.common.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface CommonService {

	Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;

	List<EgovMap> selectSampleCodeList(Map<String, Object> map) throws Exception;

}
