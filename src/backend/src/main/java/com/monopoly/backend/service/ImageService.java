package com.monopoly.backend.service;

import com.monopoly.backend.domain.entity.Image;
import com.monopoly.backend.dto.request.ImageSaveReq;
import com.monopoly.backend.dto.response.ImageRes;
import com.monopoly.backend.domain.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class ImageService {

    private final ImageRepository imageRepository;

    public Image save(String type, Long id, MultipartFile file) {
        Image newImage = new Image();

        try {
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            newImage.setBoardId(id);
            newImage.setBoardType(type);

            System.out.println(byteObjects);

            newImage.setImg(byteObjects);
        } catch (IOException e) {
            log.error("Error occurred", e);

            e.printStackTrace();
        }

        return imageRepository.save(newImage);
    }

    /**
     * 타입, ID로 찾기
     * @param boardType
     * @param boardId
     * @return List
     */
    public List<ImageRes> getListByBoardTypeAndBoardId(String boardType, Long boardId) {
        return imageRepository.findAllByBoardIdAndBoardType(boardType, boardId)
                .map(ImageRes::new)
                .collect(Collectors.toList());
    }

}
