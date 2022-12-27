package webtech.Product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webtech.User.User;
import webtech.User.UserCreateOrUpdateRequest;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/v1/products")
    public ResponseEntity<List<Product>> fetchProducts(){
        return ResponseEntity.ok(productService.findAll());

    }


    @PostMapping("/v1/products")
    public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductCreateOrUpdateRequest request) throws URISyntaxException {
        var product = productService.create(request);
        URI uri = new URI("/v1/products/"+ product.getId());
        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/v1/products/{id}")
    public ResponseEntity<Product> fetchProductById(@PathVariable Long id){
        var product = productService.findById(id);
        return  product != null? ResponseEntity.ok(product): ResponseEntity.notFound().build();

    }


    @PutMapping("/v1/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductCreateOrUpdateRequest request ){
        var product = productService.update(id, request);
        return  product != null? ResponseEntity.ok(product): ResponseEntity.notFound().build();
    }

    @DeleteMapping("/v1/products/{name}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String name){
        boolean successful = productService.deleteByName(name);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
