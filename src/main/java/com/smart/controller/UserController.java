package com.smart.controller;

import java.io.File;
import com.smart.common.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.dao.UserRepository;
import com.smart.entities.Bucket;
import com.smart.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@ModelAttribute
	public void addCommonData(Model model,Principal principal)
	{
		String username=principal.getName();
		System.out.println("Username :"+username);
		User user=userRepository.getUserByUserName(username);
		 model.addAttribute("user",user);
	}
	
	
	@GetMapping("/home")
	public String home()
	{
		return "normal/home";
	}
	
	@GetMapping("/add_document")
	public String addDocument(Model model)
	{
		model.addAttribute("title","Add Document Page");
		model.addAttribute("bucket",new Bucket());
		return "normal/add_document";
		
	}
	
	
	@PostMapping("/process_document")
	public String processContact(@ModelAttribute Bucket bucket,@RequestParam("profileImage")MultipartFile file, Principal principal,HttpSession session,Model model)
	{
		try
		{
			
		String name=principal.getName();
		User user=this.userRepository.getUserByUserName(name);
		bucket.setUser(user);
		user.getBucketList().add(bucket);
		//processing and uploading file .......
		
		if(file.isEmpty())
		{
			 // If file is empty then try our message
			System.out.println("File not available");
			bucket.setImageURL("contact.jpg");
		}
		else
		{
			//file to folder and update name of contact
			bucket.setImageURL(file.getOriginalFilename());
			File f=new ClassPathResource("static/img").getFile();
			Path path=Paths.get(f.getAbsolutePath()+File.separator+file.getOriginalFilename());
			Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Image is uploaded");
		}
		
		this.userRepository.save(user);
		
		System.out.println("document added");
		
		//message success ....
		//model.addAttribute("user",user);
		session.setAttribute("message",new Message("success","Your document is added !! Add more"));
		}catch(Exception e)
		{
			System.out.println("Exception "+e.getMessage());
			e.printStackTrace();
			//message error
			session.setAttribute("message",new Message("danger","Something went wrong!! try again"));
		}
		
		return "redirect:/user/add_document";
	}
	
	
	@GetMapping("/show_documents")
	public String showContacts()
	{
		return "normal/show_documents";
	}
	
}
