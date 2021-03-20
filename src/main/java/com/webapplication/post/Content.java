package com.webapplication.post;

import com.webapplication.account.Account;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "제목을 입력해주세요.")
    private String subject;

    @NotBlank(message = "내용을 입력해주세요.")
    private String text;

    private String file;

    @ManyToOne
    private Account writer;

    private String content_date;

    private String content_modifiedDate;

    private String content_writer_name;
}