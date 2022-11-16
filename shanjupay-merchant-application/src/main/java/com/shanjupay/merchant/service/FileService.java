package com.shanjupay.merchant.service;

import java.sql.BatchUpdateException;

public interface FileService {

    //文件上传
    public String upload(byte[] bytes, String fileName) throws BatchUpdateException;
}
