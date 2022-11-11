package webtech.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webtech.Day.Day;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserRestController {


    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/v1/users")
    public ResponseEntity<List<User>> fetchUsers(){
        return ResponseEntity.ok(userService.findAll());

    }

    @PostMapping("/v1/users")
    public ResponseEntity<Void> createUser(@RequestBody UserCreateOrUpdateRequest request) throws URISyntaxException {

        var user = userService.create(request);
        URI uri = new URI("/user/v1/users/"+ user.getId());
        return ResponseEntity.created(uri).header("Access-Control-Expose-Headers", "Location").build();


    }

    @GetMapping("/v1/users/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable Long id){
        var user = userService.findById(id);
        return  user != null? ResponseEntity.ok(user): ResponseEntity.notFound().build();

    }

    @PutMapping("/v1/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserCreateOrUpdateRequest request ){
        var user = userService.update(id, request);
        return  user != null? ResponseEntity.ok(user): ResponseEntity.notFound().build();
    }

    @DeleteMapping("/v1/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        boolean successful = userService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/v1/users/{id}/{date}")
    public ResponseEntity<User> addDayToUser(@PathVariable Long id, @PathVariable String date ){
        var user = userService.addDay(id, date);
        return  user != null? ResponseEntity.ok(user): ResponseEntity.notFound().build();
    }

    @DeleteMapping("/v1/users/{id}/{date}")
    public ResponseEntity<User> deleteDayFromUser(@PathVariable Long id,@PathVariable String date ){
        var user = userService.deleteDay(id, date);
        return  user != null? ResponseEntity.ok(user): ResponseEntity.notFound().build();
    }

}




