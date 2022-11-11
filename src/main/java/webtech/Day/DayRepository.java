package webtech.Day;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webtech.Product.ProductEntity;

import java.util.Optional;

@Repository
public interface DayRepository extends JpaRepository<DayEntity,Long> {

    Optional<DayEntity> findByDate(String date);
}
