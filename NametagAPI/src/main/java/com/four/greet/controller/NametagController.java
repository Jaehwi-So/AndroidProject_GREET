package com.four.greet.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.jsp.PageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.four.greet.service.NametagService;


@Controller
public class NametagController {

	private NametagService service;
	public void setService(NametagService service) {
		this.service = service;
	}
	//추가

	@PostMapping(value="/nametag")
	@ResponseBody
	public Map<String, String> insert(String key, MultipartFile upload) {
		Map<String, String> result = new HashMap<String, String>();
		if(upload == null) {
			result.put("result", "fail");
		}
		System.out.println("insert");		
		result = service.insert_nametag(key, upload);
		return result; 
	}
	
	@GetMapping(value = "/nametag/{key}")
	@ResponseBody
	public Map<String, String> getUserInfo(@PathVariable("key") String key) {
		Map<String, String> res = service.nametag_selectOne(key);
		System.out.println("view");		
		return res;
	}
	

	@DeleteMapping(value = "/nametag/{key}")
	@ResponseBody
	public Map<String, String> deleteUser(@PathVariable("key") String key) {
		Map<String, String> res = service.nametag_deleteOne(key);
		System.out.println("delete");		
		return res;
	}
	
}
