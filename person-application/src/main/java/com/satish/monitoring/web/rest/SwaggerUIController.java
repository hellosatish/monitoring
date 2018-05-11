package com.satish.monitoring.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author satish-s
 * <pre>
 *  Controller to redirect request to swagger-ui page
 * </pre>
 */
@Controller
public class SwaggerUIController {

	@RequestMapping(value = "/")
	public String index() {
		return "redirect:swagger-ui.html";
	}
}
