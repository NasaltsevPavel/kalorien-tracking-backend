package webtech.User;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){

        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(this::transformEntity).collect(Collectors.toList());

    }


    public User create(UserCreateOrUpdateRequest request){
        var gender = Gender.valueOf(request.getGender());


        var UserEntity = new UserEntity(request.getUsername(), request.getPasswort(), request.getWeight(),
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
        userEntity.setUsername(request.getUsername());
        userEntity.setPasswort(request.getPasswort());
        userEntity.setWeight(request.getWeight());
        userEntity.setHeight(request.getHeight());
        userEntity.setAge(request.getAge());
        userEntity.setBmi(request.getBmi());
        userEntity.setCategory(request.getCategory());
        userEntity.setGoalW(request.getGoalW());
        userEntity.setBmr(request.getBmr());
        userEntity.setGender(Gender.valueOf(request.getGender()));
        userEntity = userRepository.save(userEntity);

        return transformEntity(userEntity);


    }

    private User transformEntity(UserEntity userEntity){
        var gender = userEntity.getGender() != null ? userEntity.getGender().name(): Gender.UNKNOWN.name();
        return new User(userEntity.getId(),
                userEntity.getUsername(), userEntity.getPasswort(), userEntity.getWeight(), userEntity.getHeight(),
                userEntity.getAge(), userEntity.getGoalW(), gender);
    }

    public boolean deleteById(Long id) {
        if(!userRepository.existsById(id)){
            return false;
        }

        userRepository.deleteById(id);
        return true;
    }


}
