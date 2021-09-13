package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            FOLDER_BY_NAME_TPL,
            CLOSE_SAVED_ARTICLES_POPUP_BUTTON,
            SAVED_ARTICLE_LABEL,
            REMOVE_FROM_READING_LIST_OPTION;

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    /* TEMPLATES METHODS */

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        }
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void addArticleToExistingFolder(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        String existing_folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                existing_folder_name_xpath,
                "Cannot find existing folder",
                5
        );
    }

    public void addArticlesToMySaved() {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to the reading list", 5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article. Cannot find X link",
                5
        );
    }

    public void assertArticleHasTitle() {
        this.assertElementPresent(TITLE, "Cannot find article title on page");
    }

    public void closeSyncSavedArticlesPopUp() {
        this.waitForElementAndClick(
                CLOSE_SAVED_ARTICLES_POPUP_BUTTON,
                "Cannot find a button to close popup",
                5
        );
    }

    public void assertArticleIsAddedToSaved(String article_title) {
        if (Platform.getInstance().isAndroid()) {
            waitForElementAndClick(SAVED_ARTICLE_LABEL, "Cannot find saved article label", 5);
            waitForElementPresent(REMOVE_FROM_READING_LIST_OPTION, "The article doesn't seem to be saved. Cannot find 'Remove' option", 5);
        } else {
            this.waitForElementPresent(
                    SAVED_ARTICLE_LABEL,
                    "The article doesn't seem to be saved"
            );
        }
    }
}
