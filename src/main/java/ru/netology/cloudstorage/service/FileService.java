package ru.netology.cloudstorage.service;

import ru.netology.cloudstorage.dto.request.NewFileNameRequest;
import ru.netology.cloudstorage.model.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    List<FileEntity> getAllFiles();

    String uploadFile(MultipartFile file);

    String deleteFile(String filename);

    FileEntity getFile(String filename);

    String renameFile(String filename, NewFileNameRequest newFilename);
}