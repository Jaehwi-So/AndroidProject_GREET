package com.four.greet.dao;

import org.apache.ibatis.session.SqlSession;

import com.four.greet.vo.NametagVO;

public interface NametagDAO {
	public void setSqlSession(SqlSession sqlSession);
	public int insert(String key, String image);
	public NametagVO select(String key);
	public int delete(String key);
}
