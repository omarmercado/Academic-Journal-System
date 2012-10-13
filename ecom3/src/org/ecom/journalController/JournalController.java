package org.ecom.journalController;

import org.ecom.journalBeans.ArticleModel;
import org.ecom.journalBeans.JournalModel;
import org.ecom.journalBeans.emailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Journal Controller Class
 * 
 * 
 */
@Controller
public class JournalController {

	@Autowired
	private sesionInfo sesion;

	/**
	 * It will try to login a user to whole system
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView helloWorld() {
		return new ModelAndView("login", "message", "Spring MVC Demo");
	}

	/**
	 * Redeirect to Index page
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {

		ArticleModel art = new ArticleModel();
		JournalModel jou = new JournalModel();

		ModelAndView mav = new ModelAndView("/index");
		mav.addObject("listVolume", art.getVolumes());
		mav.addObject("JournalInfo", jou.getJournslInfo());
		mav.addObject("template", jou.getTemplate());
		mav.addObject("volume", "x");

		return mav;
	}

	/**
	 * Retrun time for a specific user based on system date/time system
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/time", method = RequestMethod.GET)
	public @ResponseBody
	String getTime(@RequestParam String user) {
		String result = "Time for " + user + " is " + new Date().toString();
		return result;
	}

	/**
	 * Select a an issue based on volume number
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/choose", method = RequestMethod.GET)
	public ModelAndView choose(HttpServletRequest req, HttpServletResponse res) {

		ModelAndView mav = null;

		String volume = req.getParameter("volume");
		String issue = req.getParameter("issue");
		String selV = req.getParameter("selV");

		if (volume == null) {
			volume = "";
		}
		if (issue == null) {
			issue = "0";
		}
		if (volume.equals("")) {
			volume = "";
		}
		if (issue.equals("")) {
			issue = "0";
		}

		if (volume.length() < 1) {
			volume = selV;
		}
		ArticleModel art = new ArticleModel();
		mav = new ModelAndView("/index");
		mav.addObject("listVolume", art.getVolumes());
		mav.addObject("listIssue", art.getIssue(volume));
		mav.addObject("ListAllArticles",
				art.getArticlesIndex(volume, Integer.valueOf(issue)));
		JournalModel jou = new JournalModel();
		mav.addObject("template", jou.getTemplate());
		mav.addObject("volume", volume);
		mav.addObject("JournalInfo", jou.getJournslInfo());
		return mav;
	}

	/**
	 * Create a journal based on its respective title and objective
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/createJournal")
	public ModelAndView createJournal(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0
				&& Integer.valueOf(sesion.getUserRole()) == 3) {
			mav = new ModelAndView("/ControlPanelEditor");

			String title = req.getParameter("txtTile");
			String objectives = req.getParameter("txtObj");

			JournalModel jou = new JournalModel();
			jou.createJournal(title, objectives);
		} else {
			mav = new ModelAndView("/auth");
		}
		return mav;

	}

	/**
	 * Send a message from reader to Author
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/sendLetter")
	public ModelAndView sendLetter(HttpServletRequest req,
			HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("/sendLetter");

		String ArticleId = req.getParameter("txtArticleId");
		mav.addObject("ArticleId", ArticleId);
		JournalModel jou = new JournalModel();
		mav.addObject("template", jou.getTemplate());

		return mav;

	}

	/**
	 * Send Email from reader to Main Editor
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/send")
	public ModelAndView send(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("/sendLetter");

		String ArticleId = req.getParameter("txtArticleId");
		String Email = req.getParameter("txtEmail");
		String Letter = req.getParameter("txtLetter");

		ArrayList artInf = new ArrayList();

		ArticleModel art = new ArticleModel();
		artInf = art.getArticleInfo(ArticleId);

		String content = "Letter send by Reader     :    " + Letter
				+ "            "
				+ "               The Reader email to respond is     :      "
				+ Email + "               Article ID  :    " + ArticleId
				+ "    " + "               Article Belongs to Issue  "
				+ artInf.get(0) + "     "
				+ "               Article Title   :     " + artInf.get(1)
				+ "               ABSTRACT  :      " + artInf.get(2);
		emailSender e = new emailSender();

		// MAIN EDITOR'S EMAIL
		e.sendEmail("ecom.editor@gmail.com", "Article Discussion", content);

		mav.addObject("response",
				"Letter has been send, response will be sento to your email address");
		JournalModel jou = new JournalModel();
		mav.addObject("template", jou.getTemplate());
		mav.addObject("ArticleId", ArticleId);

		return mav;

	}

	/**
	 * Send email from reader to Author
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/sendAuthor")
	public ModelAndView sendAuthor(HttpServletRequest req,
			HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("/sendLetter");

		String ArticleId = req.getParameter("txtArticleId2");
		String Email = req.getParameter("txtEmail2");
		String Letter = req.getParameter("txtLetter2");

		ArrayList artInf = new ArrayList();

		ArticleModel art = new ArticleModel();
		artInf = art.getArticleInfo(ArticleId);

		String content = "Letter send by Reader     :    " + Letter
				+ "            "
				+ "               The Reader email to respond is     :      "
				+ Email + "               Article ID  :    " + ArticleId
				+ "    " + "               Article Belongs to Issue  "
				+ artInf.get(0) + "     "
				+ "               Article Title   :     " + artInf.get(1)
				+ "               ABSTRACT  :      " + artInf.get(2);
		emailSender e = new emailSender();

		// EDITORS EMAIL
		e.sendEmail(artInf.get(3).toString(), "Article Discussion", content);

		mav.addObject("response",
				"Letter has been send, response will be sento to your email address");
		JournalModel jou = new JournalModel();
		mav.addObject("template", jou.getTemplate());
		mav.addObject("ArticleId", ArticleId);
		return mav;

	}

	/**
	 * Send a message from a user to author
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/postLetter")
	public ModelAndView postLetter(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0
				&& Integer.valueOf(sesion.getUserRole()) == 3) {
			mav = new ModelAndView("/postLetter");
			JournalModel jou = new JournalModel();
			mav.addObject("template", jou.getTemplate());
		} else {
			mav = new ModelAndView("/auth");
		}

		return mav;

	}

	/**
	 * Post a letter to an author from reader
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/postLet")
	public ModelAndView postLet(HttpServletRequest req, HttpServletResponse res) {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0
				&& Integer.valueOf(sesion.getUserRole()) == 3) {
			mav = new ModelAndView("/postLetter");

			String ArticleId = req.getParameter("txtArticleId");
			String Letter = req.getParameter("txtLetter");

			JournalModel jou = new JournalModel();
			jou.postLetter(ArticleId, Letter);

			mav.addObject("response", "Letter has been posted Correctly");
			mav.addObject("template", jou.getTemplate());
		} else {
			mav = new ModelAndView("/auth");
		}
		return mav;

	}

	/**
	 * Basic email subscription
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/subscription")
	public ModelAndView subscription(HttpServletRequest req,
			HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("/subscription");
		JournalModel jou = new JournalModel();
		mav.addObject("template", jou.getTemplate());
		return mav;
	}

	/**
	 * Email subsription
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/subscribe")
	public ModelAndView subscribe(HttpServletRequest req,
			HttpServletResponse res) {

		String issue = req.getParameter("txtissue");
		String keyword = req.getParameter("txtkeyword");
		String email = req.getParameter("txtEmail");

		JournalModel jou = new JournalModel();
		jou.subscribe(issue, keyword, email);

		ModelAndView mav = new ModelAndView("/subscription");
		mav.addObject("template", jou.getTemplate());
		mav.addObject("response", "Subscription has been posted Correctly");
		return mav;
	}

	@RequestMapping("/resign")
	public ModelAndView resign(HttpServletRequest req, HttpServletResponse res) {

		ModelAndView mav = null;

		JournalModel jou = new JournalModel();
		String response = jou.resign(sesion.userId);

		if (response.equals("OK")) {
			mav = new ModelAndView("/index");
		} else {
			mav = new ModelAndView("/ControlPanelEditor");
		}

		mav.addObject("template", jou.getTemplate());
		mav.addObject("resign", response);
		return mav;
	}
}
