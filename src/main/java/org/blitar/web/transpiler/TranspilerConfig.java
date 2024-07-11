package org.blitar.web.transpiler;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.bachelor.transpiler.pl1transpiler.mapper.Mapper;
import org.bachelor.transpiler.pl1transpiler.parser.Pl1Parser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TranspilerConfig {

	@Bean
	public Pl1Parser parserService(InputStream stream) {
		return new Pl1Parser(stream);
	}
	
	@Bean
	public Mapper mapperService() {
		return new Mapper();
	}
	
	@Bean
	public InputStream streamService(String input) {
		return new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
	}
	
	@Bean
	public String codeService() {
		return new Code().getPli();
	}
	
}
