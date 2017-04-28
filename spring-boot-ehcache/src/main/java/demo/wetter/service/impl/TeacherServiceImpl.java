package demo.wetter.service.impl;


import demo.wetter.domain.TeacherEntity;
import demo.wetter.domain.TeacherRepository;
import demo.wetter.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 2299
 * @version 1.0 2017/4/12
 * @Cache* 这类标签具体在 Dao 层还是在 Service 层使用，取决于具体的逻辑：
 * @link http://stackoverflow.com/questions/15420478/should-i-do-caching-on-dao-layer-or-service-layer-in-spring-mvc-web-app?answertab=votes#tab-top
 * @link https://www.quora.com/In-which-Spring-Framework-layer-should-I-manage-cache-Repository-or-Service
 * @link http://stackoverflow.com/questions/7058843/when-and-how-to-use-hibernate-second-level-cache
 */
@Service
@Transactional
@CacheConfig(cacheNames = "teacher") // Spring @Cache* 这类标签不要在 interface 中使用

public class TeacherServiceImpl implements TeacherService {

  private static final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);
  private final TeacherRepository repository;

  @Autowired
  public TeacherServiceImpl(TeacherRepository repository) {
    this.repository = repository;
  }

  @Override
  @Cacheable
  public TeacherEntity findTeacherById(Long id) {
    log.info("查询数据库");
    TeacherEntity res;
    try {
      res = repository.findOne(id);
      return res;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  @CachePut
  public TeacherEntity save(TeacherEntity entity) {
    log.info("修改数据库");
    return repository.save(entity);
  }

  @Override
  @CacheEvict(beforeInvocation = true)
  public void deleteById(Long id) {
    log.info("删除数据库");
    repository.deleteById(id);
  }
}
