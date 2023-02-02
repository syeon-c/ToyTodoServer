package org.zerock.api01.minio;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.api01.todo.controller.FileController;

import java.io.FileInputStream;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(FileController.class)
public class MinioUploadTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void testMinioFileSend() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "files",
                "testmock.jpeg",
                "image/jpeg",
                new FileInputStream("/Users/choeseoyeon/Desktop/학부모이미지.jpeg")
        );

        mockMvc.perform(
                multipart("/api/files/upload")
                        .file(mockMultipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .with(csrf())
        ).andExpect(status().is2xxSuccessful());
    }
}
