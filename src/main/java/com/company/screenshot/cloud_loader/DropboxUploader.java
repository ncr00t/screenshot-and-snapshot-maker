package com.company.screenshot.cloud_loader;

import com.company.screenshot.name_generator.ScreenshotNameGenerator;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class DropboxUploader {
    private String accessToken;

    public DropboxUploader(String accessToken) {
        this.accessToken = accessToken;
    }

    public void uploadScreenshot(BufferedImage image, ScreenshotNameGenerator screenshotNameGenerator) {
        try{
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(image,screenshotNameGenerator.getExtension(), output);
            InputStream input = new ByteArrayInputStream(output.toByteArray());

            DbxClientV2 client = createDbxClient();
            FileMetadata metadata = client.files()
                                          .uploadBuilder("/" + screenshotNameGenerator.generateScreenshotNameWithDate())
                                          .uploadAndFinish(input);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private DbxClientV2 createDbxClient(){
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial")
                                                  .build();
        DbxClientV2 client = new DbxClientV2(config, accessToken);
        return client;
    }
}
