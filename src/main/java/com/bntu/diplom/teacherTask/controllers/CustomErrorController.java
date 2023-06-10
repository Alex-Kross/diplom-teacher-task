package com.bntu.diplom.teacherTask.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    String error(HttpServletRequest request, Model model) {
//        Enumeration<String> attributeNames = request.getAttributeNames();
//        String message = attribute1.getMessage();
//
//        Iterator<String> iterator = attributeNames.asIterator();
//        List<String> listAttr = new ArrayList<>();
//        List<Object> listOb = new ArrayList<>();
//        while(iterator.hasNext()){
//            String next = iterator.next();
//            listAttr.add(next);
//            Object attribute = request.getAttribute(next);
//            listOb.add(attribute);
//
//        }
        Exception attribute1 =(Exception)request.getAttribute("org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR");
        model.addAttribute("message", attribute1);
        return "error";
    }
}
