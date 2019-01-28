# Screenshot-and-Snapshot-Maker
This program allows you to take screenshots from a computer screen and snapshots from a webcam and send them at regular time intervals to the Dropbox cloud storage and to email. The developed program can be used to control computer time and time management.The program uses Dropbox API, java.awt.Robot and Web-Capture API.

# Getting Started
These instructions will get you a copy of the project up and running on your local machine

Libraries

You need next libraries (them can found in folder /lib):

javax.mail-1.6.2.jar

javax.mail-api-1.6.2.jar

dropbox-core-sdk-3.0.11.jar

webcam-capture-0.3.12.jar

Usage

Clone the repository

git clone https://github.com/ncr00t/screenshot-maker-from-screen-and-webcam.git

Open the downloaded file in your favorite IDE and insert your data as in the screenshot below:

1. Go to class ScreenshotThread

2. In methode run() insert your data how showed on screenshot below:

![screenshot](https://user-images.githubusercontent.com/12431839/51830379-bed34600-2300-11e9-83d3-4d464a765d06.png)

3.Go to class LaunchScreenCapture and in method main use variable sendingDelayInSeconds for set interval time seinding:

![sending_delay](https://user-images.githubusercontent.com/12431839/51831297-d01d5200-2302-11e9-8422-72409e65c196.png)

4.Run programm from IDE

5.Ready! Go to the dropbox and your email and check the screenshots and webcam snapshots.

![result_app](https://user-images.githubusercontent.com/12431839/51831672-b4ff1200-2303-11e9-9bd5-44282d2f4344.png)
