package ru.netology.cloudstorage.service;

import ru.netology.cloudstorage.exceptions.*;
import ru.netology.cloudstorage.model.AuthToken;
import ru.netology.cloudstorage.dto.request.NewFileNameRequest;
import ru.netology.cloudstorage.model.entity.FileEntity;
import ru.netology.cloudstorage.repository.FileRepository;
import ru.netology.cloudstorage.security.TokenUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

import static ru.netology.cloudstorage.exceptions.MessageConstant.*;
import static java.util.Objects.isNull;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final AuthToken token;
    private final TokenUtils tokenUtils;

    @Override
    public List<FileEntity> getAllFiles() {
        List<FileEntity> fileList = fileRepository.findAll();
        // a test we could use
        /*
        if (fileList.isEmpty()) {
            throw new GettingFileListException(ERROR_GETTING_FILE_LIST);
        }
        */
        return fileList;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            FileEntity doublicateFilename = fileRepository.findByFilename(file.getOriginalFilename());
            if (isNull(doublicateFilename)) {
                fileRepository.save(new FileEntity(file.getOriginalFilename(),
                        file.getSize(), file.getContentType(), file.getBytes()));
                log.info("Success upload file: " + file.getOriginalFilename());
                return SUCCESS_UPLOAD;
            }
            log.error("Error upload file: " + file.getOriginalFilename() + ". Filename is not uniq!");
            throw new FileUploadException(ERROR_UPLOAD_NOT_UNIQ_FILE);
        } catch (IOException e) {
            log.error("Error upload file: " + file.getOriginalFilename() + ". Try again.");
            throw new FileUploadException(ERROR_UPLOAD_FILE);
        }
    }

    @Override
    public String deleteFile(String filename) {
        FileEntity file = fileRepository.findByFilename(filename);
        if (isNull(file)) {
            throw new DeleteFileException(ERROR_DELETE_FILENAME);
        }
        fileRepository.delete(file);
        return SUCCESS_DELETE;
    }

    @Override
    public FileEntity getFile(String filename) {
        return fileRepository.findByFilename(filename);
    }

    @Override
    public String renameFile(String filename, NewFileNameRequest nf) {
        FileEntity file = fileRepository.findByFilename(filename);
        if (isNull(file)) {
            log.error("Error rename file: " + filename + ". Try again.");
            throw new RenameFileException(ERROR_RENAME_FILENAME);
        }
        file.setFilename(nf.filename());
        fileRepository.save(file);
        log.info("Success rename file: " + filename);
        return SUCCESS_RENAME;
    }
}