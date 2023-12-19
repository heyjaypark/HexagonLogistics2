package sales.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sales.service.SalesPage;
import sales.service.ListSalesService;
import mvc.command.CommandHandler;

public class SalesListHandler implements CommandHandler {
	
	private ListSalesService listService = new ListSalesService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		/*
		 * pageNo 파라미터 값을 이용해서 읽어올 페이지 번호를 구한다.
		 * */
		String pageNoVal = req.getParameter("pageNo");
		/*
		 * 가장 첫 페이지 설정을 1번째 페이지로 해 놓는다는 뜻
		 * */
		int pageNo = 1; 
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		/*
		 * ListSalesHandler를 이용해서 지정한 페이지 번호에 해당하는 게시글 데이터를 구한다.
		 * */
		
		SalesPage salesPage = listService.getSalesPage(pageNo);
		/*
		 * SalesPage 객체를 JSP에서 사용할 수 있도록 request의 salesPage 속성에 저장한다. 
		 * */
		req.setAttribute("salesPage", salesPage);
		return "/WEB-INF/view/ListSales_overview.jsp";
		
	}

	
	
}
