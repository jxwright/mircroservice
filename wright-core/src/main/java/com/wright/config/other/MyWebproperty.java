//package com.wright.config.other;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
///**
// * 加载自定义配置文件
// * 若@Configuration换成@Component，可以直接其他地方使用@Autowired来创建其实例对象 
// *     @Autowired 
// *     MyWebConfig myWebConfig;  
// *     使用时myWebConfig.getName()
// *  使用@Configuration 时
// *  @Value("${wu.name}")
//    private String name;   
// * @author WRIGHT
// *
// */
//@Configuration
//@PropertySource("classpath:/ssp/ssp.properties")//@PropertySource来指定自定义的资源目录
//@ConfigurationProperties(prefix="wu")
//public class MyWebproperty{  
//    private String name;  
//    private int age;  
//    public String getName() {  
//        return name;  
//    }  
//    public void setName(String name) {  
//        this.name = name;  
//    }  
//    public int getAge() {  
//        return age;  
//    }  
//    public void setAge(int age) {  
//        this.age = age;  
//    }  
//} 
