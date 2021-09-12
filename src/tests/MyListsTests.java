package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
            MyListsPageObject.closeSyncSavedArticlesPopUp();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

//    @Test
//    public void testAddingTwoArticlesToReadingList()
//    {
//        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
//        SearchPageObject.initSearchInput();
//        SearchPageObject.typeSearchLine("Pixies");
//        SearchPageObject.clickByArticleWithSubstring("Pixies (band)");
//
//        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
//        String article_title_first = ArticlePageObject.getArticleTitle();
//        String name_of_folder = "Cool bands";
//        ArticlePageObject.addArticleToMyList(name_of_folder);
//        ArticlePageObject.closeArticle();
//
//        SearchPageObject.initSearchInput();
//        SearchPageObject.typeSearchLine("Nirvana");
//        SearchPageObject.clickByArticleWithSubstring("Nirvana (band)");
//
//        String article_title_second = ArticlePageObject.getArticleTitle();
//        ArticlePageObject.addArticleToExistingFolder(name_of_folder);
//        ArticlePageObject.closeArticle();
//
//        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
//        NavigationUI.clickMyLists();
//
//        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
//        MyListsPageObject.openFolderByName(name_of_folder);
//        MyListsPageObject.swipeByArticleToDelete(article_title_first);
//        MyListsPageObject.waitForArticleToAppearByTitle(article_title_second);
//        MyListsPageObject.openSavedArticleFromFolder(article_title_second);
//
//        String opened_article_title = ArticlePageObject.getArticleTitle();
//
//        assertEquals(
//                "We see unexpected title",
//                article_title_second,
//                opened_article_title
//        );
//    }
}