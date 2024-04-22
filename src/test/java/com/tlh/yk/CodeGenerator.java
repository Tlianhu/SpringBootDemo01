package com.tlh.yk;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class CodeGenerator {

    @Test
    public void run(){

        //1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //2、全局配置
        GlobalConfig gc = new GlobalConfig();
        //路径
        gc.setOutputDir("/Users/tianhu/code/Java_code/Spring_Boot/spring_boot_demo02_yk/src/main/java");
        gc.setAuthor("田连虎Java");
        gc.setOpen(false);  //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService"); //去掉Service
        gc.setIdType(IdType.ID_WORKER_STR); //主键策略
        gc.setDateType(DateType.ONLY_DATE); //定义生成的实体类中日前类型
        gc.setSwagger2(true); //开启Swagger2模式

        mpg.setGlobalConfig(gc);

        //3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/yk?serviceTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("12345678");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        //4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.tlh");
        pc.setModuleName("yk"); //模块名
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        //5、策略配置
        StrategyConfig stc = new StrategyConfig();
        stc.setInclude("edu_teacher");
        stc.setNaming(NamingStrategy.underline_to_camel); //数据库表映射到实体的命名策略
        stc.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表的前缀
        stc.setColumnNaming(NamingStrategy.underline_to_camel); //数据库表字段映射到实体命名策略
        stc.setEntityLombokModel(true); //Lombok模型
        stc.setRestControllerStyle(true); //restful api风格控制器
        stc.setControllerMappingHyphenStyle(true); //URL中驼峰转连字符
        mpg.setStrategy(stc);

        //6、执行
        mpg.execute();

    }
}
