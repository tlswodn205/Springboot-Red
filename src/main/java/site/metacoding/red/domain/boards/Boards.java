package site.metacoding.red.domain.boards;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class Boards {
	private Integer id;
	private String title;
	private String content;
	private Integer userId;
	private Timestamp createdAt;
	
	public Boards(String title, String content, Integer userId) {
		super();
		this.title = title;
		this.content = content;
		this.userId = userId;
	}
	
	
}
