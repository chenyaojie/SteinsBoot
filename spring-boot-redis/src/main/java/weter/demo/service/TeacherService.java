package weter.demo.service;


import weter.demo.domain.TeacherEntity;

/**
 * @author 2299
 * @version 1.0 2017/4/12
 */

public interface TeacherService {

  TeacherEntity findTeacherById(Long id);

  TeacherEntity save(TeacherEntity entity);

  void deleteById(Long id);

  void cleanAllCache();
}
