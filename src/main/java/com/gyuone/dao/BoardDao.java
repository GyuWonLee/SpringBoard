package com.gyuone.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.gyuone.model.ArticleVO;

@Mapper
@Repository("boardDao")
public interface BoardDao { // 인터페이스 추상메서드
	// 이클립스에서 mapper와 동일하게 작동 spring이 알아서 찾아감
	public List<ArticleVO> selectAllArticles() throws DataAccessException;
	public ArticleVO selectArticle(int articleNo) throws DataAccessException;
	public void insertArticle(ArticleVO article) throws DataAccessException;
	public void updateArticle(ArticleVO article) throws DataAccessException;
	public void deleteArticle(int articleNo) throws DataAccessException; 
	
}
