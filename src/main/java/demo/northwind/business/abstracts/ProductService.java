package demo.northwind.business.abstracts;

import demo.northwind.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
}
