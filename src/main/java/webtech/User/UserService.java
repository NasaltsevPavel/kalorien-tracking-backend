package webtech.User;

import org.springframework.stereotype.Service;
import webtech.Day.Day;
import webtech.Day.DayEntity;
import webtech.Day.DayRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final DayRepository dayRepository;

    public UserService(UserRepository userRepository, DayRepository dayRepository) {
        this.userRepository = userRepository;
        this.dayRepository = dayRepository;
    }

    public List<User> findAll(){

        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(this::transformEntity).collect(Collectors.toList());

    }


    public User create(UserCreateOrUpdateRequest request){
        if (request.getGender() == null){

            request.setGender("UNKNOWN");

        }

            var gender = Gender.valueOf(request.getGender());

        var UserEntity = new UserEntity(request.getUsername(),request.getWeight(),
                request.getHeight(), request.getAge(), request.getBmi(), request.getCategory(), request.getGoalW(), request.getBmr(),
                gender);
        UserEntity = userRepository.save(UserEntity);

        return transformEntity(UserEntity);
    }

    public User findById(Long id){
        var userEntity = userRepository.findById(id);
        return userEntity.isPresent()? transformEntity(userEntity.get()) : null;
    }

    public User update(Long id, UserCreateOrUpdateRequest request){

        var userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isEmpty()){
            return null;
        }

        var userEntity = userEntityOptional.get();
        userEntity.setWeight(request.getWeight());
        userEntity.setHeight(request.getHeight());
        userEntity.setAge(request.getAge());
        userEntity.setGoalW(request.getGoalW());
        userEntity = userRepository.save(userEntity);

        return transformEntity(userEntity);


    }

    public User transformEntity(UserEntity userEntity){
        var gender = userEntity.getGender() != null ? userEntity.getGender().name(): Gender.UNKNOWN.name();
        var dayIds = userEntity.getDays().stream().map(DayEntity::getId).collect(Collectors.toList());
        return new User(userEntity.getId(),
                userEntity.getUsername(), userEntity.getWeight(), userEntity.getHeight(),
                userEntity.getAge(), userEntity.getGoalW(), gender, dayIds);
    }

    public boolean deleteById(Long id) {
        if(!userRepository.existsById(id)){
            return false;
        }

        userRepository.deleteById(id);
        return true;
    }

    public User addDay(Long id, String date){

        var userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isEmpty()){
            return null;
        }

        var dayEntityOptional = dayRepository.findByDate(date);
        if (dayEntityOptional.isEmpty()){
            return null;
        }

        var userEntity = userEntityOptional.get();
        var dayEntity = dayEntityOptional.get();
        userEntity.addDay(dayEntity);

        userEntity = userRepository.save(userEntity);

        return transformEntity(userEntity);


    }

    public User deleteDay(Long id, String date){

        var userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isEmpty()){
            return null;
        }

        var dayEntityOptional = dayRepository.findByDate(date);
        if (dayEntityOptional.isEmpty()){
            return null;
        }

        var userEntity = userEntityOptional.get();
        var dayEntity = dayEntityOptional.get();
        userEntity.deleteDay(dayEntity);

        userEntity = userRepository.save(userEntity);

        return transformEntity(userEntity);


    }

    public boolean createdCheck(Long id) {
        if(!userRepository.existsById(id)){
            return false;
        }

        return true;
    }
}
