package article.command;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticlePage;
import article.service.ListArticleService;
import mvc.command.CommandHandler;


public class ListArticleHandler implements CommandHandler {
	
	
	
	private ListArticleService listService = new ListArticleService();
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)throws Exception{
		// TODO Auto-generated method stub
	String pageNoVal=req.getParameter("pageNo");
	int pageNo=1;
	if(pageNoVal!=null) {
		pageNo=Integer.parseInt(pageNoVal);
	}
	ArticlePage articlePage=listService.getArticlePage(pageNo);
	
	req.setAttribute("articlePage", articlePage);
	
	Enumeration<String> attributes = req.getSession().getAttributeNames();
	while (attributes.hasMoreElements()) {
	    String attribute = (String) attributes.nextElement();
	    System.err.println(attribute+" : "+req.getSession().getAttribute(attribute));
	}
	
	return "/WEB-INF/view/listArticle.jsp";
	}
	
}






