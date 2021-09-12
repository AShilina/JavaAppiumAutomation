package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name='{TITLE}')]";
        CLOSE_SAVED_ARTICLES_POPUP_BUTTON = "xpath://XCUIElementTypeButton[@name='places auth close']";
    }

    public iOSMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}