package webtech.Day;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webtech.Product.Product;
import webtech.Product.ProductCreateOrUpdateRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class DayRestController {

    private final DayService dayService;

    public DayRestController(DayService dayService) {
        this.dayService = dayService;
    }

    @GetMapping("/v1/days")
    public ResponseEntity<List<Day>> fetchDays(){
        return ResponseEntity.ok(dayService.findAll());

    }

    @PostMapping("/v1/days")
    public ResponseEntity<Void> createDay(@RequestBody DayCreateOrUpdateRequest request) throws URISyntaxException {

        var day = dayService.create(request);
        URI uri = new URI("/v1/days/"+ day.getId());
        return ResponseEntity.created(uri).build();


    }

    @PutMapping("/v1/days/{id}/{name}")
    public ResponseEntity<Day> addProductToDay(@PathVariable Long id,@PathVariable String name ){
        var day = dayService.addProduct(id, name);
        return  day != null? ResponseEntity.ok(day): ResponseEntity.notFound().build();
    }

    @PutMapping("/v1/days/delete/{id}/{name}")
    public ResponseEntity<Day> deleteProductToDay(@PathVariable Long id,@PathVariable String name ){
        var day = dayService.deleteProduct(id, name);
        return  day != null? ResponseEntity.ok(day): ResponseEntity.notFound().build();
    }
}
