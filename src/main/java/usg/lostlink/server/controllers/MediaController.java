package usg.lostlink.server.controllers;

import java.security.MessageDigest;
import java.util.HexFormat;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import usg.lostlink.server.entity.Media;
import usg.lostlink.server.response.ApiResponse;
import usg.lostlink.server.service.implementation.MediaService;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

  private final MediaService mediaService;

  private static String sha256Hex(byte[] data) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(data);
      return HexFormat.of().formatHex(md.digest());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //  @GetMapping("/{id}")
  //  public ResponseEntity<byte[]> getMedia(@PathVariable String id) {
  //    Media media = mediaService.getMedia(id);
  //
  //    HttpHeaders headers = new HttpHeaders();
  //    headers.setContentType(MediaType.parseMediaType(media.getMimeType())); // e.g., image/jpeg
  //    return new ResponseEntity<>(media.getData(), headers, HttpStatus.OK);
  //  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ApiResponse<String>> createImage(
      @RequestParam("media") MultipartFile media) {
    String id = mediaService.saveImage(media);
    return ResponseEntity.ok(ApiResponse.success(id, "Media was created", HttpStatus.CREATED));

  }

  @GetMapping("/{id}")
  public ResponseEntity<byte[]> getMedia(@PathVariable String id, WebRequest request) {
    Media media = mediaService.getMedia(id); // throw 404 if not found

    byte[] bytes = media.getData();
    String mime = media.getMimeType() != null ? media.getMimeType() :
        MediaType.APPLICATION_OCTET_STREAM_VALUE;

    // Strong ETag based on content hash
    String etag = "\"" + sha256Hex(bytes) + "\"";

    // If client has the same ETag, Spring returns 304 automatically
    if (request.checkNotModified(etag)) {
      return null; // 304 Not Modified
    }

    return ResponseEntity.ok()
        .header(HttpHeaders.CACHE_CONTROL, "private, max-age=60, immutable")
        .eTag(etag)
        .contentType(MediaType.parseMediaType(mime))
        .body(bytes);
  }

}