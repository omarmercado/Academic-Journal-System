package org.ecom.journalController;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ecom.journalBeans.JournalModel;
import org.ecom.journalBeans.SearchModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * Search Controller Class
 * 
 */
@Controller
public class SearchController {
	/**
	 * Start to search through all accepted articles
	 * 
	 * @return
	 */
	@RequestMapping("/search")
	public ModelAndView search() {
		JournalModel jou = new JournalModel();
		ModelAndView mav = new ModelAndView("/search");
		mav.addObject("template", jou.getTemplate());
		return mav;
	}

	/**
	 * Searching all accepted artciles based on text
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/doSearch", method = RequestMethod.GET)
	public ModelAndView doSearch(HttpServletRequest req, HttpServletResponse res) {

		String searchInput = req.getParameter("txtSearch");

		String option = req.getParameter("searchOPT");

		SearchModel s = new SearchModel();
		ArrayList search = new ArrayList();
		search.add(searchInput);

		ModelAndView mav = new ModelAndView("/search");
		mav.addObject("response", s.execSearch(option, search));
		JournalModel jou = new JournalModel();
		mav.addObject("template", jou.getTemplate());
		return mav;
	}

	/**
	 * Searching all accepted artciles based on date
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/doSearch2", method = RequestMethod.GET)
	public ModelAndView doSearch2(HttpServletRequest req,
			HttpServletResponse res) {

		String searchInput = req.getParameter("date1");
		String searchInput2 = req.getParameter("date2");
		String option = "date";

		SearchModel s = new SearchModel();
		ArrayList search = new ArrayList();
		search.add(searchInput);
		search.add(searchInput2);

		ModelAndView mav = new ModelAndView("/search");
		mav.addObject("response", s.execSearch(option, search));
		JournalModel jou = new JournalModel();
		mav.addObject("template", jou.getTemplate());
		return mav;
	}

}
