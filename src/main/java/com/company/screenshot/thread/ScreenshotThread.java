package com.company.screenshot.thread;

import com.company.screenshot.cloud_loader.DropboxUploader;
import com.company.screenshot.name_generator.ScreenshotNameGenerator;
import com.company.screenshot.screenshot_maker.ScreenshotMaker;

import java.awt.image.BufferedImage;

public class ScreenshotThread extends Thread{
    private int sendingDelayInSeconds;
    private int sendingDelayInMilliseconds;

    public ScreenshotThread(int sendingDelayInSeconds) {
        this.sendingDelayInSeconds = sendingDelayInSeconds;
        final int AMOUNT_MILLISECONDS_IN_SECOND = 1000;
        sendingDelayInMilliseconds = sendingDelayInSeconds * AMOUNT_MILLISECONDS_IN_SECOND;
    }

    @Override
    public void run() {
        while(true){
            try {
                ScreenshotNameGenerator screenshotNameGenerator = new ScreenshotNameGenerator().setDatePattern("YYYYMMdd_HHmmss")
                                                                                               .setExtension("png");


                ScreenshotMaker screenshotMaker = new ScreenshotMaker();
                BufferedImage imageFromScreen = screenshotMaker.makeScreenshotFromScreen();
                BufferedImage imageFromWebcam = screenshotMaker.makeScreenshotFromWebCam();

                String accessToken = "insert your token for Dropbox";
                DropboxUploader dropboxUploader = new DropboxUploader(accessToken);

                screenshotNameGenerator.setScreenshotName("desktop");
                dropboxUploader.uploadScreenshot(imageFromScreen, screenshotNameGenerator);

                screenshotNameGenerator.setScreenshotName("webcam");
                dropboxUploader.uploadScreenshot(imageFromWebcam, screenshotNameGenerator);

                sleep(sendingDelayInMilliseconds);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
