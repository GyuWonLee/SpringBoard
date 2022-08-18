package com.gyuone.Controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gyuone.model.ArticleVO;
import com.gyuone.service.BoardService;

@Controller("boardController")
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	ArticleVO articleVo;
	
	List<ArticleVO> articleList = new ArrayList<ArticleVO>();
	
	Logger logger = LoggerFactory.getLogger("com.gyuone.controller.BoardController");
	
	@RequestMapping({"/listArticles","/"}) // 배열로 때림
	public String getArticleList(Model model) {
		logger.info("===============> getArticleList 메서드 진입");
		articleList = boardService.listArticles();
		model.addAttribute("articleList", articleList);
		return "listArticles";
	}
	
	@RequestMapping("/mlist")
	@ResponseBody
	public List<ArticleVO> getArticleListForMobile() {
		articleList = boardService.listArticles();
		return articleList;
	}
	
	
	@RequestMapping("/newArticle")
	public String writeArticle(Model model) {
		return "articleForm";
		
	}
	
	@RequestMapping(value="/addArticle", method=RequestMethod.POST)
	public String addArticle(Model model, @RequestParam(value="title") String title,
			@RequestParam(value="content") String content) {
		articleVo.setTitle(title);
		articleVo.setContents(content);
		articleVo.setWriteId("bit");
		boardService.addArticle(articleVo);
		
		return "redirect:listArticles"; // client ---> server addArticle로 들어온거.
		// server----> client 요청한거 다 끝냈으니깐 listArticles로 더 불러줄래? ---> listArticles로 다시 불러줌.
	}
	
	@RequestMapping(value="/viewArticle", method=RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam(value="articleno") String articleNo) {
		articleVo = boardService.viewArticle(Integer.parseInt(articleNo));
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewArticle");
		mv.addObject("article", articleVo);
		
		return mv;
	}
	
	@RequestMapping(value="/editArticle", method=RequestMethod.POST)
	public String editArticle(@RequestParam(value="articleNo") String articleNo,
			@RequestParam(value="title") String title, @RequestParam(value="content") String content,
			RedirectAttributes redirect) {
		articleVo.setArticleNo(Integer.parseInt(articleNo));
		articleVo.setTitle(title);
		articleVo.setContents(content);
		articleVo.setWriteId("bit");
		boardService.editArticle(articleVo);
		
		redirect.addAttribute("articleno", articleNo);
		return "redirect:viewArticle";
	}
	
	@RequestMapping(value="/removeArticle", method=RequestMethod.POST)
	public String removeArticle(@RequestParam(value="articleNo") String articleNo) {
		boardService.removeArticle(Integer.parseInt(articleNo));
		return "redirect:listArticles";
	}
}
