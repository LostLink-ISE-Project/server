package usg.lostlink.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import usg.lostlink.server.entity.Media;
import usg.lostlink.server.response.ApiResponse;
import usg.lostlink.server.service.implementation.MediaService;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

  private final MediaService mediaService;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ApiResponse<String>> createImage(@RequestParam("media") MultipartFile media) {
    String id = mediaService.saveImage(media);
    return ResponseEntity.ok(ApiResponse.success(id, "Media was created", HttpStatus.CREATED));

  }

  @GetMapping("/{id}")
  public ResponseEntity<byte[]> getMedia(@PathVariable String id) {
    Media media = mediaService.getMedia(id);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(media.getMimeType())); // e.g., image/jpeg
    return new ResponseEntity<>(media.getData(), headers, HttpStatus.OK);
  }
}
