package com.commerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartService {
    public void addToCart(int userId, int productId, int quantity) {
        try (Connection conn = DbConnection.getConnection()) {
            String query = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CartItem> viewCart(int userId) {
        List<CartItem> cartItems = new ArrayList<>();
        try (Connection conn = DbConnection.getConnection()) {
            String query = "SELECT c.id, p.name, c.quantity, p.price " +
                    "FROM cart c JOIN products p ON c.product_id = p.id WHERE c.user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                cartItems.add(new CartItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItems;
    }
}
