package org.ecom.journalController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ecom.journalBeans.CommentsModel;
import org.ecom.journalBeans.CriticismModel;
import org.ecom.journalBeans.JournalModel;
import org.ecom.journalBeans.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * Document Controller Class
 * 
 */
@Controller
public class ReadDocumentController {

	/**
	 * Try to fetch a document from database to read
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/readDocument")
	public ModelAndView readDocument(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");// submissionId
		String title = request.getParameter("title");
		String Abstract = request.getParameter("Abstract");
		String publishDate = request.getParameter("publishDate");
		String author = request.getParameter("author");
		String roleId = request.getParameter("roleId");
		String version = "0";

		ModelAndView mav = new ModelAndView("readDocument");

		UserModel use = new UserModel();
		mav.addObject("ListAuthors",
				use.getAllAuthorsSub(Integer.valueOf(author)));
		mav.addObject("ListMainAuthorInfo",
				use.getMainAuthorInfo(Integer.valueOf(author)));

		mav.addObject("id", id);// submissionId
		mav.addObject("title", title);
		mav.addObject("Abstract", Abstract);
		mav.addObject("publishDate", publishDate);
		mav.addObject("author", author);
		mav.addObject("version", version);
		mav.addObject("roleId", roleId);

		CriticismModel crit = new CriticismModel();
		CommentsModel com = new CommentsModel();
		mav.addObject("ListCriticism",
				crit.getAllCriticismbySubId(Integer.valueOf(id)));
		mav.addObject("ListComments", com.getComments(Integer.valueOf(id)));
		JournalModel jou = new JournalModel();
		mav.addObject("template", jou.getTemplate());

		mav.addObject("fileIdd", jou.getFileSubmission(Integer.valueOf(id)));
		return mav;

	}

	/**
	 * Fetch Abstract of a document
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/readAbstract")
	public ModelAndView readAbstract(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		String url = request.getParameter("url");
		String title = request.getParameter("title");
		String Abstract = request.getParameter("Abstract");
		String publishDate = request.getParameter("publishDate");
		String author = request.getParameter("author");
		String roleId = request.getParameter("roleId");
		String mainAuthorId = request.getParameter("mainAuthorId");

		ModelAndView mav = new ModelAndView("readAbstract");
		UserModel use = new UserModel();
		mav.addObject("ListAuthors",
				use.getAllAuthorsSub(Integer.valueOf(mainAuthorId)));
		mav.addObject("ListMainAuthorInfo",
				use.getMainAuthorInfo(Integer.valueOf(mainAuthorId)));

		mav.addObject("id", id);
		mav.addObject("url", url);
		mav.addObject("title", title);
		mav.addObject("Abstract", Abstract);
		mav.addObject("publishDate", publishDate);
		mav.addObject("author", author);
		mav.addObject("roleId", roleId);
		JournalModel jou = new JournalModel();
		mav.addObject("template", jou.getTemplate());

		return mav;

	}

}
