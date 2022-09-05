package site.metacoding.red.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.boards.WriteDto;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.response.boards.MainDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final HttpSession session;
	private final BoardsDao boardsDao;
	
	@GetMapping({"/","/boards"})
	public String getBoardList(Model model) {
		List<MainDto> boardsList = boardsDao.findAll();
		model.addAttribute("boardsList", boardsList);
		return "boards/main";
	}
	
	@PostMapping("/boards")
	public String writeBoards(WriteDto writeDto) {
		//1번 세션에 접근해서 세션값을 확인한다. 그때 Users로 다운캐스팅하고 키값은 principal로 한다.
		Users principal = (Users) session.getAttribute("principal");
		
		//2번 principal null인지 확인하고 null이면 loginForm으로 리다이렉션해준다.
		if(principal == null) {
			return "redirect:/loginForm";
		}
		
		//3번 BoardsDao에 접근해서 insert 메서드로 호출한다.
		boardsDao.insert(writeDto.toEntity(principal.getId()));
		
		return "redirect:/";
		//조건 : dto를 entity로 변환해서 인수로 담아준다.
		//조건: entity에는 세션의 principal에 getId가 필요하다.
		
	}
	
	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id, Model model) {
		Boards boards = boardsDao.findById(id);
		model.addAttribute("boards" , boards);
		return "boards/detail";
	}
	
	@GetMapping("/boards/writeForm")
	public String writeForm() {
		Users principal = (Users) session.getAttribute("principal");
		if(principal==null) {
			return "redirect:/loginForm";
		}
		return "boards/writeForm";
	}
}
