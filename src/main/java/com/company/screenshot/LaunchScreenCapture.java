package com.company.screenshot;

import com.company.screenshot.thread.ScreenshotThread;

public class LaunchScreenCapture {
    public static void main(String[] args)  {
        int sendingDelayInSeconds = 5;

        ScreenshotThread screenshotThread = new ScreenshotThread(sendingDelayInSeconds);
        screenshotThread.start();
    }
}
