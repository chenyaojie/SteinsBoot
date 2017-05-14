package wetter.demo;


import org.junit.Assert;
import org.junit.Before;
import wetter.demo.domain.TeacherEntity;
import wetter.demo.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 在测试类中添加该标签可以将事务回滚
public class SpringBootEhcacheApplicationTests {

  private Logger log = LoggerFactory.getLogger(SpringBootEhcacheApplicationTests.class);

  @Autowired
  private TeacherService teacherService;

  @Autowired
  private CacheManager cacheManager;

  @Test
  public void contextLoads() {
  }

  @Test
  public void cacheTest() {
    log.info("\n\n" + "=========================================================\n"
        + "Using cache manager: " + this.cacheManager.getClass().getName() + "\n"
        + "=========================================================\n\n");
    teacherService.cleanAllCache();

    log.info("\n\n" + "=========================================================\n"
        + "第一次查询，从数据库中获取\n"
        + "=========================================================\n\n");
    TeacherEntity teacher = teacherService.findTeacherById(1L);
    log.info(teacher.toString());

    log.info("\n\n" + "=========================================================\n"
        + "第二次查询，直接从缓存中获取\n"
        + "=========================================================\n\n");
    log.info(teacherService.findTeacherById(1L).toString());

    log.info("\n\n" + "=========================================================\n"
        + "修改数据，同步缓存\n"
        + "=========================================================\n\n");
    teacher.setDepart("物理系");
    teacher.setProf("讲师");
    teacherService.save(teacher);

    log.info("\n\n" + "=========================================================\n"
        + "第三次查询，从缓存中获取修改后的数据\n"
        + "=========================================================\n\n");
    TeacherEntity afterModify = teacherService.findTeacherById(1L);
    Assert.assertEquals(teacher, afterModify);
    log.info(afterModify.toString());

    log.info("\n\n" + "=========================================================\n"
        + "删除数据，同步缓存\n"
        + "=========================================================\n\n");
    teacherService.deleteById(1L);

    log.info("\n\n" + "=========================================================\n"
        + "第四次查询，判断数据已经从缓存和数据库中移除\n"
        + "=========================================================\n\n");
    TeacherEntity delete = teacherService.findTeacherById(1L);
    Assert.assertNull(delete);
    log.info("数据已移除");

    log.info("\n\n" + "=========================================================\n"
        + "回滚事务\n"
        + "=========================================================\n\n");
  }

  @Test
  public void syncCacheTest() {
    log.info("\n\n" + "=========================================================\n"
        + "测试缓存的并发\n"
        + "=========================================================\n\n");
    int i = 5;
    TestCacheThread runnableCache = new TestCacheThread();
    while (i-- > 0) {
      log.info("开启线程: " + i + "\n");
      new Thread(runnableCache).start();
    }
    try {
      // 防止自动回滚
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  class TestCacheThread implements Runnable {

    @Override
    public void run() {
      log.info("\n\n执行查询\n\n");
      log.info(teacherService.findTeacherById(1L).toString());
    }
  }


}
