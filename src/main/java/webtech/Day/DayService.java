package webtech.Day;

import org.springframework.stereotype.Service;
import webtech.Product.ProductEntity;
import webtech.Product.ProductRepository;
import webtech.User.User;
import webtech.User.UserRepository;
import webtech.User.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DayService {

    private final DayRepository dayRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final UserService userService;

    public DayService(DayRepository dayRepository, ProductRepository productRepository, UserRepository userRepository, UserService userService) {
        this.dayRepository = dayRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    public List<Day> findAll(){

        List<DayEntity> days = dayRepository.findAll();

        return days.stream().map(this::transformEntity).collect(Collectors.toList());

    }

    private Day transformEntity(DayEntity dayEntity){

        int kcalFromProd = 0;
        User user = userService.transformEntity(dayEntity.getUser());

        if ( dayEntity.getProducts()==null){
            return new Day(dayEntity.getId(), dayEntity.getDate(), null, dayEntity.getTodayKcal(), user, dayEntity.getSeason().name());
        }

        else {
            var productNames = dayEntity.getProducts().stream().map(ProductEntity::getName).collect(Collectors.toList());
            var productKcal = dayEntity.getProducts().stream().map(ProductEntity::getKcal).collect(Collectors.toList());
                for (Integer pr : productKcal) {
                    kcalFromProd = kcalFromProd + pr;
                }
                dayEntity.setTodayKcal(kcalFromProd);
            return new Day(dayEntity.getId(), dayEntity.getDate(), productNames, kcalFromProd, user,dayEntity.getSeason().name());
        }
    }


    public Day create(DayCreateOrUpdateRequest request){

        var user = userRepository.findById(request.getUserId()).orElseThrow();
        var season = DaySeason.valueOf(request.getSeason());
        var DayEntity = new DayEntity(request.getDay(),request.getMonth(),request.getYear(), user, season);

        DayEntity = dayRepository.save(DayEntity);

        return transformEntity(DayEntity);


    }

    public Day findById(Long id){
        var DayEntity = dayRepository.findById(id);
        return DayEntity.isPresent()? transformEntity(DayEntity.get()) : null;
    }

    public Day addProduct(Long id, String name){

        var dayEntityOptional = dayRepository.findById(id);
        if (dayEntityOptional.isEmpty()){
           return null;
        }

        var productEntityOptional = productRepository.findByName(name);
        if (productEntityOptional.isEmpty()){
            return null;
        }

        var dayEntity = dayEntityOptional.get();
        var productEntity = productEntityOptional.get();
         dayEntity.addProduct(productEntity);

        dayEntity = dayRepository.save(dayEntity);

        return transformEntity(dayEntity);


    }

    public Day deleteProduct(Long id, String name){

        var dayEntityOptional = dayRepository.findById(id);
        if (dayEntityOptional.isEmpty()){
            return null;
        }

        var productEntityOptional = productRepository.findByName(name);
        if (productEntityOptional.isEmpty()){
            return null;
        }

        var dayEntity = dayEntityOptional.get();
        var productEntity = productEntityOptional.get();
        dayEntity.deleteProduct(productEntity);

        dayEntity = dayRepository.save(dayEntity);

        return transformEntity(dayEntity);


    }



    public boolean deleteById(Long id) {
        if(!dayRepository.existsById(id)){
            return false;
        }

        dayRepository.deleteById(id);
        return true;
    }



}
