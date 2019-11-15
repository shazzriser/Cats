package com.example.nyt; // <============= CHANGE ME

import com.example.nyt.model.Article;
import com.example.nyt.model.Book;
import com.example.nyt.model.CatResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/***
 * A class to simulate some data source existing. Use to practice intents and lists/RecyclerView
 * before going into APIs.
 *
 * Week 6:
 *  Now this acts as a place to store our objects, and make them accessible by an ID.
 *  It no longer contains fake data in there by default. You must populate it by parsing the JSON.
 *
 *
 * Example usage:
 *      Article articleObject1 = FakeDatabase.getArticleById(1);
 *      System.out.println(articleObject1.getTitle());
 *
 * Output:
 *      Diamonds, Warlords and Mercenaries: Russia’s New Playbook in Africa
 */
public class FakeDatabase {
    public static HashMap<String, CatResponse> catResponses = new HashMap<>();

    public static HashMap<Long, Book> books = new HashMap<>();

    /***
     * Retrieves an Article object using the provided id.
     */
    public static CatResponse getArticleById(String articleID) {
        return catResponses.get(articleID);
    }

    /***
     * Return an ArrayList containing all the articles in the database.
     */
    public static ArrayList<CatResponse> getAllArticles() {
        return new ArrayList<CatResponse>((List) Arrays.asList(catResponses.values().toArray()));
    }

    // This methods simulates saving the data you get from the API to your local database.
    // This way, we retrieve the whole object for an Article by using its ID.
    // Keep in mind it's not a real database yet.
    public static void saveArticlesToFakeDatabase(ArrayList<CatResponse> articlesToSave) {
        for(int i = 0; i < articlesToSave.size(); i++) {
            CatResponse article = articlesToSave.get(i);
            catResponses.put(article.getId(), article);
        }
    }

    public static void addFavouriteCat(CatResponse cat){
        catResponses.put(cat.getId(), cat);
    }


    // Following are methods that do the same but for books
    public static Book getBookByIsbn(long isbn) {
        return books.get(isbn);
    }

    public static void saveBooksToFakeDatabase(ArrayList<Book> booksToSave) {
        for(int i = 0; i < booksToSave.size(); i++) {
            Book book = booksToSave.get(i);
            books.put(book.getIsbn(), book);
        }
        System.out.println(books);
    }


}
