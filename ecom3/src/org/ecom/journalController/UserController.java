package org.ecom.journalController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ecom.journalBeans.ArticleModel;
import org.ecom.journalBeans.CommentsModel;
import org.ecom.journalBeans.IssueModel;
import org.ecom.journalBeans.JournalModel;
import org.ecom.journalBeans.ReviewModel;
import org.ecom.journalBeans.SubmissionModel;
import org.ecom.journalBeans.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * User Controller Class
 * 
 * 
 */
@Controller
public class UserController {

	@Autowired
	private sesionInfo sesion;

	/**
	 * Start to singing up a user
	 * 
	 * @return
	 */
	@RequestMapping("/signup")
	public ModelAndView signup() {
		return new ModelAndView("/signup");
	}

	/**
	 * Sign up a user
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/doSignUp", method = RequestMethod.GET)
	public ModelAndView doSignUp(HttpServletRequest req, HttpServletResponse res) {

		String firstName = req.getParameter("txtfirstName");
		String lastName = req.getParameter("txtlastName");
		String email = req.getParameter("txtemail");
		String userName = req.getParameter("txtuserName");
		String password = req.getParameter("txtpassword");
		String role = req.getParameter("txtrole");

		UserModel user = new UserModel();

		ModelAndView mav = new ModelAndView("/signup");
		mav.addObject("response", user.SignUp(firstName.trim(),
				lastName.trim(), email.trim(), userName.trim(),
				password.trim(), role.trim()));

		return mav;
	}

	/*
	 * Start to singing in a user
	 */
	@RequestMapping("/signin")
	public ModelAndView signin() {
		JournalModel jou = new JournalModel();
		ModelAndView mav = null;
		mav = new ModelAndView("/signin");
		mav.addObject("template", jou.getTemplate());

		return mav;
	}

	/**
	 * Siging in a user
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/doSignIn", method = RequestMethod.POST)
	public ModelAndView doSignIn(HttpServletRequest req, HttpServletResponse res) {

		String userName = req.getParameter("txtUsername");
		String password = req.getParameter("txtPassword");

		UserModel user = new UserModel();

		String response = user.SignIn(userName.trim(), password.trim(), sesion);

		ModelAndView mav = null;

		if (response.equals("User Doesnt Exist")) {
			mav = new ModelAndView("/signin");
			mav.addObject("response", response);
			JournalModel jou = new JournalModel();
			mav.addObject("template", jou.getTemplate());
		} else {

			SubmissionModel submision = new SubmissionModel();
			ReviewModel review = new ReviewModel();
			ArticleModel art = new ArticleModel();
			CommentsModel com = new CommentsModel();

			UserModel use = new UserModel();

			int userId = sesion.getUserId();
			int userRole = Integer.valueOf(sesion.getUserRole().trim());

			if (userRole == 2 || userRole == 1 || userRole == 4) {
				mav = new ModelAndView("/ControlPanel");
				mav.addObject("userId", sesion.getUserId());
				mav.addObject("userName", sesion.getUsername());
				mav.addObject("ListSubmited",
						submision.getUserSubmission(userId));
				mav.addObject("ListReviews", review.getReviewsfromUser(userId));
				mav.addObject("ListArticles", art.getArtcileUser(userId));
				mav.addObject("roleId", userRole);
				mav.addObject("User", use.getUserInfo(userId));
				JournalModel jou = new JournalModel();
				mav.addObject("template", jou.getTemplate());

			} else if (userRole == 3) {
				mav = new ModelAndView("/ControlPanelEditor");

				ReviewModel rev = new ReviewModel();
				IssueModel iss = new IssueModel();
				rev.ChooseMoreReviews();// Send reminder to revieres that have
										// no reviews assign to choose more
										// reviews
				rev.SubmitYourOldReviews();// Send reminde to reviewers
											// reminding the to select more
											// reviews
				rev.CloseReviews();// Close reviews that are already 1 week old,
									// no more changes allowed

				mav.addObject("userId", sesion.getUserId());
				mav.addObject("userName", sesion.getUsername());
				mav.addObject("roleId", userRole);

				mav.addObject("ListToPublish",
						submision.getSubmissionToPublish());

				mav.addObject("ListAllArticles", art.getAllArtciles());
				mav.addObject("ListAllUsers", use.getAllUsers());
				mav.addObject("ListAllSubWCom", com.getListComEditor());

				mav.addObject("ListReviewRoleApplications",
						use.getReviewRoleApplications());
				mav.addObject("ListReviews", review.getReviewsfromUser(userId));

				mav.addObject("User", use.getUserInfo(userId));
				mav.addObject("TimeToPublish", iss.timeToPublishNewIssue());
				JournalModel jou = new JournalModel();
				mav.addObject("template", jou.getTemplate());
				mav.addObject("listTemplates", jou.getTemplates());
				mav.addObject("roleId", userRole);
				mav.addObject("resign", "");

			} else {
				mav = new ModelAndView("/auth");
			}
		}
		return mav;
	}

	/**
	 * Return back one step from whatever page a user is in there
	 * 
	 * @return
	 */
	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public ModelAndView back() {

		ModelAndView mav = null;

		SubmissionModel submision = new SubmissionModel();
		ReviewModel review = new ReviewModel();
		ArticleModel art = new ArticleModel();
		CommentsModel com = new CommentsModel();

		UserModel use = new UserModel();

		int userId = sesion.getUserId();
		int userRole = Integer.valueOf(sesion.getUserRole());

		if (userRole == 2 || userRole == 1 || userRole == 4) {
			mav = new ModelAndView("/ControlPanel");
			mav.addObject("userId", sesion.getUserId());
			mav.addObject("userName", sesion.getUsername());
			mav.addObject("ListSubmited", submision.getUserSubmission(userId));
			mav.addObject("ListReviews", review.getReviewsfromUser(userId));
			mav.addObject("ListArticles", art.getArtcileUser(userId));
			mav.addObject("roleId", userRole);
			mav.addObject("User", use.getUserInfo(userId));
			JournalModel jou = new JournalModel();
			mav.addObject("template", jou.getTemplate());
		} else if (userRole == 3) {
			mav = new ModelAndView("/ControlPanelEditor");

			ReviewModel rev = new ReviewModel();
			// rev.ChooseMoreReviews();//Send reminder to revieres that have no
			// reviews assign to choose more reviews
			// rev.SubmitYourOldReviews();//Send reminde to reviewers reminding
			// the to select more reviews
			// rev.CloseReviews();//Close reviews that are already 1 week old,
			// no more changes allowed

			mav.addObject("userId", sesion.getUserId());
			mav.addObject("userName", sesion.getUsername());
			mav.addObject("roleId", userRole);

			mav.addObject("ListToPublish", submision.getSubmissionToPublish());

			mav.addObject("ListAllArticles", art.getAllArtciles());
			mav.addObject("ListAllUsers", use.getAllUsers());
			mav.addObject("ListAllSubWCom", com.getListComEditor());

			mav.addObject("ListReviewRoleApplications",
					use.getReviewRoleApplications());
			mav.addObject("ListReviews", review.getReviewsfromUser(userId));

			mav.addObject("User", use.getUserInfo(userId));
			JournalModel jou = new JournalModel();
			mav.addObject("template", jou.getTemplate());
			mav.addObject("listTemplates", jou.getTemplates());
			mav.addObject("resign", "");
		} else {
			mav = new ModelAndView("/auth");
		}

		return mav;
	}

	/**
	 * Start to authenticating a user based on his credentials
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/user")
	public ModelAndView user(HttpServletRequest req, HttpServletResponse res) {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0) {

			mav = new ModelAndView("/user");

			String userId = req.getParameter("userId");
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String email = req.getParameter("email");
			String roleName = req.getParameter("roleName");

			mav.addObject("userId", userId);
			mav.addObject("firstName", firstName);
			mav.addObject("lastName", lastName);
			mav.addObject("email", email);
			mav.addObject("roleName", roleName);
			JournalModel jou = new JournalModel();
			mav.addObject("template", jou.getTemplate());
		} else {
			mav = new ModelAndView("/auth");
		}

		return mav;
	}

	/**
	 * Edit user information
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/editUser")
	public ModelAndView editUser(HttpServletRequest req, HttpServletResponse res) {

		ModelAndView mav = null;
		if (sesion != null) {
			if (sesion.getUserId() != 0
					&& Integer.valueOf(sesion.getUserRole()) != 0) {
				mav = new ModelAndView("/editUser");

				String userId = req.getParameter("userId");
				String firstName = req.getParameter("firstName");
				String lastName = req.getParameter("lastName");
				String email = req.getParameter("email");
				String roleName = req.getParameter("roleName");
				String userName = req.getParameter("userName");
				String passWord = req.getParameter("passWord");

				mav.addObject("userId", userId.trim());
				mav.addObject("firstName", firstName.trim());
				mav.addObject("lastName", lastName.trim());
				mav.addObject("email", email.trim());
				mav.addObject("roleName", roleName.trim());
				mav.addObject("userName", userName.trim());
				mav.addObject("passWord", passWord.trim());

				UserModel use = new UserModel();
				mav.addObject("ListRoles", use.getRoles());
				JournalModel jou = new JournalModel();
				mav.addObject("template", jou.getTemplate());

			} else {
				mav = new ModelAndView("/auth");
			}
		} else {
			mav = new ModelAndView("/auth");
		}
		return mav;
	}

	/**
	 * Update user information
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/updateUser")
	public @ResponseBody
	String updateUser(HttpServletRequest req, HttpServletResponse res) {

		ModelAndView mav = null;

		if (sesion != null) {
			if (sesion.getUserId() != 0
					&& Integer.valueOf(sesion.getUserRole()) != 0) {
				mav = new ModelAndView("/editUser");

				String userId = req.getParameter("userId");
				String firstName = req.getParameter("firstName");
				String lastName = req.getParameter("lastName");
				String email = req.getParameter("email");
				String roleName = req.getParameter("roleName");
				String userName = req.getParameter("userName");
				String passWord = req.getParameter("passWord");

				UserModel usu = new UserModel();
				usu.updateUser(Integer.valueOf(userId.trim()),
						firstName.trim(), lastName.trim(), email.trim(),
						roleName.trim(), email.trim(), passWord.trim());
			} else {
				mav = new ModelAndView("/auth");
			}
		} else {
			mav = new ModelAndView("/auth");
		}
		return "ok";
	}

	/**
	 * Update user Roles
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/applyReviewRole")
	public ModelAndView applyReviewRole(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = null;
		mav = new ModelAndView("/applyReviewRole");
		JournalModel jou = new JournalModel();

		mav.addObject("template", jou.getTemplate());
		return mav;
	}

	/**
	 * Update user Roles
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/applyReviewRoleForm")
	public @ResponseBody
	String applyReviewRoleForm(HttpServletRequest req, HttpServletResponse res) {

		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String msg = req.getParameter("msg");

		UserModel usu = new UserModel();
		usu.applyReviewer(firstName.trim(), lastName.trim(), email.trim(),
				msg.trim());

		return "Application Received, You will receive the response to your application trough your email";
	}

	/**
	 * Fetch user Role information
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/readReviewRoleApplication")
	public ModelAndView readReviewRoleApplication(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0
				&& Integer.valueOf(sesion.getUserRole()) == 3) {
			mav = new ModelAndView("/readReviewRoleApplication");

			String id = req.getParameter("id");
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String email = req.getParameter("email");
			String msg = req.getParameter("msg");

			mav.addObject("id", id);
			mav.addObject("firstName", firstName);
			mav.addObject("lastName", lastName);
			mav.addObject("email", email);
			mav.addObject("msg", msg);

			JournalModel jou = new JournalModel();
			mav.addObject("template", jou.getTemplate());
		} else {
			mav = new ModelAndView("/auth");
		}
		return mav;
	}

	/**
	 * Accepting to review an article by a user
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/acceptNewReviewer")
	public ModelAndView acceptNewReviewer(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0
				&& Integer.valueOf(sesion.getUserRole()) == 3) {
			mav = new ModelAndView("/readReviewRoleApplication");

			String id = req.getParameter("id");

			UserModel use = new UserModel();
			use.acceptNewReviewer(Integer.valueOf(id.trim()));
			JournalModel jou = new JournalModel();
			mav.addObject("template", jou.getTemplate());
			mav.addObject("response", "OK");
		} else {
			mav = new ModelAndView("/auth");
		}
		return mav;
	}

	/**
	 * Reject a review by a particular user
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/rejectNewReviewer")
	public ModelAndView rejectNewReviewer(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = null;
		if (sesion.getUserId() != 0
				&& Integer.valueOf(sesion.getUserRole()) != 0
				&& Integer.valueOf(sesion.getUserRole()) == 3) {
			mav = new ModelAndView("/readReviewRoleApplication");

			String id = req.getParameter("id");

			UserModel use = new UserModel();
			use.rejectNewReviewer(Integer.valueOf(id.trim()));
			JournalModel jou = new JournalModel();
			mav.addObject("template", jou.getTemplate());
			mav.addObject("response", "OK");
		} else {
			mav = new ModelAndView("/auth");
		}
		return mav;
	}

	/**
	 * Edit template of journal
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/changeTemp")
	public ModelAndView changeTemp(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = null;

		mav = new ModelAndView("/ControlPanelEditor");

		String id = req.getParameter("template");

		JournalModel jou = new JournalModel();
		jou.changeTemp(Integer.valueOf(id.trim()));
		mav = back();

		return mav;
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest req) {
		ModelAndView mav = null;
		mav = new ModelAndView("/index");
		JournalModel jou = new JournalModel();
		mav.addObject("template", jou.getTemplate());
		mav.addObject("JournalInfo", jou.getJournslInfo());
		sesion.setUserRole("0");
		sesion.setUserId(0);
		return mav;
	}

}
