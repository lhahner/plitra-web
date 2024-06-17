package org.blitar.web.transpiler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/trans")
	public String getTranspiler(Model model) {
		model.addAttribute("code", new Code());
		return "translator";
	}
	
	@RequestMapping(value = "/docs")
	public String getDocs(Model model) {
		return "documentation";
	}
	
	@RequestMapping(value = "/v01")
	public String getV01() {
		return "v01docs";
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST) 
	public String codeSubmit(@ModelAttribute Code code, Model model) {
		model.addAttribute("code", code);
		System.out.println(code.pli);
		return "translator";
	}
}

