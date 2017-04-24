package demo.wetter.service;

import demo.wetter.domain.TeacherEntity;
import java.util.List;


/**
 * @author 2299
 * @version 1.0 2017/4/12
 */

public interface TeacherService {

  List<TeacherEntity> findAllTeacher();

  TeacherEntity findTeacherById(Long id);
}
