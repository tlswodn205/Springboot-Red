package site.metacoding.red.domain.boards;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WriteDto {
	private String title;
	private String content;

	public Boards toEntity(Integer usersId) {
		Boards boards = new Boards(this.title, this.content, usersId);
		return boards;
	}
}
