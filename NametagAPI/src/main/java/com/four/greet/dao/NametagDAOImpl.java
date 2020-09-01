package com.four.greet.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import com.four.greet.vo.NametagVO;

public class NametagDAOImpl implements NametagDAO{

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	//명함 추가
	public int insert(String key, String image){
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("image", image);
		int res = sqlSession.insert("nametag.insert", map);
		return res;
	}
	
	public NametagVO select(String key) {
		NametagVO vo = null;
		vo = sqlSession.selectOne("nametag.select", key);
		return vo;
	}
	
	public int delete(String key) {
		int res = sqlSession.delete("nametag.delete", key);
		return res;
	}

}
