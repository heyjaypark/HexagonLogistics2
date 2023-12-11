package product.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import product.service.ProductListService;
import product.service.ProductPage;


public class ProductListArticleHandler implements CommandHandler {
	
	
	private ProductListService listService = new ProductListService();
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)throws Exception{
		// TODO Auto-generated method stub
	String pageNoVal=req.getParameter("pageNo");
	int pageNo=1;
	if(pageNoVal!=null) {
		pageNo=Integer.parseInt(pageNoVal);
	}
	ProductPage productPage=listService.getProductPage(pageNo);
	req.setAttribute("productPage", productPage);
	
	
	return "/WEB-INF/view/Productlist.jsp";
	}
	
	
}






