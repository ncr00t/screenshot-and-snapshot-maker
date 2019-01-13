package com.company.screenshot.screenshot_maker;

import com.company.screenshot.file_manager.FileManager;
import com.company.screenshot.name_generator.ScreenshotNameGenerator;
import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenshotMaker {
    private FileManager fileManager;
    private ScreenshotNameGenerator screenshotNameGenerator;

    public ScreenshotMaker() {

    }

    public String saveScreenshotInHomeDirectory(ScreenshotNameGenerator screenshotNameGenerator) throws AWTException, IOException {
        BufferedImage screenshot = makeScreenshotFromScreen();
        String screenshotNameWithExtension = screenshotNameGenerator.generateScreenshotNameWithDate();

        fileManager = new FileManager();
        File homeDirectory = fileManager.getHomeDirectory();
        File homeDirectoryWithScreenshot = fileManager.getHomeDirectoryWithScreenshot(screenshotNameWithExtension);

        ImageIO.write(screenshot, screenshotNameGenerator.getExtension(),
                                    homeDirectoryWithScreenshot);
        return homeDirectory + "/" + screenshotNameWithExtension;
    }

    public BufferedImage makeScreenshotFromScreen() throws AWTException {
        Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit()
                                                   .getScreenSize());
        BufferedImage imageFromDesktop = new Robot().createScreenCapture(screen);
        return imageFromDesktop;
    }

    public BufferedImage makeScreenshotFromWebCam()  {
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        BufferedImage imageFromWebCam = webcam.getImage();
        return imageFromWebCam;
    }
}
