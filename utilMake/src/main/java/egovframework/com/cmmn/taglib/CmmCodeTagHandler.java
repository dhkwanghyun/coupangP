package egovframework.com.cmmn.taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import egovframework.com.cmmn.util.CodeUtil;
import egovframework.com.code.vo.CodeVO;

public class CmmCodeTagHandler extends MultiTagHandler {

	@Override
	public void doTag() throws IOException{
		List<CodeVO> codeList = CodeUtil.codeList(pCodeValue, codeAllYn);

		StringBuffer sb = new StringBuffer();
		sb.append(generateSelectTag(codeList));
		JspWriter out = getJspContext().getOut();
		out.print(sb.toString());
	}
}
