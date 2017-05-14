package wetter.demo.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.stereotype.Component;

/**
 * @author 2299
 * @version 1.0 2017/5/11
 */
@Component
public class MyCacheResolver implements CacheResolver {

  private final static Logger log = LoggerFactory.getLogger(MyCacheResolver.class);
  private final CacheManager cacheManager;

  @Autowired
  public MyCacheResolver(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  /**
   * 返回用于指定调用的缓存
   *
   * @param context the context of the particular invocation
   * @return the cache(s) to use (never {@code null})
   */
  @Override
  public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
    log.info("\n\nOperation: " + context.getOperation() + "\nTarget: " + context.getTarget()
        + "\nMethod: " + context.getMethod().getName() + "\nArgs: " + Arrays
        .toString(context.getArgs()) + "\n\n");
    // 可自定义目标缓存名添加到结果集
    Collection<Cache> result = new ArrayList<>();
    Cache cache = this.cacheManager.getCache("teacher");
    result.add(cache);
    return result;

  }
}
