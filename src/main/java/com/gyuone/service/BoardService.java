package com.gyuone.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gyuone.dao.BoardDao;
import com.gyuone.model.ArticleVO;

@Service("boardService")
@Transactional // 트랜잭션 처리가 필요할 때, 갱신하고 지우고 한 단계에서 처리하고 싶을 때 트랜잭션을 쓸 수 있음.
// 트랜잭션 처리는 두 개의 작업을 한번에 묶어서 처리 출금, 입금 한 번에 묶어서 ..  
public class BoardService {
	private BoardDao boardDao;

	Logger logger = LoggerFactory.getLogger("com.gyuone.service.BoardService");

	@Autowired
	public BoardService(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articleList = boardDao.selectAllArticles();
		return articleList;
	}
	
	public void addArticle(ArticleVO article) {
		boardDao.insertArticle(article);
	}
	
	public ArticleVO viewArticle(int articleNo) {
		ArticleVO article;
		article = boardDao.selectArticle(articleNo);
		return article;
	}
	
	public void editArticle(ArticleVO article) {
		boardDao.updateArticle(article);
	}
	
	public void removeArticle(int articleNo) {
		boardDao.deleteArticle(articleNo);
	}
}