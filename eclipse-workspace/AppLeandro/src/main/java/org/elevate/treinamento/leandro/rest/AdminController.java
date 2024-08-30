package org.elevate.treinamento.leandro.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

		    @GetMapping("hello")
		    public String Hello() {
		    	return "Hello";
		    	}

}
