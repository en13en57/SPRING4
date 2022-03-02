package kr.green.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.green.member.service.TestService;
import kr.green.member.vo.TestVO;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping(value = "/test")
	public String test(Model model, 
			@RequestParam(required = false) Integer num1,
			@RequestParam(required = false) Integer num2) {
		num1 = (num1==null) ?  22: num1;
		num2 = (num2==null) ?  33: num2;

		model.addAttribute("num1", num1);
		model.addAttribute("num2", num2);
		model.addAttribute("today", testService.selectToday());
		model.addAttribute("sum", testService.selectSum(num1, num2));
		model.addAttribute("mul", testService.selectMul(num1, num2));

		TestVO testVO = new TestVO();
		testVO.setNum1(num1);
		testVO.setNum2(num2);
		model.addAttribute("vo", testService.selectVO(testVO));
		
		return "test";
	}
	
	@RequestMapping(value = "/test/download")
	public ModelAndView download(@RequestParam HashMap<Object, Object> params, ModelAndView mv) {
		String ofileName = (String) params.get("of"); // 원본이름
		String sfileName = (String) params.get("sf"); // 저장이름
		mv.setViewName("downloadView");
		mv.addObject("ofileName", ofileName);
		mv.addObject("sfileName", sfileName);
		return mv;
	}
}
