package org.ecom.journalController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ecom.journalBeans.IssueModel;
import org.ecom.journalBeans.JournalModel;
import org.ecom.journalBeans.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Article Controller Class
 * 
 * 
 */
@Controller
public class ArticleController {

	@Autowired
	private sesionInfo sesion;

	/**
	 * Return a Model/View controller
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/readArticle")
	public ModelAndView readArticle(HttpServletRequest req,
			HttpServletResponse res) {

		String ArticleId = req.getParameter("ArticleId");
		String title = req.getParameter("title");
		String Abstract = req.getParameter("Abstract");
		String author = req.getParameter("author");
		String MainAuthorId = req.getParameter("MainAuthorId");

		ModelAndView mav = new ModelAndView("/readArticle");

		UserModel use = new UserModel();
		JournalModel jou = new JournalModel();
		mav.addObject("ListAuthors",
				use.getAllAuthorsSub(Integer.valueOf(MainAuthorId)));
		mav.addObject("ListMainAuthorInfo",
				use.getMainAuthorInfo(Integer.valueOf(MainAuthorId)));

		mav.addObject("articleId", ArticleId);
		mav.addObject("title", title);
		mav.addObject("Abstract", Abstract);
		mav.addObject("author", author);

		String letter = jou.getArticleLetter(ArticleId);

		if (letter.length() < 1) {
			letter = "NO";
		}

		mav.addObject("letter", letter);
		mav.addObject("template", jou.getTemplate());
		mav.addObject("fileIdd", jou.getFileArticle(Integer.valueOf(ArticleId)));

		return mav;

	}

	/**
	 * Retrun Model/View of a new issue
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/newIssue")
	public ModelAndView newIssue(HttpServletRequest req, HttpServletResponse res) {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0
				&& Integer.valueOf(sesion.getUserRole()) == 3) {
			mav = new ModelAndView("/ControlPanelEditor");

			IssueModel iss = new IssueModel();
			iss.newIssue();
		} else {
			mav = new ModelAndView("/auth");
		}
		return mav;

	}

}
