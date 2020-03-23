package egovframework.com.cmmn.taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.tagext.SimpleTagSupport;

import egovframework.com.code.vo.CodeVO;

public class MultiTagHandler extends SimpleTagSupport{
	protected String name;
	protected String values;
	protected String pCodeValue="";
	protected boolean codeAllYn=false;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public String getpCodeValue() {
		return pCodeValue;
	}
	public void setpCodeValue(String pCodeValue) {
		this.pCodeValue = pCodeValue;
	}
	public boolean isCodeAllYn() {
		return codeAllYn;
	}
	public void setCodeAllYn(boolean codeAllYn) {
		this.codeAllYn = codeAllYn;
	}



	public String generateSelectTag(List<CodeVO> codeList){
		StringBuffer sb = new StringBuffer("\n");
		sb.append("<select style=\"width:150px;\"/>");
		for(int i=0;i<codeList.size();i++){
			sb.append("<option value=\"");
			sb.append(codeList.get(i).getCodeValue());
			sb.append("\"");
			sb.append(">");
			sb.append(codeList.get(i).getCodeName());
			sb.append("</option>\n");
		}
		sb.append("</select>\n");
		return sb.toString();
	}
	public void doTag() throws IOException {
		// TODO Auto-generated method stub

	}
}
