package wetter.demo.cache;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.stereotype.Component;

/**
 * @author 2299
 * @version 1.0 2017/5/10
 */
@Component
public class MyKeyGenerator implements KeyGenerator {

  private static final Logger log = LoggerFactory.getLogger(MyKeyGenerator.class);

  /**
   * Generate a key for the given method and its parameters.
   *
   * @param target the target instance
   * @param method the method being called
   * @param params the method parameters (with any var-args expanded)
   * @return a generated key
   */
  @Override
  public Object generate(Object target, Method method, Object... params) {
    log.info("\nTarget: " + target + "\nMethod: " + method.getName() + "\nParams: " + Arrays
        .toString(params));
    return new SimpleKeyGenerator().generate(target, method, params);
  }
}
