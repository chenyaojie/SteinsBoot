package demo.wetter.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 2299
 * @version 1.0 2017/4/12
 */

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

  TeacherEntity findOne(Long id);

  TeacherEntity save(TeacherEntity entity);

  void deleteById(Long id);

}
