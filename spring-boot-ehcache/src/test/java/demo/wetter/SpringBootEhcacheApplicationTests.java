package demo.wetter;


import demo.wetter.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootEhcacheApplicationTests {

  private Logger log = LoggerFactory.getLogger(SpringBootEhcacheApplicationTests.class);

  @Autowired
  private TeacherService teacherService;

  @Test
  public void contextLoads() {
  }

  @Test
  public void cacheTest() {
    log.info("First query, data should from database");
    log.info(teacherService.findTeacherById(1L).toString());

    log.info("Second query, data should from memory cache");
    log.info(teacherService.findTeacherById(1L).toString());
  }

}
