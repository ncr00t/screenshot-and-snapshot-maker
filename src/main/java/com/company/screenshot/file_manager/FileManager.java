package com.company.screenshot.file_manager;

import com.company.screenshot.name_generator.ScreenshotNameGenerator;
import com.company.screenshot.screenshot_maker.ScreenshotMaker;
import com.company.screenshot.snapshot_webcam_maker.SnapshotWebcamMaker;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileManager {
    private FileSystemView fileSystemView;

    public FileManager() {
        fileSystemView = FileSystemView.getFileSystemView();
    }

    public File getHomeDirectory(){
        return fileSystemView.getHomeDirectory();
    }

    public File getPathToDirectoryWithScreenshot(File pathToDirectory, String screenshotNameWithExtension){
        return new File(pathToDirectory, screenshotNameWithExtension);
    }

    public String saveScreenshotFromScreenInDirectory(File pathToDirectory,ScreenshotMaker screenshotMaker, ScreenshotNameGenerator screenshotNameGenerator) throws AWTException, IOException {
        BufferedImage screenshot = screenshotMaker.makeScreenshotFromScreen();
        String pathToScreenshot = saveImageInDirectory(pathToDirectory, screenshot,
                                                           screenshotNameGenerator);
        return pathToScreenshot;
    }

    public String saveSnapshotFromWebcamInDirectory(File pathToDirectory, SnapshotWebcamMaker snapshotWebcamMaker, ScreenshotNameGenerator screenshotNameGenerator) throws IOException {
        BufferedImage snapshotFromWebcam = snapshotWebcamMaker.makeSnapshotFromWebCam();
        String pathToSnapshotFromWebcam = saveImageInDirectory(pathToDirectory, snapshotFromWebcam,
                                                                screenshotNameGenerator);
        return pathToSnapshotFromWebcam;
    }

    private String saveImageInDirectory(File pathToDirectory, BufferedImage image, ScreenshotNameGenerator screenshotNameGenerator) throws IOException {
        String screenshotNameWithExtension = screenshotNameGenerator.generateScreenshotNameWithDate();

        File pathDirectoryWithScreenshot = getPathToDirectoryWithScreenshot(pathToDirectory, screenshotNameWithExtension);

        ImageIO.write(image, screenshotNameGenerator.getExtension(),
                                 pathDirectoryWithScreenshot);
        return pathToDirectory + "\\" + screenshotNameWithExtension;
    }

    public void deleteFile(String pathToImage){
        new File(pathToImage).delete();
    }
}
