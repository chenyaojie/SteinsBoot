package demo.wetter.service.impl;


import demo.wetter.domain.TeacherEntity;
import demo.wetter.domain.TeacherRepository;
import demo.wetter.service.TeacherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 2299
 * @version 1.0 2017/4/12
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

  private final TeacherRepository repository;

  @Autowired
  public TeacherServiceImpl(TeacherRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional(readOnly = true)
  public List<TeacherEntity> findAllTeacher() {
    return repository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable(value = "user", key = "#id")
  public TeacherEntity findTeacherById(Long id) {
    return repository.findOne(id);
  }
}
