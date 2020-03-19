package egovframework.com.cmmn.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import egovframework.com.code.vo.CodeVO;

public class CodeUtil{

	private static CodeUtil CodeUtil;

	private List<CodeVO> CodeUtilStr;

	private CodeUtil() {
		CodeUtilStr = null;
	}

	public static synchronized CodeUtil getInstance()
	{
		if(CodeUtil == null)
		{
			CodeUtil = new CodeUtil();
		}
		return CodeUtil;
	}

	public List<CodeVO> getCodeUtilStr() {
		return CodeUtilStr;
	}

	public synchronized void setCodeUtilStr(List<CodeVO> CodeUtilStr) {
		this.CodeUtilStr = CodeUtilStr;
	}

	public static JsonArray codeListToJson(String srchCode,boolean codeAllYn){
		String codeJsonStr = new Gson().toJson(CodeUtil.getCodeUtilStr());
		//System.out.println("egovSampleList : "+codeJsonStr);
		//JsonObject jsonObject = new Gson().fromJson(codeJsonStr, JsonObject.class);
		if(codeAllYn){
			JsonArray codeJsonArray = (JsonArray) new JsonParser().parse(codeJsonStr).getAsJsonArray();
			//System.out.println("codeJsonArray : "+codeJsonArray.get(0));
			return codeJsonArray;
		}else{
			JsonArray codeJsonArrayTmp = (JsonArray) new JsonParser().parse(codeJsonStr).getAsJsonArray();
			JsonArray codeJsonArray = new JsonArray();
			srchCode = (srchCode == null) ? "": srchCode;
			for(int i=0;i<codeJsonArrayTmp.size();i++){
				JsonObject data = (JsonObject) codeJsonArrayTmp.get(i);
				String codeParent = data.get("codeParent").toString().replace("\"", "");
				System.out.println("codeParent : "+codeParent);
				System.out.println("srchCode : "+srchCode);
				if(codeParent.equals(srchCode)){
					codeJsonArray.add(codeJsonArrayTmp.get(i));
				}
			}
			return codeJsonArray;
		}
	}

}
