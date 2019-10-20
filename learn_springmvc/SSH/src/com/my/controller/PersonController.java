package com.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.model.Person;
import com.my.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@RequestMapping("/toForm.do")
	public String toForm() {
		return "form";
	}

	@RequestMapping("/savePerson.do")
	public String savePerson(Person person) {
      personService.savePerson(person);
      return "success";
	}
}
