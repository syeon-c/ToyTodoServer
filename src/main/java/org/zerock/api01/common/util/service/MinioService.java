package org.zerock.api01.common.util.service;

import io.minio.GetObjectResponse;
import io.minio.ObjectWriteResponse;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.api01.common.dto.FileDTO;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface MinioService {

    List<FileDTO> getListObjects();

    byte[] getFileObject(String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    String uploadFile(MultipartFile multipartFile);

}
