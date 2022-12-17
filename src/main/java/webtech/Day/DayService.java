package webtech.Day;

import org.springframework.stereotype.Service;
import webtech.Product.ProductEntity;
import webtech.Product.ProductRepository;
import webtech.User.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DayService {

    private final DayRepository dayRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public DayService(DayRepository dayRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.dayRepository = dayRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Day> findAll(){

        List<DayEntity> days = dayRepository.findAll();

        return days.stream().map(this::transformEntity).collect(Collectors.toList());

    }

    private Day transformEntity(DayEntity dayEntity){

        int kcalFromProd = 0;
        var userId = dayEntity.getUser().getId();
        if ( dayEntity.getProducts()==null){
            return new Day(dayEntity.getId(), dayEntity.getDate(), null, dayEntity.getTodayKcal(), userId, dayEntity.getSeason().name(), dayEntity.getDayBmr());
        }

        else {
            var productNames = dayEntity.getProducts().stream().map(ProductEntity::getName).collect(Collectors.toList());
            var productKcal = dayEntity.getProducts().stream().map(ProductEntity::getKcal).collect(Collectors.toList());
                for (Integer pr : productKcal) {
                    kcalFromProd = kcalFromProd + pr;
                }
                dayEntity.setTodayKcal(kcalFromProd);
            return new Day(dayEntity.getId(), dayEntity.getDate(), productNames, kcalFromProd, userId,dayEntity.getSeason().name(), dayEntity.getDayBmr());
        }
    }


    public Day create(DayCreateOrUpdateRequest request){

        var user = userRepository.findById(request.getUserId()).orElseThrow();
        DaySeason season = null;

        if(request.getMonth() == 1 || request.getMonth() == 2 || request.getMonth() == 12 ){
            season = DaySeason.valueOf("WINTER");
        }
        if(request.getMonth() == 3 || request.getMonth() == 4 || request.getMonth() == 5 ){
            season = DaySeason.valueOf("SPRING");
        }
        if(request.getMonth() == 6 || request.getMonth() == 7 || request.getMonth() == 8 ){
            season = DaySeason.valueOf("SUMMER");
        }
        if(request.getMonth() == 9 || request.getMonth() == 10 || request.getMonth() == 11 ){
            season = DaySeason.valueOf("AUTUMN");
        }
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
