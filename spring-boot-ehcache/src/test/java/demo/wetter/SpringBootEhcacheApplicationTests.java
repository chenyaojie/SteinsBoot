package demo.wetter;


import demo.wetter.domain.TeacherEntity;
import demo.wetter.domain.TeacherRepository;
import demo.wetter.service.TeacherService;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 添加该标签可以将事务回滚
public class SpringBootEhcacheApplicationTests {

  private Logger log = LoggerFactory.getLogger(SpringBootEhcacheApplicationTests.class);

  @Autowired
  private TeacherService teacherService;

  @Test
  public void contextLoads() {
  }

  @Test
  public void cacheTest() {
    log.info("第一次查询，从数据库中获取");
    TeacherEntity teacher = teacherService.findTeacherById(1L);
    log.info(teacher.toString());

    log.info("第二次查询，直接从缓存中获取");
    log.info(teacherService.findTeacherById(1L).toString());

    teacher.setDepart("物理系");
    teacher.setProf("讲师");
    teacherService.save(teacher);

    log.info("修改后会自动写入缓存");
    log.info(teacherService.findTeacherById(1L).toString());

    teacherService.deleteById(1L);
    TeacherEntity delete = teacherService.findTeacherById(1L);
    if (delete == null) {
      log.info("null");
    } else {
      log.info(delete.toString());
    }
  }

}
