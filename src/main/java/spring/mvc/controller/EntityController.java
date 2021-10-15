package spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import spring.mvc.data.Entity;
import spring.mvc.data.EntityRepository;

/**
 * 「もの(Entity)」データベース設定
 */
@RestController
@RequestMapping("")
public class EntityController {
	@Autowired
	protected EntityRepository entityRepo;

	/**
	 * 「もの」データベース設定画面
	 */
	@GetMapping("")
	public ModelAndView home(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}

	/**
	 * 「もの」入力
	 * 遷移先 {@link #entityEntry(Entity, ModelAndView) entityEntry(Entity, ModelAndView)}
	 */
	@GetMapping("entity")
	public ModelAndView entity(@ModelAttribute("entity") Entity entity, ModelAndView mav) {
		mav.addObject("entity", entity);
		mav.setViewName("entity");
		return mav;
	}
	/**
	 * 「もの」登録
	 */
	@PostMapping("entity")
	public ModelAndView entityEntry(@ModelAttribute("entity") Entity entity, ModelAndView mav) {
		System.out.println(entity);
		mav.setViewName("entityEntry");
		entityRepo.save(entity);
		return mav;
	}

	/**
	 * 「もの」一覧
	 */
	@GetMapping("entityList")
	public ModelAndView entityList(ModelAndView mav) {
		mav.addObject("list", entityRepo.findAll());
		mav.setViewName("entityList");
		return mav;
	}
}
