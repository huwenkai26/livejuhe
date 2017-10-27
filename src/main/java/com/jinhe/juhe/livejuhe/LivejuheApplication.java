package com.jinhe.juhe.livejuhe;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;

import org.springframework.boot.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class LivejuheApplication {


	@RequestMapping("/livejuhe")
	@ResponseBody
	String livejuhe() {
		return new KingboxController().domain();
	}

	@RequestMapping(value="/livejuhe/{id}", method = {RequestMethod.GET})
	@ResponseBody
	Platforminfo livejuhe2(@PathVariable(value="id") Integer id) {
		return new KingboxController2().domain(id);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(LivejuheApplication.class, args);
	}
}