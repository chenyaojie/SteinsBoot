package demo.wetter.web;


import demo.wetter.domain.TeacherEntity;
import demo.wetter.service.TeacherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 2299
 * @version 1.0 2017/4/12
 */
@RestController
public class TeacherController {

  private final TeacherService service;

  @Autowired
  public TeacherController(TeacherService service) {
    this.service = service;
  }

  @GetMapping("findAllTeacher")
  public List<TeacherEntity> findAllTeacher() {
    return service.findAllTeacher();
  }
}
