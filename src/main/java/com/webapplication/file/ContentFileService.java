package com.webapplication.file;

import com.webapplication.account.Account;
import com.webapplication.account.AccountRepository;
import com.webapplication.content.Content;
import com.webapplication.content.ContentRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class ContentFileService {

    private final ContentFileRepository contentFileRepository;
    private final ContentRepository contentRepository;
    private final AccountRepository accountRepository;

    public void saveFile(Long content_id, List<MultipartFile> files) throws IOException {
        ContentFile contentfile = new ContentFile();
        contentfile.setMultipartFile(files);
        Optional<Content> selectedContent = contentRepository.findById(content_id);

        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String basePath = rootPath + "\\" + "multi";

        for(MultipartFile file : contentfile.getMultipartFile()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = basePath + "\\" + fileName;

            ContentFile newContentFile = contentfile.builder()
                    .fileName(fileName)
                    .filePath(filePath)
                    .content(selectedContent.get())
                    .multipartFile(files)
                    .build();

            contentFileRepository.save(newContentFile);

            File Folder = new File(basePath);
            if(!Folder.exists()){
                try{
                    Folder.mkdir();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            file.transferTo(new File(filePath));
        }
    }

    public void generateTestData(Principal principal) {
        String name = principal.getName();
        Account selectedAccount = accountRepository.findByName(name);

        for (int i = 0; i <50; i++) {

            String randomSubjectValue = RandomString.make(10);
            String randomTextValue = RandomString.make(10);

            Content content = Content.builder()
                    .subject(randomSubjectValue)
                    .text(randomTextValue)
                    .date(new Date())
                    .modifiedDate(new Date())
                    .writer(selectedAccount)
                    .build();

            contentRepository.save(content);
        }
    }
}
