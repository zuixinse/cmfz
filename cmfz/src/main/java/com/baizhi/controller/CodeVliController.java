package com.baizhi.controller;

import com.baizhi.util.CreateValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/code")
public class CodeVliController {
    @RequestMapping("image")
    public void image(HttpSession session, HttpServletResponse response) throws IOException {
        CreateValidateCode vCode = new CreateValidateCode(100, 30, 4, 10);
        session.setAttribute("code", vCode.getCode()); //保存在Session作用域
        vCode.write(response.getOutputStream());
    }
}
