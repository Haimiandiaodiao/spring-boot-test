package _009_MybatisTest;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Dyy
 * @Description
 * @Date 2018/5/17 16:25
 */
public class _001_RevertMybasitProject {

    /**
     * 1.生成所需的Mybatis文件
     */
    @Test
    public void baseUse1() throws Exception {
        //加载配置文件并进行相应的写操作
        List<String> warnings = new ArrayList<String>();
         boolean overwrite = true;
        File configFile = new File("src/test/resource/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
