package com.webapplication.content;

import com.webapplication.account.Account;
import com.webapplication.file.ContentFile;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "제목을 입력해주세요.")
    private String subject;

    @Column(nullable = false)
    @NotBlank(message = "내용을 입력해주세요.")
    private String text;

    @ManyToOne
    private Account writer;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
}