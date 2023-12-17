package org.example;

import java.sql.*;

public class NewsAgencyDatabase {
    Statement statement = null;
    Connection connection = null;

    public NewsAgencyDatabase(String url, String user, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
    }

    public int findCategoryId(String categoryName) throws SQLException {
        String idQuery = "SELECT ID_CO FROM news_agency.news_categories WHERE NAME = '" + categoryName + "'";
        ResultSet idSet = statement.executeQuery(idQuery);
        idSet.next();

        return idSet.getInt("ID_CO");
    }

    public void showAllCategories() {
        try {
            String query = "SELECT * FROM news_agency.news_categories";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("Category ID: " + resultSet.getInt("ID_CO") + ", Category Name: " + resultSet.getString("NAME"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAllNews() {
        try {
            String query = "SELECT * FROM NEWS_AGENCY.NEWS";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("News ID: " + resultSet.getInt("ID_NE") + ", title: " + resultSet.getString("NAME"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAllNewsInCategory(String categoryName) {
        try {
            int categoryId = findCategoryId(categoryName);
            String query = "SELECT * FROM news_agency.news WHERE ID_NE = " + categoryId;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("News ID: " + resultSet.getInt("ID_NE") + ", Title: " + resultSet.getString("NAME"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNews(String categoryName, int newsId, String text) {
        try {
            int categoryId = findCategoryId(categoryName);
            String query = "INSERT INTO news_agency.news (ID_CO, ID_NE, NAME) VALUES (" + categoryId + ", '" + newsId + "', '" + text + "')";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCategory(int categoryId,String title) {
        try {
            String query = "INSERT INTO news_agency.news_categories (ID_CO,NAME) VALUES (" + categoryId + ", '" + title + "')";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteNews(int newsId) {
        try {
            String query = "DELETE FROM news_agency.news WHERE ID_NE = " + newsId;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCategory(String categoryName) {
        try {
            int categoryId = findCategoryId(categoryName);
            String query = "DELETE FROM news_agency.news_categories WHERE ID_CO  = " + categoryId;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateNews(int newsId, String categoryName, String title) {
        try {
            int categoryId = findCategoryId(categoryName);
            String query = "UPDATE news_agency.news SET ID_CO = " + categoryId + ", NAME = '" + title + "' WHERE ID_NE = " + newsId;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCategory(String oldCategoryName, String newCategoryName) {
        try {
            int categoryId = findCategoryId(oldCategoryName);
            String query = "UPDATE news_agency.news_categories SET NAME = '" + newCategoryName + "' WHERE ID_CO = " + categoryId;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
