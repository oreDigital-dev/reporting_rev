package rw.oredigital.com.v1.dto.responses;

import javax.xml.crypto.Data;
import java.util.Date;

public record UserDTO(
        Long userId,
        String email,
        String username,
        String national_id,
        String role,
        Date lastLogin
        ) {
}
