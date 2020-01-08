package com.thao.penzu.message.request;

import org.springframework.web.multipart.MultipartFile;

public class MultipartForm {
    private MultipartFile[] files;

    public MultiFileForm(MultipartFile[] files) {
        this.files = files;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
}
