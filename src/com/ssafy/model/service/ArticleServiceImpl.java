package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.ArticleDto;
import com.ssafy.model.dao.ArticleDao;
import com.ssafy.model.dao.ArticleDaoImpl;

public class ArticleServiceImpl implements ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleServiceImpl() {
		articleDao = new ArticleDaoImpl();
	}

	@Override
	public void writeArticle(ArticleDto articleDto) throws Exception {
		if(articleDto.getSubject() == null || articleDto.getContent() == null) {
			throw new Exception();
		}
		articleDao.writeArticle(articleDto);
	}

	@Override
	public List<ArticleDto> listArticle(String key, String word) throws Exception {
		key = key == null ? "" : key;
		word = word == null ? "" : word;
		return articleDao.listArticle(key, word);
	}

	@Override
	public ArticleDto getArticle(int articleno) throws Exception {
		return articleDao.getArticle(articleno);
	}

	@Override
	public void modifyArticle(ArticleDto articleDto) throws Exception {
		articleDao.modifyArticle(articleDto);
	}

	@Override
	public void deleteArticle(int articleno) throws Exception {
		articleDao.deleteArticle(articleno);
	}

}
