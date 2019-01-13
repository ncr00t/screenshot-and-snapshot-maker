package com.company.screenshot.name_generator;

import com.company.screenshot.date_format.DateViewFormat;

public class ScreenshotNameGenerator {
    private String screenshotName;
    private String extension;
    private String datePattern;

    public ScreenshotNameGenerator setScreenshotName(String screenshotName) {
        this.screenshotName = screenshotName;
        return this;
    }

    public ScreenshotNameGenerator setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public ScreenshotNameGenerator setDatePattern(String datePattern) {
        this.datePattern = datePattern;
        return this;
    }

    public String getExtension() {
        return extension;
    }

    public String generateScreenshotNameWithDate(){
        String dateFormat = DateViewFormat.getCurrentDateWithTimeByPattern(datePattern);
        return screenshotName + "_" + dateFormat + "." + extension;
    }
}
