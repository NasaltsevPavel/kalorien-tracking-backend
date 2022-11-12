package webtech.Product;

import org.springframework.stereotype.Service;
import webtech.Day.DaySeason;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){

        List<ProductEntity> users = productRepository.findAll();

        return users.stream().map(this::transformEntity).collect(Collectors.toList());

    }

    private Product transformEntity(ProductEntity productEntity){

        return new Product(productEntity.getId(), productEntity.getName(), productEntity.getKcal(), productEntity.getType().name());
    }

    public Product create(ProductCreateOrUpdateRequest request){

        if (request.getType() == null){

            request.setType("UNKNOWN");

        }

        var type = ProductType.valueOf(request.getType());

        var ProductEntity = new ProductEntity(request.getName(), request.getKcal(), type);

        ProductEntity = productRepository.save(ProductEntity);

        return transformEntity(ProductEntity);


    }

    public Product findById(Long id){
        var ProductEntity = productRepository.findById(id);
        return ProductEntity.isPresent()? transformEntity(ProductEntity.get()) : null;
    }


    public Product update(Long id, ProductCreateOrUpdateRequest request){

        var productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isEmpty()){
            return null;
        }

        var productEntity = productEntityOptional.get();
        productEntity.setName(request.getName());
        productEntity.setKcal(request.getKcal());

        productEntity = productRepository.save(productEntity);

        return transformEntity(productEntity);


    }

    public boolean deleteById(Long id) {
        if(!productRepository.existsById(id)){
            return false;
        }

        productRepository.deleteById(id);
        return true;
    }


}
