package com.company.screenshot.screenshot_maker;

import com.github.sarxos.webcam.Webcam;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenshotMaker {

    public ScreenshotMaker() {

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
