package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        CLOSE_SAVED_ARTICLES_POPUP_BUTTON = "xpath://XCUIElementTypeButton[@name='places auth close']";
        SAVED_ARTICLE_LABEL = "id:Saved. Activate to unsave.";
    }

    public iOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
