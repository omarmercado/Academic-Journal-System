/*
 * Author : Omar Manuel Mercado Casillas
 * Date: 14/05/2012
 * Project: Electronic Journal System
 */
package org.ecom.journalController;

import org.ecom.journalBeans.ArticleModel;
import org.ecom.journalBeans.ReviewModel;
import org.ecom.journalBeans.SubmissionModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * ControlPanel Controller Class
 * 
 */
@Controller
public class ControlPanelController {

	@RequestMapping("/ControlPanel")
	public ModelAndView start() {
		ModelAndView mav = new ModelAndView("/ControlPanel");

		return mav;

	}

}
