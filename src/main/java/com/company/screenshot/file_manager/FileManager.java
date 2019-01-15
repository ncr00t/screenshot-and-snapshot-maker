package com.company.screenshot.file_manager;

import com.company.screenshot.name_generator.ScreenshotNameGenerator;
import com.company.screenshot.screenshot_maker.ScreenshotMaker;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private FileSystemView fileSystemView;

    public FileManager() {
        fileSystemView = FileSystemView.getFileSystemView();
    }

    public  File getHomeDirectory(){
        return fileSystemView.getHomeDirectory();
    }

    public File getHomeDirectoryWithScreenshot(String screenshotNameWithExtension){
        return new File(getHomeDirectory(), screenshotNameWithExtension);
    }

    public String saveScreenshotInHomeDirectory(ScreenshotMaker screenshotMaker, ScreenshotNameGenerator screenshotNameGenerator) throws AWTException, IOException {
        BufferedImage screenshot = screenshotMaker.makeScreenshotFromScreen();
        String screenshotNameWithExtension = screenshotNameGenerator.generateScreenshotNameWithDate();

        File homeDirectory = getHomeDirectory();
        File homeDirectoryWithScreenshot = getHomeDirectoryWithScreenshot(screenshotNameWithExtension);

        ImageIO.write(screenshot, screenshotNameGenerator.getExtension(),
                homeDirectoryWithScreenshot);
        return homeDirectory + "/" + screenshotNameWithExtension;
    }

    public boolean isExistFileWithThisName(String scrrenshotNameWithExtension){
        File homeDirectory = getHomeDirectory();
        Path pathToScreenshot = Paths.get(homeDirectory + "/" + scrrenshotNameWithExtension);
        return Files.exists(pathToScreenshot);
    }
}
