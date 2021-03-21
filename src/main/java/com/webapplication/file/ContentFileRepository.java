package com.webapplication.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentFileRepository extends JpaRepository<ContentFile, Long> {
    List<ContentFile> findByContent_id (Long content_id);
}
