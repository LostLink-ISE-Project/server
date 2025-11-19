package usg.lostlink.server.service.implementation;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import usg.lostlink.server.entity.Media;
import usg.lostlink.server.repository.MediaRepository;

@Service
@RequiredArgsConstructor
public class MediaService {

  private final MediaRepository mediaRepository;

  public String saveImage(MultipartFile media) {
    Media image = new Media();
    try {
      image.setData(media.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    image.setMimeType(media.getContentType());

    return mediaRepository.save(image).getId();
  }

  public Media getMedia(String id) {
    return mediaRepository.findById(id).orElse(null);
  }
}
