package com.four.greet.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.four.greet.dao.NametagDAO;

public interface NametagService {
	public void setDao(NametagDAO dao);
	public Map<String, String> insert_nametag(String key, MultipartFile photo);
	public Map<String, String> nametag_selectOne(String key);
	public Map<String, String> nametag_deleteOne(String key);
}
