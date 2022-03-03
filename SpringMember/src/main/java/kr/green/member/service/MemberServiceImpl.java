package kr.green.member.service;

import java.util.HashMap;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.member.dao.MemberDAO;
import kr.green.member.dao.MemberRoleDAO;
import kr.green.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MemberRoleDAO memberRoleDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public MemberVO loginOk(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO logoutOk(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(MemberVO memberVO) {
		log.info("insert 호출 : " + memberVO);
		if(memberVO!=null) {
			// DB에 저장한다.
			memberDAO.insert(memberVO);
			memberRoleDAO.insert(memberVO.getUserid());
			// 메일을 발송한다.
			sendEmail(memberVO);
		}
	}

	@Override
	public void update(MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassword(MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberVO updateUse(String userid, String col1) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("use", "1");
		map.put("userid", userid);
		map.put("col1", col1);
		memberDAO.updateUse(map); // DB의 use값을 변경하고
		return memberDAO.selectUserId(userid); // 변경된 값을 리턴
	}

	@Override // 동일한 아이디의 개수를 리턴한다. 0이면 없는 아이디 그외의 수면 존재하는 아이디  
	public int idCheck(String userid) {
		return memberDAO.selectCountByUserId(userid);
	}

	@Override
	public MemberVO idSearch(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO passwordSearch(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 회원 가입시 인증 메일 보내는 메서드 
	public void sendEmail(MemberVO memberVO) {
		String subject = "회원 가입을 축하드립니다.";
		String to = memberVO.getEmail();
		String content = "반갑습니다. " + memberVO.getUsername() + "님!!!<br>"
                + "회원 가입을 축하드립니다.<br> "
        		+ "회원 가입을 완료하려면 다음의 링크를 클릭해서 인증하시기 바랍니다.<br>"
                + "<a href='http://localhost:8080/member/confirm?userid="+memberVO.getUserid()+"&col1="+memberVO.getCol1()+"'>인증</a><br>";
		
        MimeMessagePreparator preparator = getMessagePreparator(to, subject, content);
        try {
            mailSender.send(preparator);
            System.out.println("메일 보내기 성공 ***************************************************");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
	
	// 메일 내용 완성
    private MimeMessagePreparator getMessagePreparator(String to, String subject, String content) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            	helper.setFrom("itsungnam202111@gmail.com");
            	helper.setTo(to);
            	helper.setSubject(subject);
            	helper.setText(content, true);
            }
        };
        return preparator;
    }

}
