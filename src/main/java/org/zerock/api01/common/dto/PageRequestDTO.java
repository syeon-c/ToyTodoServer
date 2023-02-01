package org.zerock.api01.common.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;


//    private String link;
//
//    public String getLink() {
//
//        if (link == null) {
//            StringBuilder builder = new StringBuilder();
//
//            builder.append("page=" + this.page);
//
//            builder.append("&size=" + this.size);
//
//
//            if (type != null && type.length() > 0) {
//                builder.append("&type=" + type);
//            }
//
//            if (keyword != null) {
//                try {
//                    builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
//                } catch (UnsupportedEncodingException e) {
//                }
//            }
//            link = builder.toString();
//        }
//
//        return link;
//    }

    public int getSkip() {

        return (page - 1) * size;
    }
}