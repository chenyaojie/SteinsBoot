package weter.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 2299
 * @version 1.0 2017/4/12
 */
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

  TeacherEntity findOne(Long id);

  <S extends TeacherEntity> S save(S entity);

  void deleteById(Long id);

}
