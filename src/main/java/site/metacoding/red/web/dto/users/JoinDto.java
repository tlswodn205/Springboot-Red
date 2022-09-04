package site.metacoding.red.web.dto.users;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDto {
   private String username;
   private String password;
   private String email;
}