package usg.lostlink.server.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean ok;
    private T data;
    private String message;
    private HttpStatus status;
}
