package sales.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import sales.model.RegistRequest;
import sales.service.RegistSalesService;

public class RegistSalesHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/RegistSales.jsp";
	private RegistSalesService salesService = new RegistSalesService();
	
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
		RegistRequest salesReq = new RegistRequest();
		int p_no = 0;
		int s_seoul = 0;
		int s_suwon = 0;
		int s_incheon = 0;
		
		String p_noVal = req.getParameter("p_no");
		String s_seoulVal = req.getParameter("s_seoul");
		String s_suwonVal = req.getParameter("s_suwon");
		String s_incheonVal = req.getParameter("s_incheon");
		
		p_no = Integer.parseInt(p_noVal);
		s_seoul = Integer.parseInt(s_seoulVal);
		s_suwon = Integer.parseInt(s_suwonVal);
		s_incheon = Integer.parseInt(s_incheonVal);
		
		salesReq.setP_no(p_no);
		salesReq.setP_name(req.getParameter("p_name"));
		salesReq.setS_seoul(s_seoul);
		salesReq.setS_seoul(s_seoul);
		salesReq.setS_suwon(s_suwon);
		salesReq.setS_incheon(s_incheon);

		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		salesReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		return FORM_VIEW;
	}
}
