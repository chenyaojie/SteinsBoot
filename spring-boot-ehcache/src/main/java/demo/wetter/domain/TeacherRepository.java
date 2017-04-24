package demo.wetter.domain;

import java.util.List;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 2299
 * @version 1.0 2017/4/12
 */
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

  List<TeacherEntity> findAll();


  TeacherEntity findOne(Long id);

}
