package webtech.Day;

import org.springframework.stereotype.Service;
import webtech.Product.Product;
import webtech.Product.ProductCreateOrUpdateRequest;
import webtech.Product.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DayService {

    private final DayRepository dayRepository;

    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }


    public List<Day> findAll(){

        List<DayEntity> days = dayRepository.findAll();
        return days.stream().map(this::transformEntity).collect(Collectors.toList());

    }

    private Day transformEntity(DayEntity dayEntity){


        if ( dayEntity.getProducts()==null){

        return new Day(dayEntity.getId(), dayEntity.getDate(), null, dayEntity.getTodayKcal());}

        else {
            var productNames = dayEntity.getProducts().stream().map(ProductEntity::getName).collect(Collectors.toList());
            return new Day(dayEntity.getId(), dayEntity.getDate(), productNames, dayEntity.getTodayKcal());
        }
    }


    public Day create(DayCreateOrUpdateRequest request){

        var DayEntity = new DayEntity(request.getDay(),request.getMonth(),request.getYear());

        DayEntity = dayRepository.save(DayEntity);

        return transformEntity(DayEntity);


    }

    public Day findById(Long id){
        var DayEntity = dayRepository.findById(id);
        return DayEntity.isPresent()? transformEntity(DayEntity.get()) : null;
    }

    //public Day update(Long id, DayCreateOrUpdateRequest request){

       // var dayEntityOptional = dayRepository.findById(id);
       // if (dayEntityOptional.isEmpty()){
      //      return null;
      //  }

      //  var dayEntity = dayEntityOptional.get();
       // dayEntity.setDate(request.getDate());
       // dayEntity.setTodayKcal(request.getTodayKcal());

      //  dayEntity = dayRepository.save(dayEntity);

      //  return transformEntity(dayEntity);


    //}


    public boolean deleteById(Long id) {
        if(!dayRepository.existsById(id)){
            return false;
        }

        dayRepository.deleteById(id);
        return true;
    }



}
