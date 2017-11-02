package com.jinhe.juhe.livejuhe.Controller;

import com.jinhe.juhe.livejuhe.Exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionController {
    @RequestMapping("/json")
    @ResponseBody
    public String json() throws MyException {
        System.out.println("发生错误");
        throw new MyException("发生错误2");
    }
}