package com.mycompany.controller;


import com.mycompany.entity.UserTable;
import com.mycompany.form.UserForm;
import com.mycompany.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
/*@RequestMapping("/users")*/
public class UserController {

    @Autowired
    private UserServiceInter userService;
/*
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request){
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        String nationalityIdStr = request.getParameter("nid");
        Integer nationalityId =null;
        if(nationalityIdStr!=null && !nationalityIdStr.trim().isEmpty()){
            nationalityId =Integer.parseInt(nationalityIdStr);
        }
        List<UserTable> userList = userService.searchUsers(name,surname,nationalityId);
        request.setAttribute("userList",userList);
        return "users";
    }*/


    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ModelAndView indexM(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            @RequestParam(value = "nid", required = false) Integer nationalityId){

        List<UserTable> userList = userService.searchUsers(name,surname,nationalityId);
        ModelAndView mvm = new ModelAndView("users");
        mvm.addObject("users",userList);
        return mvm;
    }

     @RequestMapping(method = RequestMethod.GET, value = "/usersm")
     public ModelAndView indexM(@Valid @ModelAttribute("user") UserForm u, BindingResult bindingResult){
         ModelAndView mvm = new ModelAndView("users");
         if(bindingResult.hasErrors()){
             return mvm;
         }
         List<UserTable> users =  users = userService.searchUsers(u.getName(),u.getSurname(),u.getNationalityId());


         mvm.addObject("users",users);
         return mvm;
     }

    @RequestMapping(method = {RequestMethod.GET}, value = "/login")
    public String login(){
       return "/login";
    }

    @RequestMapping(method = {RequestMethod.GET}, value="/logout")
    public String logout(){
        return "logout";
    }


    @ModelAttribute("user")
     public UserForm getEmptyUserForm(){
         return new UserForm(null,null,null);
     }

}
