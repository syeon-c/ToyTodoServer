package org.zerock.api01.common.util.service;

import io.minio.GetObjectResponse;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.api01.common.dto.FileDTO;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface MinioService {

    List<FileDTO> getListObjects();

    GetObjectResponse getFileObjectResponse(String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
    byte[] getFileByteArr(GetObjectResponse getObjectResponse) throws IOException;

    String uploadFile(MultipartFile multipartFile);

}
