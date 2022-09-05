package site.metacoding.red.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.boards.LoginDto;
import site.metacoding.red.web.dto.request.users.JoinDto;

@RequiredArgsConstructor
@Controller
public class UsersController {
   
	private final HttpSession session; //스프링이 서버시작시에 loC 컨테이너에 보관함.
    private final UsersDao usersDao;
   
   @GetMapping("/logout")
   public String logout() {
	   session.invalidate();
	   return "redirect:/";
   }
   
   @PostMapping("/login") // 로그인만 예외로 select인데 post로 함.
   public String login(LoginDto loginDto) {
	   Users userPS = usersDao.login(loginDto);
	   if(userPS != null) { //로그인 인증됨.
		   session.setAttribute("principal", userPS);
		   return "redirect:/";
	   }else { // 인증 안됨.
		   System.out.println(userPS.getUsername()); 
		   return "redirect:/loginForm";
	   }

   }
   
   @PostMapping("/join")
   public String join(JoinDto joinDto) {
	  usersDao.insert(joinDto);
      return "redirect:/loginForm";
   }
   
   @GetMapping("/loginForm")
   public String loginForm() {
      return "users/loginForm";
   }
   
   @GetMapping("/joinForm")
   public String joinForm() {
      return "users/joinForm";
   }
}