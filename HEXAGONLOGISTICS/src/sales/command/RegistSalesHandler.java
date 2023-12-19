package sales.command;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		

		int p_no = 0;
		int s_seoul = 0;
		int s_suwon = 0;
		int s_incheon = 0;
		
		String p_noVal = req.getParameter("p_no");
		String s_seoulVal = req.getParameter("s_seoul");
		String s_suwonVal = req.getParameter("s_suwon");
		String s_incheonVal = req.getParameter("s_incheon");
		String s_dateVal = req.getParameter("s_date");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = null;
		java.sql.Date s_date = null;
		

		
	    // 판매량을 하나라도 입력하지 않았을 경우 
	    if (s_seoulVal.trim().isEmpty() && s_suwonVal.trim().isEmpty() && s_incheonVal.trim().isEmpty()) {
	        errors.put("salesError", true);
	        return FORM_VIEW;
	    }
	    
	    try {
	    if (s_dateVal == null || s_dateVal.trim().isEmpty()) {
	    	errors.put("salesDateError", true);
	    	return FORM_VIEW;
	    } else {
	    	utilDate = dateFormat.parse(s_dateVal);
	    	s_date = new java.sql.Date(utilDate.getTime());
	    }
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
		
		try {
		p_no = Integer.parseInt(p_noVal);
		s_seoul = !s_seoulVal.isEmpty() ? Integer.parseInt(s_seoulVal) : 0;
		s_suwon = !s_suwonVal.isEmpty() ? Integer.parseInt(s_suwonVal) : 0;
		s_incheon = !s_incheonVal.isEmpty() ? Integer.parseInt(s_incheonVal) : 0;
		

		
		salesReq.setP_no(p_no);
		salesReq.setS_seoul(s_seoul);
		salesReq.setS_suwon(s_suwon);
		salesReq.setS_incheon(s_incheon);
		salesReq.setS_date(s_date);

		
		System.out.println("p_no from request: " + p_noVal);
		System.out.println("p_noVal: " + p_noVal);
		System.out.println("s_seoulVal: " + s_seoulVal);
		System.out.println("s_seoul: " + s_seoul);
		System.out.println("s_suwonVal: " + s_suwonVal);
		System.out.println("s_suwon: " + s_suwon);
		System.out.println("s_incheonVal: " + s_incheonVal);
		System.out.println("s_incheon: " + s_incheon);
		System.out.println("s_dateVal: " + s_dateVal);
		System.out.println("s_date: " + s_date);


		salesReq.validate(errors);
		
		

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		

		salesService.registSales(salesReq);
		return FORM_VIEW;
		} catch (NumberFormatException e) {
			errors.put("numberFormat", true);
			return FORM_VIEW;
		} catch (SQLException e) {
	        e.printStackTrace(); // 로그에 예외 정보 출력
	        errors.put("databaseError", Boolean.TRUE);
	        req.setAttribute("databaseErrorMessage", "데이터베이스 처리 중 오류가 발생했습니다.");
	        res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // HTTP 상태 코드 설정
	        return FORM_VIEW;
	    } catch (Exception e) {
	        e.printStackTrace(); // 로그에 예외 정보 출력
	        errors.put("unknownError", Boolean.TRUE);
	        req.setAttribute("unknownErrorMessage", "알 수 없는 오류가 발생했습니다.");
	        return FORM_VIEW;
		}

	}
}
