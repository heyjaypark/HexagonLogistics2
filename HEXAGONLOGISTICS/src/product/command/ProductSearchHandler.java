package product.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import product.model.Product;
import product.service.ProductSearchService;

public class ProductSearchHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/Productregi.jsp";
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
		/*
		 * String p_name=req.getParameter("p_name"); String
		 * p_seoulval=req.getParameter("p_seoul"); String
		 * p_suwonval=req.getParameter("p_suwon"); String
		 * p_incheonval=req.getParameter("p_incheon"); String
		 * p_priceval=req.getParameter("price");
		 */
		int p_no = 0;
		/*
		 * int p_seoul = 0; int p_suwon = 0; int p_incheon = 0; int price = 0;
		 */
	
		
		p_no=Integer.parseInt(p_noval);
		/*
		 * p_seoul=Integer.parseInt(p_seoulval); p_suwon=Integer.parseInt(p_suwonval);
		 * p_incheon=Integer.parseInt(p_incheonval); price=Integer.parseInt(p_priceval);
		 */
	
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		errors.put("notnull",Boolean.FALSE);
		
		/*
		 * try { User user = loginService.login(id, password);
		 * req.getSession().setAttribute("authUser", user);
		 * res.sendRedirect(req.getContextPath() + "/index.jsp"); return null; } catch
		 * (LoginFailException e) { errors.put("idOrPwNotMatch", Boolean.TRUE); return
		 * FORM_VIEW; }
		 * 
		 */
		
		try { p_no=Integer.parseInt(p_noval);
		
			Product product1 = productsearch.SearchProduct(p_no);
			errors.put("notnull",Boolean.TRUE);
			req.setAttribute("product1", product1);
			return "/WEB-INF/view/Productlist.jsp";
			}catch(NumberFormatException e) {errors.put("NumberFormatException", Boolean.TRUE);
				return "/WEB-INF/view/Productlist.jsp";
			}
		
		
	}
}
