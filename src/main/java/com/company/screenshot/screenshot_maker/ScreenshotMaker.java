package com.company.screenshot.screenshot_maker;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenshotMaker {

    public static Dimension getScreenResolution(){
        return Toolkit.getDefaultToolkit()
                      .getScreenSize();
    }

    public BufferedImage makeScreenshotFromScreen() throws AWTException {
        Dimension screenResolution = getScreenResolution();
        Rectangle screen = new Rectangle(screenResolution);
        BufferedImage imageFromDesktop = new Robot().createScreenCapture(screen);
        return imageFromDesktop;
    }
}
