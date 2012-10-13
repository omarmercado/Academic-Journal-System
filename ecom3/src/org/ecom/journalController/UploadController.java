/*
 * Author : Omar Manuel Mercado Casillas
 * Date: 14/05/2012
 * Project: Electronic Journal System
 */
package org.ecom.journalController;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ecom.journalBeans.JournalModel;
import org.ecom.journalBeans.SubmissionModel;
import org.ecom.journalBeans.UploadModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Upload controller class
 * 
 * 
 */
@Controller
@RequestMapping(value = "/upload.htm")
public class UploadController implements HandlerExceptionResolver {

	public final static String PATH = "//winsrv4/DesktopR$/acp11omm/Desktop/files/";
	public final static String FILE_TYPE = "pdf";

	/**
	 * Create a upload form
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(ModelMap model, HttpServletRequest request) {

		String submissionId = request.getParameter("submissionId");
		SubmissionModel sub = new SubmissionModel();
		String fileId = sub
				.getFileRelation(Integer.valueOf(submissionId.trim()));

		UploadModel form = new UploadModel();
		model.addAttribute("FORM", form);
		model.addAttribute("fileId", fileId);
		model.addAttribute("submissionId", submissionId);
		JournalModel jou = new JournalModel();
		model.addAttribute("template", jou.getTemplate());
		return "upload";
	}

	/**
	 * Try to catch data from client side as a binary format
	 * 
	 * @param form
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value = "FORM") UploadModel form,
			BindingResult result, HttpServletRequest request) {

		String FileId = request.getParameter("fileId");
		String submissionId = request.getParameter("submissionId");
		if (!result.hasErrors()) {
			FileOutputStream outputStream = null;

			String filePath = PATH + FileId.trim() + FILE_TYPE;
			try {
				outputStream = new FileOutputStream(new File(filePath));
				outputStream.write(form.getFile().getFileItem().get());
				outputStream.close();
				SubmissionModel sub = new SubmissionModel();
				sub.setFileUpload(Integer.valueOf(submissionId.trim()));

			} catch (Exception e) {
				System.out.println("Error while saving file");
				return "upload";
			}
			return "success";
		} else {
			return "upload";
		}
	}

	/**
	 * A helper function for resolving some binary upload problem
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception exception) {

		String error = "";

		Map<Object, Object> model = new HashMap<Object, Object>();
		if (exception instanceof MaxUploadSizeExceededException) {
			model.put(
					"errors",
					"File size should be less then "
							+ ((MaxUploadSizeExceededException) exception)
									.getMaxUploadSize() + " byte.");
		} else {

			error = "Application Error, please try again later";
			model.put("errors", error);
		}

		return new ModelAndView("/error");
	}
}