package com.jinhe.juhe.livejuhe;

import com.jinhe.juhe.livejuhe.KingboxController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class LivejuheApplication {


	@RequestMapping("/livejuhe")
	@ResponseBody
	String livejuhe() {
		return new KingboxController().domain();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(LivejuheApplication.class, args);
	}
}