package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/news_agency";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456789";

    public static void showMenu() {
        System.out.println("1. Show all news categories");
        System.out.println("2. Show all news");
        System.out.println("3. Show all news in a category");
        System.out.println("4. Add a new news category");
        System.out.println("5. Add a new news");// not yet
        System.out.println("6. Update a news category");
        System.out.println("7. Update a news");
        System.out.println("8. Delete a news category");
        System.out.println("9. Delete a news");
        System.out.println("10. Exit");
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        NewsAgencyDatabase newsDatabase = new NewsAgencyDatabase(DB_URL, DB_USER, DB_PASSWORD);
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (true) {
            showMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> newsDatabase.showAllCategories();
                case "2" -> newsDatabase.showAllNews();
                case "3" -> {
                    System.out.println("Enter news category name: ");
                    String categoryName = scanner.nextLine();
                    newsDatabase.showAllNewsInCategory(categoryName);
                }
                case "4" -> {
                    System.out.println("Enter news category ID: ");
                    int categoryId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter news category name: ");
                    String categoryName = scanner.nextLine();
                    newsDatabase.addCategory(categoryId,categoryName);
                }
                case "5" -> {
                    System.out.println("Enter news Category: ");
                    String categoryName = scanner.nextLine();
                    System.out.println("Enter news id: ");
                    int newsId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter news text: ");
                    String newsTitle = scanner.nextLine();
                    newsDatabase.addNews(categoryName, newsId, newsTitle);
                }
                case "6" -> {
                    System.out.println("Enter news category name: ");
                    String oldCategoryName = scanner.nextLine();
                    System.out.println("Enter new news category name: ");
                    String newCategoryName = scanner.nextLine();
                    newsDatabase.updateCategory(oldCategoryName, newCategoryName);
                }
                case "7" -> {
                    System.out.println("Enter news ID: ");
                    int newsId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter new news title: ");
                    String newNewsTitle = scanner.nextLine();
                    System.out.println("Enter new news category name: ");
                    String newCategoryName = scanner.nextLine();
                    newsDatabase.updateNews(newsId, newCategoryName, newNewsTitle);
                }
                case "8" -> {
                    System.out.println("Enter news category name: ");
                    String categoryName = scanner.nextLine();
                    newsDatabase.deleteCategory(categoryName);
                }
                case "9" -> {
                    System.out.println("Enter news ID: ");
                    int newsId = Integer.parseInt(scanner.nextLine());
                    newsDatabase.deleteNews(newsId);
                }
                case "10" -> {
                    newsDatabase.stop();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
