package org.ecom.journalController;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ecom.journalBeans.ArticleModel;
import org.ecom.journalBeans.CommentsModel;
import org.ecom.journalBeans.CriticismModel;
import org.ecom.journalBeans.JournalModel;
import org.ecom.journalBeans.ReviewModel;
import org.ecom.journalBeans.SearchModel;
import org.ecom.journalBeans.SubmissionModel;
import org.ecom.journalBeans.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Submission Controller Class
 * 
 * 
 */
@Controller
public class SubmissionController {

	@Autowired
	private sesionInfo sesion;

	/**
	 * Start submitting a file to server
	 * 
	 * @return
	 */
	@RequestMapping("/Submission")
	public ModelAndView Submission() {

		ModelAndView mav = new ModelAndView("/newSubmission");
		JournalModel jou = new JournalModel();
		mav.addObject("template", jou.getTemplate());

		return mav;
	}

	/**
	 * Submit a file to server
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/doSubmission", method = RequestMethod.GET)
	public ModelAndView doSubmission(HttpServletRequest req,
			HttpServletResponse res) {

		String Title = req.getParameter("txtTitle");
		String Abstract = req.getParameter("txtAbstract");

		// main author
		String txtMAfName = req.getParameter("txtMAfName");
		String txtMALName = req.getParameter("txtMALName");
		String txtMAEmail = req.getParameter("txtMAEmail");

		// User 2
		String txtU2fName = req.getParameter("txtU2fName");
		String txtU2LName = req.getParameter("txtU2LName");
		String txtU2Email = req.getParameter("txtU2Email");

		// User 3
		String txtU3fName = req.getParameter("txtU3fName");
		String txtU3LName = req.getParameter("txtU3LName");
		String txtU3Email = req.getParameter("txtU3Email");

		// User 4
		String txtU4fName = req.getParameter("txtU4fName");
		String txtU4LName = req.getParameter("txtU4LName");
		String txtU4Email = req.getParameter("txtU4Email");

		String keywords = req.getParameter("txtKeywords");

		ArrayList users = new ArrayList();
		ArrayList data = new ArrayList();

		String response = "";
		String submissionId = "";
		SubmissionModel sub = new SubmissionModel();

		response = sub.checkForSubmissionEmail(txtMAEmail.trim());

		ModelAndView mav = new ModelAndView("/newSubmission");
		if (response.equals("OK")) {

			UserModel usu = new UserModel();
			String MainAuthor = String.valueOf(usu.createMainAuthor(txtMAfName,
					txtMALName, txtMAEmail));

			if (!txtU2Email.equals("")) {
				data.add(txtU2fName);
				data.add(txtU2LName);
				data.add(txtU2Email);
				users.add(data.clone());
				data.clear();

			}
			if (!txtU3Email.equals("")) {
				data.add(txtU3fName);
				data.add(txtU3LName);
				data.add(txtU3Email);
				users.add(data.clone());
				data.clear();

			}

			if (!txtU4Email.equals("")) {
				data.add(txtU4fName);
				data.add(txtU4LName);
				data.add(txtU4Email);
				users.add(data.clone());
				data.clear();
			}

			submissionId = sub.startNewSubmission(Title, Abstract, users,
					MainAuthor, keywords);

			if (submissionId.length() > 0) {
				response = "OK";
			}

		}

		mav.addObject("submissionId", submissionId);// submissionId
		mav.addObject("response", response);// submissionId
		JournalModel jou = new JournalModel();
		mav.addObject("template", jou.getTemplate());

		return mav;
	}

	/**
	 * List out all availble submission on the server
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/availableSubmissions", method = RequestMethod.GET)
	public ModelAndView availableSubmissions(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = null;

		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0) {

			String userId = req.getParameter("userId");

			SubmissionModel sub = new SubmissionModel();
			ArrayList result = new ArrayList();
			ArrayList result2 = new ArrayList();
			mav = new ModelAndView("/availableSubmissions");

			ArrayList reviews = new ArrayList();
			ReviewModel rev = new ReviewModel();

			reviews = rev.getReviewsfromUser(Integer.valueOf(userId));

			if (reviews.size() < 3) {
				result = sub.getAllSubmissionForRev(Integer.valueOf(userId));

			}

			result2 = rev.getReviewsfromUser(Integer.valueOf(userId));
			result2.addAll(rev.getReviewsfromUserTemp(Integer.valueOf(userId)));

			mav.addObject("ListReviewsUser", result2);

			mav.addObject("ListReviews", result);
			mav.addObject("userId", userId);
			JournalModel jou = new JournalModel();
			mav.addObject("template", jou.getTemplate());
		} else {
			mav = new ModelAndView("/auth");
		}
		return mav;
	}

	/**
	 * Let user to resubmit a file again
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/reSubmission", method = RequestMethod.GET)
	public ModelAndView reSubmission(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0) {
			String Title = req.getParameter("txtTitle");
			String Abstract = req.getParameter("txtAbstract");
			String submissionId = req.getParameter("txtSubmissionId");

			mav = new ModelAndView("/reSubmit");
			SubmissionModel newsub = new SubmissionModel();

			String response = newsub.reSubmission(
					Integer.valueOf(submissionId), Title, Abstract);
			CriticismModel crit = new CriticismModel();
			mav.addObject("ListCriticism",
					crit.getAllCriticismbySubId(Integer.valueOf(submissionId)));
			mav.addObject("id", submissionId);

			mav.addObject("response", response);
		} else {
			mav = new ModelAndView("/auth");
		}
		return mav;
	}

	/**
	 * let user to resubmit a file again
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/reSubmit", method = RequestMethod.GET)
	public ModelAndView reSubmit(HttpServletRequest req, HttpServletResponse res) {

		String Title = req.getParameter("title");
		String Abstract = req.getParameter("Abstract");
		String submissionId = req.getParameter("id");
		String userID = req.getParameter("userID");

		ModelAndView mav = new ModelAndView("/reSubmit");
		CriticismModel crit = new CriticismModel();
		mav.addObject("ListCriticism",
				crit.getAllCriticismbySubId(Integer.valueOf(submissionId)));
		mav.addObject("id", submissionId);
		mav.addObject("title", Title);
		mav.addObject("Abstract", Abstract);
		mav.addObject("userID", userID);
		JournalModel jou = new JournalModel();
		mav.addObject("template", jou.getTemplate());
		return mav;
	}

	/**
	 * After submitting try to enque the file into publishing queue
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/publishDocument")
	public ModelAndView publishDocument(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0
				&& Integer.valueOf(sesion.getUserRole()) == 3) {

			String submissionId = req.getParameter("submissionId");
			String title = req.getParameter("title");
			String Abstract = req.getParameter("abstract");
			String author = req.getParameter("author");

			mav = new ModelAndView("/publishDocument");
			mav.addObject("submissionId", submissionId);
			mav.addObject("title", title);
			mav.addObject("Abstract", Abstract);
			mav.addObject("author", author);

			UserModel use = new UserModel();
			mav.addObject("ListAuthors",
					use.getAllAuthorsSub(Integer.valueOf(author)));
			mav.addObject("ListMainAuthorInfo",
					use.getMainAuthorInfo(Integer.valueOf(author)));

			CriticismModel crit = new CriticismModel();
			CommentsModel com = new CommentsModel();
			ReviewModel rev = new ReviewModel();
			mav.addObject("ListCriticism",
					crit.getAllCriticismbySubId(Integer.valueOf(submissionId)));
			mav.addObject("ListComments",
					com.getComments(Integer.valueOf(submissionId)));
			mav.addObject("ListReview",
					rev.getReviewsfromSub(Integer.valueOf(submissionId)));

			JournalModel jou = new JournalModel();
			mav.addObject("fileIdd",
					jou.getFileSubmission(Integer.valueOf(submissionId)));

		} else {
			mav = new ModelAndView("/auth");
		}
		return mav;
	}

	/**
	 * Publish file as article
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/publish")
	public ModelAndView publish(HttpServletRequest req, HttpServletResponse res) {

		String submissionId = req.getParameter("txtSubmissionId");
		String title = req.getParameter("txtTitle");
		String Abstract = req.getParameter("txtAbstract");

		ModelAndView mav = new ModelAndView("/succesfulPublish");

		ArticleModel art = new ArticleModel();

		art.publishDocument(Integer.valueOf(submissionId), title, Abstract);

		mav.addObject("responsePub",
				"Publishing has been succesfull , close window and go back to Control Panel");

		return mav;
	}

	/**
	 * Reject the file to not to publish it furthermore
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/rejectReview")
	public ModelAndView rejectReview(HttpServletRequest req,
			HttpServletResponse res) {

		String reviewId = req.getParameter("reviewId");
		ModelAndView mav = new ModelAndView("/publishDocument");
		ReviewModel rev = new ReviewModel();
		mav.addObject("response", rev.rejectReview(Integer.valueOf(reviewId)));

		return mav;
	}

	/**
	 * Findout any confilct of a file
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/conflictReview")
	public ModelAndView conflictReview(HttpServletRequest req,
			HttpServletResponse res) {

		String reviewId = req.getParameter("reviewId");
		ModelAndView mav = new ModelAndView("/publishDocument");
		ReviewModel rev = new ReviewModel();
		mav.addObject("response", rev.conflictReview(Integer.valueOf(reviewId)));

		return mav;
	}

	/**
	 * After accepting a file as an article if article rejects it can be catched
	 * by the help of this function
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/rejectArticle")
	public ModelAndView rejectArticle(HttpServletRequest req,
			HttpServletResponse res) {

		String submissionId = req.getParameter("submissionId");
		ModelAndView mav = new ModelAndView("/publishDocument");
		ReviewModel rev = new ReviewModel();
		mav.addObject("response",
				rev.rejectArticle(Integer.valueOf(submissionId)));

		return mav;
	}

}
