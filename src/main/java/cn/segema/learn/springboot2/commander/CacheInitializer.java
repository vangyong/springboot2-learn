package cn.segema.learn.springboot2.commander;

import org.springframework.boot.CommandLineRunner;

/**
 * @Description [初始化缓存数据] 
 * @author wangyong
 * @CreateDate 2020/04/28
 */
public class CacheInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("初始化缓存数据!");

    }

}
