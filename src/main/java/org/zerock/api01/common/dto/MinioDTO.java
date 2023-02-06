package org.zerock.api01.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MinioDTO implements Serializable {

    private static final long serialVersionUID = 232836038145089522L;

    private String title;

    private String description;

    private MultipartFile file;

    private String url;

    private Long size;

    //UUID
    private String filename;

}