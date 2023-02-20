package com.smart.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.smart.common.Message;
import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.exception.UserException;


@Controller
public class HomeController {

	@Autowired
	private UserRepository  userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/")
	public String home()
	{
		System.out.println("This is home page");
		return "home";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model)
	{
		System.out.println("This is login page");
		model.addAttribute("title","Login Page");
		model.addAttribute("user",new User());
		
		return "login";
	}
	
	@GetMapping("/signup")
	public String signupPage(Model model)
	{
		System.out.println("This is signup page");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@PostMapping("/register_new_user")
	public String registerNewUser(@Valid @ModelAttribute User user,BindingResult result,Model model,HttpSession session)
	{
		System.out.println("resullt"+result);
		if(result.hasErrors())
		{
			return "signup";
		}
		else
		{
			try
			{
			if(!user.isAgreed())
			{
				throw new UserException("You have not agreed the terms and conditions");
			}
			else
			{
				System.out.println(user);
				user.setRole("ROLE_USER");
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				User u=userRepository.save(user);
				System.out.println(u);
				model.addAttribute("user",new User());
				session.setAttribute("message",new Message("alert-success","Successfully registered"));
			}
			}catch(Exception exception)
			{
				System.out.println(exception.getMessage());
				model.addAttribute("user",user);
				session.setAttribute("message", new Message("alert-error","Something went wrong :"+exception.getMessage()));
			}
		}
		
		
		return "signup";
	}

	@GetMapping("/signin")
	public String customLogin(Model model)
	{
	System.out.println("This is signin page");
	model.addAttribute("title","Login Page");
	return "login";
	}
}
