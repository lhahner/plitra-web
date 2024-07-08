package org.blitar.web.transpiler;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.bachelor.transpiler.pl1transpiler.mapper.Mapper;
import org.bachelor.transpiler.pl1transpiler.parser.Pl1Parser;
import org.bachelor.transpiler.pl1transpiler.symboltable.SymbolTable;
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
		try {
			String input = code.getPli();
			
			InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
			Pl1Parser parser = new Pl1Parser(stream);
			org.bachelor.transpiler.pl1transpiler.parser.SimpleNode root = parser.program();
			Mapper mapper = new Mapper(root);
			for(String expression : mapper.javaExpression) {
				code.java = code.java + expression;
			}
		}
		catch(Exception e) {
			code.setJava(e.getMessage());
		}
		return "translator";
	}
}

