package ru.netology.cloudstorage.service;

import org.springframework.http.MediaType;
import ru.netology.cloudstorage.model.entity.FileEntity;
import org.springframework.mock.web.MockMultipartFile;

public class PrepareInfoForTest {

    public final static String TOKEN = "afe1716dc4be4ecabe2926decea2cffe";
    public final static String CORRECT_LOGIN = "check@email.ru";
    public final static String VALID_PASSWORD = "check";
    public final static String NON_VALID_PASSWORD = "nonValidPassword";
    public final static String FILENAME = "test.txt";

    public static MockMultipartFile getFileToUploadTest() {
        return new MockMultipartFile("file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello!".getBytes());
    }

    public static FileEntity getTestUserEntity() {
        FileEntity file = new FileEntity(FILENAME, 2, "text/plain", null);
        file.setId(1L);
        return file;
    }
}
