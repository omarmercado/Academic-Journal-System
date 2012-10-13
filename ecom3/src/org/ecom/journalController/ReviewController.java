package org.ecom.journalController;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ecom.journalBeans.CriticismModel;
import org.ecom.journalBeans.JournalModel;
import org.ecom.journalBeans.ReviewModel;
import org.ecom.journalBeans.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Review Controller class
 * 
 * 
 */
@Controller
public class ReviewController {

	@Autowired
	private sesionInfo sesion;

	/**
	 * List all reviews of a article
	 * 
	 * @return
	 */
	@RequestMapping("/listReviews")
	public ModelAndView listReviews() {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0) {
			mav = new ModelAndView("/listReviews");

		} else {
			mav = new ModelAndView("/auth");
		}

		return mav;
	}

	/**
	 * Start to review an article
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/doSelectReview", method = RequestMethod.GET)
	public @ResponseBody
	String SelectReview(HttpServletRequest req) {

		String userId = req.getParameter("userId");
		String submissionId = req.getParameter("submissionId");

		String result = "";

		ArrayList reviews = new ArrayList();

		ReviewModel rev = new ReviewModel();

		reviews = rev.getReviewsfromUser(Integer.valueOf(userId));

		if (reviews.size() < 3) {
			result = rev.selectReview(Integer.valueOf(userId),
					Integer.valueOf(submissionId));
			rev.deleteTempSelect(Integer.valueOf(userId),
					Integer.valueOf(submissionId), "NO");
		} else {
			result = "Limit of 3 reviews";
		}

		return result;
	}

	/**
	 * Remove a template from journal
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deleteTempSelect", method = RequestMethod.GET)
	public @ResponseBody
	String deleteTempSelect(HttpServletRequest req) {

		String userId = req.getParameter("userId");
		String submissionId = req.getParameter("submissionId");

		String result = "";

		ArrayList reviews = new ArrayList();

		ReviewModel rev = new ReviewModel();

		result = rev.deleteTempSelect(Integer.valueOf(userId),
				Integer.valueOf(submissionId), "OK");

		return result;
	}

	/**
	 * Select an article for reviewing
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/doSelectReviewTemp", method = RequestMethod.GET)
	public @ResponseBody
	String doSelectReviewTemp(HttpServletRequest req) {

		String userId = req.getParameter("userId");
		String submissionId = req.getParameter("submissionId");

		String result = "";

		ArrayList reviews = new ArrayList();

		ReviewModel rev = new ReviewModel();

		reviews.addAll(rev.getReviewsfromUser(Integer.valueOf(userId)));
		reviews.addAll(rev.getReviewsfromUserTemp(Integer.valueOf(userId)));

		if (reviews.size() < 3) {
			result = rev.selectReviewTemp(Integer.valueOf(userId),
					Integer.valueOf(submissionId));
		} else {
			result = "Limit of 3 reviews";
		}

		return result;
	}

	/**
	 * View profile of a user
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile(HttpServletRequest req, HttpServletResponse res) {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0
				&& Integer.valueOf(sesion.getUserRole()) == 3) {
			String userId = req.getParameter("userId");

			UserModel use = new UserModel();

			mav = new ModelAndView("/profile");
			mav.addObject("title", "Previews Reviews ");
			mav.addObject("listPreviews",
					use.getReviewerProfile(Integer.valueOf(userId)));
			JournalModel jou = new JournalModel();
			mav.addObject("template", jou.getTemplate());
		} else {
			mav = new ModelAndView("/auth");
		}
		return mav;
	}
}
