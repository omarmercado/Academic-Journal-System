package org.ecom.journalController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ecom.journalBeans.CommentsModel;
import org.ecom.journalBeans.CriticismModel;
import org.ecom.journalBeans.ExpertiseModel;
import org.ecom.journalBeans.JournalModel;
import org.ecom.journalBeans.ReviewModel;
import org.ecom.journalBeans.ScoreModel;
import org.ecom.journalBeans.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Document Reviwning Controller Class
 * 
 * 
 */
@Controller
public class reviewDocumentController {

	@Autowired
	private sesionInfo sesion;

	/**
	 * Start to reviewing an article
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/reviewDocument")
	public ModelAndView reviewDocument(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0) {

			String id = request.getParameter("id");// //submission ID
			String url = request.getParameter("url");
			String title = request.getParameter("title");
			String Abstract = request.getParameter("Abstract");
			String publishDate = request.getParameter("publishDate");
			String author = request.getParameter("author");
			String userID = request.getParameter("userId");
			String roleId = request.getParameter("roleId");
			String reviewId = request.getParameter("reviewId");
			String version = request.getParameter("version");

			mav = new ModelAndView("reviewDocument");
			mav.addObject("id", id);// submission ID
			mav.addObject("url", url);
			mav.addObject("title", title);
			mav.addObject("Abstract", Abstract);
			mav.addObject("publishDate", publishDate);
			mav.addObject("author", author);
			mav.addObject("userId", userID);
			mav.addObject("roleId", roleId);
			mav.addObject("reviewId", reviewId);
			mav.addObject("version", version);

			UserModel use = new UserModel();
			mav.addObject("ListAuthors",
					use.getAllAuthorsSub(Integer.valueOf(author)));
			mav.addObject("ListMainAuthorInfo",
					use.getMainAuthorInfo(Integer.valueOf(author)));

			ReviewModel rev = new ReviewModel();
			mav.addObject("reviewText",
					rev.getReviewText(Integer.valueOf(reviewId)));

			CriticismModel crit = new CriticismModel();
			CommentsModel com = new CommentsModel();
			ScoreModel sco = new ScoreModel();
			ExpertiseModel exp = new ExpertiseModel();

			mav.addObject("ListScore", sco.getScores());
			mav.addObject("ListExpertise", exp.getExpertise());

			if (roleId.equals("3")) {
				mav.addObject("ListCriticism",
						crit.getAllCriticismbySubId(Integer.valueOf(id)));
			} else {
				mav.addObject("ListCriticism", crit.getCriticismbySubIdUserId(
						Integer.valueOf(id), Integer.valueOf(userID)));
			}

			mav.addObject(
					"ListComments",
					com.getCommentsByUser(Integer.valueOf(id),
							Integer.valueOf(userID)));
			JournalModel jou = new JournalModel();
			mav.addObject("template", jou.getTemplate());

			mav.addObject("fileIdd", jou.getFileSubmission(Integer.valueOf(id)));

		} else {
			mav = new ModelAndView("/auth");
		}

		return mav;

	}

	/**
	 * Response to a comment during reviewing
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/respondComent")
	public ModelAndView respondComent(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = null;

		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0
				&& Integer.valueOf(sesion.getUserRole()) == 3) {

			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String Abstract = request.getParameter("Abstract");
			String author = request.getParameter("author");
			String userId = request.getParameter("userId");

			mav = new ModelAndView("respondComent");
			mav.addObject("id", id);// submission ID
			mav.addObject("title", title);
			mav.addObject("Abstract", Abstract);
			mav.addObject("author", author);
			mav.addObject("userId", userId);

			UserModel use = new UserModel();
			mav.addObject("ListAuthors",
					use.getAllAuthorsSub(Integer.valueOf(author)));
			mav.addObject("ListMainAuthorInfo",
					use.getMainAuthorInfo(Integer.valueOf(author)));

			CommentsModel com = new CommentsModel();

			mav.addObject("ListComments", com.getComments(Integer.valueOf(id)));
			JournalModel jou = new JournalModel();
			mav.addObject("template", jou.getTemplate());
		} else {
			mav = new ModelAndView("/auth");
		}
		return mav;
	}

	/**
	 * Criticizing an article
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/Criticism", method = RequestMethod.GET)
	public ModelAndView Criticism(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0) {
			String submissionId = req.getParameter("submissionId");
			String content = req.getParameter("content");
			String userId = req.getParameter("userId");
			String reviewId = req.getParameter("reviewId");
			String response = "";

			CriticismModel crit = new CriticismModel();

			mav = new ModelAndView("/reviewDocument");
			mav.addObject("response", crit.setCriticism(
					Integer.valueOf(submissionId), content, response,
					Integer.valueOf(userId), Integer.valueOf(reviewId)));
		} else {
			mav = new ModelAndView("/auth");
		}
		return mav;
	}

	/**
	 * Response to a criticize
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/crtResponse", method = RequestMethod.GET)
	public ModelAndView crtResponse(HttpServletRequest req,
			HttpServletResponse res) {

		String submissionId = req.getParameter("submissionId");
		String response = req.getParameter("response");
		String crtcismId = req.getParameter("crtcismId");

		CriticismModel crit = new CriticismModel();

		ModelAndView mav = new ModelAndView("/reviewDocument");
		mav.addObject(
				"CritResponse",
				crit.respondCriticism(Integer.valueOf(crtcismId),
						Integer.valueOf(submissionId), response));

		return mav;
	}

	/**
	 * Accept a criticize
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/crtAccept", method = RequestMethod.GET)
	public ModelAndView crtAccept(HttpServletRequest req,
			HttpServletResponse res) {

		String submissionId = req.getParameter("submissionId");
		String crtcismId = req.getParameter("crtcismId");

		CriticismModel crit = new CriticismModel();

		ModelAndView mav = new ModelAndView("/reviewDocument");
		mav.addObject(
				"CritResponse",
				crit.acceptCriticism(Integer.valueOf(crtcismId),
						Integer.valueOf(submissionId)));

		return mav;
	}

	/**
	 * Comment on an Article
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/Comment", method = RequestMethod.GET)
	public ModelAndView Comment(HttpServletRequest req, HttpServletResponse res) {

		String submissionId = req.getParameter("submissionId");
		String comment = req.getParameter("comment");
		String userId = req.getParameter("userId");
		String roleId = req.getParameter("roleId");
		String title = "";

		String response = "";

		CommentsModel com = new CommentsModel();

		ModelAndView mav = new ModelAndView("/reviewDocument");
		mav.addObject("ComResponse", com.newComments(comment,
				Integer.valueOf(submissionId), response,
				Integer.valueOf(userId), Integer.valueOf(roleId), title));

		return mav;
	}

	/**
	 * Response to a comment of an article
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/ComResponse", method = RequestMethod.GET)
	public ModelAndView ComResponse(HttpServletRequest req,
			HttpServletResponse res) {

		String commentId = req.getParameter("commentId");
		String response = req.getParameter("response");

		CommentsModel com = new CommentsModel();

		ModelAndView mav = new ModelAndView("/reviewDocument");
		mav.addObject("ComResponse",
				com.responseCom(Integer.valueOf(commentId), response));

		return mav;
	}

	/**
	 * Set a review on an article
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/setReview", method = RequestMethod.GET)
	public ModelAndView setReview(HttpServletRequest req,
			HttpServletResponse res) {

		String scoreId = req.getParameter("scoreId");
		String reviewerExpertiseId = req.getParameter("reviewerExpertiseId");
		String reviewId = req.getParameter("reviewId");
		String reviewText = req.getParameter("reviewText");

		ReviewModel rev = new ReviewModel();

		ModelAndView mav = new ModelAndView("/reviewDocument");
		mav.addObject(
				"setReview",
				rev.setReview(Integer.valueOf(scoreId),
						Integer.valueOf(reviewerExpertiseId),
						Integer.valueOf(reviewId), reviewText));

		return mav;
	}

	/**
	 * finishing a review of an article
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/succesfulReview", method = RequestMethod.GET)
	public ModelAndView succesfulReview(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = new ModelAndView("/succesfulReview");
		return mav;
	}
}
