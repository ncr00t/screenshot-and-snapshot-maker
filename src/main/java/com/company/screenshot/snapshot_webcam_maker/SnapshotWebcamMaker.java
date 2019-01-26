package com.company.screenshot.snapshot_webcam_maker;

import com.company.screenshot.screenshot_maker.ScreenshotMaker;
import com.github.sarxos.webcam.Webcam;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SnapshotWebcamMaker {

    public Dimension getSnapshotResolution(){
        return ScreenshotMaker.getScreenResolution();
    }

    public BufferedImage makeSnapshotFromWebCam()  {
        Webcam webcam = Webcam.getDefault();
        Dimension snapshotResolution = getSnapshotResolution();
        webcam.setCustomViewSizes(snapshotResolution);
        webcam.setViewSize(snapshotResolution);

        webcam.open();
        BufferedImage snapshotFromWebCam = webcam.getImage();
        webcam.close();
        return snapshotFromWebCam;
    }
}
