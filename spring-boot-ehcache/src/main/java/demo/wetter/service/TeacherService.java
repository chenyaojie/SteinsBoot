package demo.wetter.service;

import demo.wetter.domain.TeacherEntity;
import java.util.List;


/**
 * @author 2299
 * @version 1.0 2017/4/12
 */

public interface TeacherService {

  TeacherEntity findTeacherById(Long id);

  TeacherEntity save(TeacherEntity entity);

  void deleteById(Long id);
}
