package egovframework.com.cmmn.taglib;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import egovframework.com.cmmn.util.CodeUtil;

public class CmmCodeTagHandler extends MultiTagHandler {

	@Override
	public void doTag() throws IOException{
		CodeUtil codeUtil = CodeUtil.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append(generateSelectTag(codeUtil.getCodeUtilStr()));
		JspWriter out = getJspContext().getOut();
		out.print(sb.toString());
	}
}
