package product.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import mvc.command.CommandHandler;
import product.model.ProductRequest;
import product.service.NoMinusException;
import product.service.ProductRegiService;

public class ProductRegiHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/Productregi.jsp";
	private ProductRegiService productService = new ProductRegiService();

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
		ProductRequest productReq = new ProductRequest();
		String p_noval = req.getParameter("p_no");
		String p_seoulval = req.getParameter("p_seoul");
		String p_suwonval = req.getParameter("p_suwon");
		String p_incheonval = req.getParameter("p_incheon");
		String p_priceval = req.getParameter("price");
		int p_no = 0;
		int p_seoul = 0;
		int p_suwon = 0;
		int p_incheon = 0;
		int price = 0;

		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		errors.put("numberInsert", null);
		errors.put("duplicateno", Boolean.FALSE);

		/* productReq.validate(errors); */

		/*
		 * if (!errors.isEmpty()) { return FORM_VIEW; }
		 */ 
		 
		 

		try {
			
			p_no = Integer.parseInt(p_noval);
			p_seoul = Integer.parseInt(p_seoulval);
			p_suwon = Integer.parseInt(p_suwonval);
			p_incheon = Integer.parseInt(p_incheonval);
			price = Integer.parseInt(p_priceval);
if(p_seoul<0 || p_suwon<0 || p_incheon<0) {
			throw new NoMinusException();
			


}else {
	productReq.setP_no(p_no);
	productReq.setP_name(req.getParameter("p_name"));
	productReq.setP_seoul(p_seoul);
	productReq.setP_suwon(p_suwon);
	productReq.setP_incheon(p_incheon);
	productReq.setPrice(price);
	System.out.println(p_no);
	
	productService.productregi(productReq);
	 errors.put("successRegi", Boolean.TRUE); 
	 return "/WEB-INF/view/Productregi.jsp"; 
} }catch(NoMinusException e) {
	errors.put("NoMinus", Boolean.TRUE);
	return FORM_VIEW;
	
	

			
	
		} catch (NumberFormatException e) {

			// TODO: handle exception
			errors.put("numberInsert", Boolean.TRUE);
			return FORM_VIEW;
		}catch (DuplicateIdException e) {

			// TODO: handle exception
			errors.put("duplicateno", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
}



