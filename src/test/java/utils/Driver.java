package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
public class Driver {
    private static AndroidDriver appiumDriver;
    private static IOSDriver iosDriver;
    static final String TELEFONADI="Pixel 2";
    static final String ANDROIDVERSION="7.0";
    static final String PLATFORM="Android";
    static final String OTOMASYON_ISMI="UiAutomator2";

    public static AndroidDriver getAndroidDriver()  {
        URL appiumServerURL = null;
        try {
            appiumServerURL = new URL("http://127.0.0.1:4723/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (appiumDriver == null) {

            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("Pixel 8 Pro")
                    .setPlatformName("Android")
                    .setPlatformVersion("13.0")
                    .setAutomationName("UiAutomator2")
                   // .setApp("C:\\Users\\Lenovo\\IdeaProjects\\Appium_T135_YeniVersion\\Apps\\Teknosa – Alışveriş, Teknoloji_7.2.7_APKPure.apk")
                   .setAppPackage("com.tmob.teknosa")
                   .setAppActivity("com.tmob.teknosa.MainActivity")
                    .setNoReset(false)
                    .setAutoGrantPermissions(true);

            if (ConfigReader.getProperty("platformName").equals("Android")) {
                assert appiumServerURL != null;
                appiumDriver = new AndroidDriver(appiumServerURL,options);
                appiumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            }else {
                assert appiumServerURL != null;
                iosDriver = new IOSDriver(appiumServerURL,options);
                iosDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                throw new UnsupportedOperationException("Dostum Ios kullanmaya calisiyorsun YAPMA!!");
            }
        }
        return appiumDriver;
    }
    public static void quitAppiumDriver(){
        if (appiumDriver != null) {
            appiumDriver.closeApp();
            appiumDriver = null;
        }
    }
}