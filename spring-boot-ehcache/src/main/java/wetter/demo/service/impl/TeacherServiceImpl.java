package wetter.demo.service.impl;


import wetter.demo.domain.TeacherEntity;
import wetter.demo.domain.TeacherRepository;
import wetter.demo.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@CacheConfig(cacheNames = "teacher")
public class TeacherServiceImpl implements TeacherService {

  private static final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);
  private final TeacherRepository repository;

  @Autowired
  public TeacherServiceImpl(TeacherRepository repository) {
    this.repository = repository;
  }

  @Override
  @Cacheable(keyGenerator = "myKeyGenerator", sync = true)
  public TeacherEntity findTeacherById(Long id) {
    log.info("开始查询数据");
    TeacherEntity res;
    try {
      res = repository.findOne(id);
      log.info("查询数据成功");
      return res;
    } catch (Exception e) {
      e.printStackTrace();
      log.info("结束查询数据异常\n" + e);
      return null;
    }
  }

  @Override
  @CachePut(cacheResolver = "myCacheResolver")
  public TeacherEntity save(TeacherEntity entity) {
    log.info("修改数据");
    return repository.save(entity);
  }

  @Override
  @CacheEvict(beforeInvocation = true)
  public void deleteById(Long id) {
    log.info("删除数据");
    repository.deleteById(id);
  }

  @Override
  @CacheEvict(allEntries = true, beforeInvocation = true)
  public void cleanAllCache() {
    log.info("删除所有缓存数据");
  }
}
