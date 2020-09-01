package com.four.greet.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.four.greet.dao.NametagDAO;
import com.four.greet.vo.NametagVO;

public class NametagServiceImpl implements NametagService{
	@Autowired 
	ServletContext application; 
	@Autowired
	HttpServletRequest request;
	
	private NametagDAO dao;
	public void setDao(NametagDAO dao) {
		this.dao = dao;
	}
	public Map<String, String> insert_nametag(String key, MultipartFile photo) {
		String filename = "no_file";
		String webPath = "/resources/upload/";
		String savePath = application.getRealPath(webPath);
		System.out.println(savePath);

		if(!photo.isEmpty()) {
			filename = photo.getOriginalFilename();

			File saveFile = new File(savePath, filename);
			if( !saveFile.exists() ) {
				saveFile.mkdirs();
			}else {
				long time = System.currentTimeMillis();
				filename = String.format("%d_%s", time, filename);
				saveFile = new File(savePath, filename);
			}

			try {
				photo.transferTo(saveFile);
				System.out.println("이미지를 저장하였습니다. : " + filename);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("파일이 존재하지 않습니다");
				
			} 

		}
		else {
			System.out.println("photo empty");
		}
		    
	    int res = dao.insert(key, filename);
	    Map<String, String> result = new HashMap<String, String>();
	    if(res == 1) {
		    result.put("result", "success");
		    System.out.println("success" + filename);
	    }
	    else{
		    result.put("result", "fail");
		    System.out.println("fail" + filename);
	    }
	    
	    return result;
	}
	
	public Map<String, String> nametag_selectOne(String key){
		NametagVO vo = dao.select(key);
		Map<String, String> res = new HashMap<String, String>();
		if(vo == null) {
			res.put("result", "fail");
			res.put("key", "fail");
			res.put("image", "fail");
		}
		else {
			res.put("result", "success");
			res.put("key", vo.getKey());
			res.put("image", vo.getImage());
		}
		return res;
	}
	
	public Map<String, String> nametag_deleteOne(String key){
		Map<String, String> res = new HashMap<String, String>();
		NametagVO vo = dao.select(key);
		if(vo == null) {
			res.put("result", "fail");
			return res;
		}
		int del_res = dao.delete(key);
		if(del_res != 1) {
			res.put("result", "fail");
		}
		else {
			res.put("result", "success");
		}
		return res;
	}
}
	