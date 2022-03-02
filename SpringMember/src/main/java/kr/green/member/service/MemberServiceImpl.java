package kr.green.member.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import kr.green.member.dao.MemberDAO;
import kr.green.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private JavaMailSender mailSender;

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
		// TODO Auto-generated method stub
		
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
	public void updateUse(MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void idCheck(MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void idSearch(MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passwordSearch(MemberVO memberVO) {
		// TODO Auto-generated method stub
		
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
