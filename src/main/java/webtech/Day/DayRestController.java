package webtech.Day;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
