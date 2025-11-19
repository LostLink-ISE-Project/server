package usg.lostlink.server.response;

import java.util.Collections;
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
  private int status;

  public static <T> ApiResponse<T> success(T data, String message, HttpStatus status) {
    return new ApiResponse<>(true, data != null
        ? data : (T) Collections.emptyMap(), message, status.value());
  }

  public static ApiResponse<Object> failure(String message, HttpStatus status) {
    return new ApiResponse<>(false, Collections.emptyMap(), message, status.value());
  }

}
