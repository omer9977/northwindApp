package demo.northwind.business.concretes;

import demo.northwind.business.abstracts.ProductService;
import demo.northwind.core.utilities.results.DataResult;
import demo.northwind.core.utilities.results.Result;
import demo.northwind.core.utilities.results.SuccessDataResult;
import demo.northwind.dataAccess.abstracts.ProductDao;
import demo.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public DataResult<List<Product>> getAll() {
//        try{
            return new SuccessDataResult<List<Product>>(this.productDao.findAll(), "Data is listed successfully.");
//        }catch(Exception e){
//            return new ErrorDataResult<List<Product>>();
//        }
    }
    
    @Override
    public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(pageable).getContent(),"Data is listed successfully.");
    }

    @Override
    public DataResult<List<Product>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC, "productName"); 
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(sort),"Data is listed successfully.");
    }

    @Override
    public Result add(Product product) {
        this.productDao.save(product);
        return new SuccessDataResult<Product>(product,"Product is added successfully.");
    }

    @Override
    public DataResult<Product> getByProductName(String productName) {
        return new SuccessDataResult<Product>(this.productDao.getByProductName(productName),"Data is listed successfully.");
    }

    @Override
    public DataResult<Product> getByProductNameAndCategory(String productName, int categoryId) {
        return new SuccessDataResult<Product>(this.productDao.getByProductNameAndCategory_CategoryId(productName, categoryId),"Data is listed successfully.");
    }

    @Override
    public DataResult<List<Product>> getByProductNameOrCategory(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameOrCategory_CategoryId(productName, categoryId),"Data is listed successfully.");
    }

    @Override
    public DataResult<List<Product>> getByCategoryIn(List<Integer> categories) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByCategoryIn(categories),"Data is listed successfully.");
    }

    @Override
    public DataResult<List<Product>> getByProductNameContains(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameContains(productName),"Data is listed successfully.");
    }

    @Override
    public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameStartsWith(productName),"Data is listed successfully.");
    }

    @Override
    public DataResult<List<Product>> getByNameOrCategory(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByNameOrCategory(productName, categoryId),"Data is listed successfully.");
    }

}
