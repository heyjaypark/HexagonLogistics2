package product.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import mvc.command.CommandHandler;
import product.model.Product;
import product.service.ProductSearchService;

public class ProductSearchHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/Productlist.jsp";
	private ProductSearchService productsearch = new ProductSearchService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		String p_noval=req.getParameter("p_no");
		int p_no = 0;
		p_no=Integer.parseInt(p_noval);
	
		System.out.println(p_no);

		
			Product product1 = productsearch.SearchProduct(p_no);
			System.out.println(product1);
			req.setAttribute("product1", product1);
			return "/WEB-INF/view/Productlist.jsp";
		
	}
}
