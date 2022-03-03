package kr.green.member.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.green.member.service.MemberService;
import kr.green.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/insertForm")
	public String insertForm() {
		return "insertForm";
	}

	@RequestMapping(value = "/insertOk", method=RequestMethod.GET)
	public String insertOk() {
		return "redirect:/";
	}
	
	@RequestMapping(value = "/insertOk", method=RequestMethod.POST)
	public String insertOk(@ModelAttribute MemberVO memberVO, Model model) {
		memberVO.setCol1(UUID.randomUUID().toString());
		memberVO.setPassword(bCryptPasswordEncoder.encode(memberVO.getPassword())); // 비번 암호화
		memberService.insert(memberVO); // DB에 저장
		model.addAttribute("vo", memberVO);
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/login")
	public String login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			Model model
			) {
		if(error!=null) {
			model.addAttribute("error", "아이디가 없거나 비번이 일치하지 않습니다.");
		}
		if(logout!=null) {
			model.addAttribute("msg", "성공적으로 로그아웃을 했습니다.");
		}
		return "login";
	}
	
	
	@RequestMapping(value = "/idCheck", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String idCheck(@RequestParam(required = false) String userid) {
		String userids[] = "admin,root,master,webmaster,administrator".split(","); // 금지 아이디 목록
		int count = 0;
		for(String id : userids) { 
			if(userid.equals(id)) {
				count=1;
				break;
			}
		}
		if(count==0) {
			// 서비스를 호출하여 동일한 아이디의 개수를 얻어소 count변수에 넣는다.
			count = memberService.idCheck(userid);
		}
		return count + "";
	}
	// 이메일 인증처리
	@RequestMapping(value = "/confirm")
	public String confirm(@RequestParam String userid, @RequestParam String col1, Model model) {
		log.debug("confirm 호출 : " + userid + ", " + col1);
		MemberVO memberVO = memberService.updateUse(userid, col1); // use값을 1로 변경
		model.addAttribute("memberVO", memberVO);
		return "confirm";
	}
}
