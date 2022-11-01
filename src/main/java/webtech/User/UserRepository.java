package webtech.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    User findAllByUsername(String username);

    User findByUsername(String username);


}
