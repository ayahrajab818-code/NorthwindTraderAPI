package com.pluralsight.NorthwindTraderAPI.Controller;

import com.pluralsight.NorthwindTraderAPI.Data.ProductDao;
import com. pluralsight.NorthwindTraderAPI.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// This tells Spring that this class is a REST controller.
// It means this class will handle web requests and return data (like JSON).
@RestController
public class ProductController {

    // This will hold the ProductDao object so the controller can talk to the database.
    private ProductDao productDao;

    // @Autowired tells Spring to automatically give us a ProductDao object when creating this controller.
    @Autowired
    public ProductController(ProductDao productDao){

        // Store the ProductDao that Spring gives us.
        this.productDao = productDao;
    }

    // This method will run when someone sends a GET request to "/products".
    // It returns a list of all products.
    @RequestMapping(path= "/products", method = RequestMethod.GET)
    public List<Product> getProducts()throws SQLException {

        // Ask the ProductDao to get all products from the database.
        return productDao.getAll();
    }

    // This method will run when someone sends a GET request to "/products/{id}".
    // The {id} part means the user passes in a product ID in the URL which is {id}.
    @RequestMapping(path="/products/{id}", method=RequestMethod.GET)
    public Product getProduct(@PathVariable int id)throws SQLException {
        // Use the ID from the URL to get that specific product from the database.
    return productDao.getById(id);
    }

    // This method will run when someone sends a POST request to "/products".
    // POST means "create a new product".
    @RequestMapping(path="/products", method = RequestMethod.POST)
    // This tells Spring to respond with status 201 CREATED when the product is successfully added.
    //HTTP 201 means “The request has succeeded and resulted in a new resource being created.”
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) throws SQLException{
        // Insert the new product into the database and return it.
        return productDao.insert(product);
    }

    // This method will run when someone sends a PUT request to "/products/{id}".
    // PUT means "update an existing product".
    @RequestMapping(path="/products/{id}", method = RequestMethod.PUT)
    public void updateProduct(@PathVariable int id, @RequestBody Product product)throws SQLException{
        // Update the product with the given ID using the new product data from the request body.
        productDao.update(id,product);
    }
    // This method will run when someone sends a DELETE request to "/products/{id}"
    @RequestMapping(path = "/products/{id}", method = RequestMethod.DELETE)
    // This tells Spring to return 204 NO CONTENT, meaning the delete was successful but there is no data to send back.
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id)throws SQLException{
        // Delete the product with the given ID from the database.
        productDao.delete(id);
    }



}
