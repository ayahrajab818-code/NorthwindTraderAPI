package com.pluralsight.NorthwindTraderAPI.Data;

import com.pluralsight.NorthwindTraderAPI.Models.Product;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao{

    private DataSource dataSource;
    private static final Logger log = (Logger) LoggerFactory.getLogger(JdbcProductDao.class);

    @Autowired
    public JdbcProductDao(DataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    public List<Product> getAll() throws SQLException {

        List<Product> products = new ArrayList<>();

        String query = """
                SELECT
               ProductId,
               ProductName as name,
               CategoryId,
               UnitPrice as price
               FROM products;""";

        try(Connection connection = dataSource.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet results = statement.executeQuery()){
            while(results.next()){
                int productid = results.getInt(1);
                String productname = results.getString(2);
                int categoryId = results.getInt(3);
                double price = results.getDouble(4);

                Product p = new Product();
                p.setProductId(productid);
                p.setName(productname);
                p.setCategoryId(categoryId);
                p.setPrice(price);

                products.add(p);
            }


        }

        return products;


    }

    @Override
    public Product getById(int id) throws SQLException {

        String query = """
            SELECT
            ProductId,
            ProductName as name,
            CategoryId,
            UnitPrice as price
            FROM products WHERE ProductId = ?;""";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ){

            statement.setInt(1, id);

            try(ResultSet results = statement.executeQuery()){
                if(results.next()){
                    int productid = results.getInt(1);
                    String productname = results.getString(2);
                    int categoryId = results.getInt(3);
                    double price = results.getDouble(4);

                    Product p = new Product();
                    p.setProductId(productid);
                    p.setName(productname);
                    p.setCategoryId(categoryId);
                    p.setPrice(price);

                    return p;
                }
                else{
                    return null;
                }
            }
        }
    }

    @Override
    public Product insert(Product product) throws SQLException {
        String query = """
                INSERT INTO products (
                	ProductName,
                    CategoryID,
                    UnitPrice)
                VALUES
                (?, ?, ? );
                """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, product.getName());
            statement.setInt(2, product.getCategoryId());
            statement.setDouble(3, product.getPrice());

            int rows = statement.executeUpdate();

            try(ResultSet keys = statement.getGeneratedKeys()){
                if(keys.next()){
                    int productId =  keys.getInt(1);
                    product.setProductId(productId);
                    return product;
                }

            }
        }

        return null;

    }

    @Override
    public void update(int id, Product product) throws SQLException {
        String query = """
                UPDATE products
                SET
                ProductName = ?,
                CategoryId = ?,
                UnitPrice = ?
                WHERE ProductId = ?;""";


        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, product.getName());
            statement.setInt(2, product.getCategoryId());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, id);

            int rows = statement.executeUpdate();

        }

    }

    @Override
    public void delete(int id) throws SQLException {
        String query = """
                DELETE FROM products
                WHERE productId = ?;""";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

        }


    }
}