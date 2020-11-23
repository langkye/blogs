package com.langkye.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/*
* Swagger的所有注解定义在io.swagger.annotations包下
*   下面列一些经常用到的，未列举出来的可以另行查阅说明：
**/

/**
 * @author langkye
 */
//配置类
@Configuration
// 开启Swagger2的自动配置
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 配置docket以配置Swagger具体参数
     * @return Docket
     */
    @Bean
    public Docket docket(Environment environment) {
        // 设置要显示swagger的环境
        Profiles env = Profiles.of("dev", "test");
        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean swaggerEnable = environment.acceptsProfiles(env);
        //return new Docket(DocumentationType.SWAGGER_2);
        //Docket 实例关联上 apiInfo()
        return new Docket(DocumentationType.SWAGGER_2)
                //配置是否启用Swagger，如果是false，在浏览器将无法访问
                .enable(swaggerEnable)
                .apiInfo(apiInfo())
                // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.langkye.controller"))
                .build()
                .groupName("LANGKYE")
                ;
        /**
         * RequestHandlerSelectors
         *      .any() // 扫描所有，项目中的所有接口都会被扫描到
         *      .none() // 不扫描接口
         *      .withMethodAnnotation(final Class<? extends Annotation> annotation) //基于方法注解扫描，如.withMethodAnnotation(GetMapping.class)只扫描get请求
         *      .withClassAnnotation(final Class<? extends Annotation> annotation) //基于类注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
         *      .basePackage(final String basePackage) // 根据包路径扫描接口,如.basePackage("com.langkye.controller")
         **/
    }
    /*
    * @Bean
    * public Docket docket() {
    *     return new Docket(DocumentationType.SWAGGER_2)
    *                     .apiInfo(apiInfo())
    *                     .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
    *                     .apis(RequestHandlerSelectors.basePackage("com.langkye.controller"))
    *                     .paths(PathSelectors.ant("/langkye/**")) // 配置如何通过path过滤,即这里只扫描 [请求: 浏览器请求]以/langkye开头的接口，如http://localhost:8080/langkye/profile
    *                                          //上面的.ant()可以换为下面的方法
    *                                          .any() // 任何请求都扫描
    *                                          .none() // 任何请求都不扫描
    *                                          .regex(final String pathRegex) // 通过正则表达式控制
    *                                          .ant(final String antPattern) // 通过ant()控制
    *                     .build();
    * }
    **/

    /*@Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("GROUP_ONE");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("GROUP_TOW");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("GROUP_THREE");
    }*/



    /**
     * 配置文档信息
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("langkye", "https://cnblogs.com/langkyesir", "langkye@gmail.com");
        return new ApiInfo(
                // 标题
                "blogs",
                // 描述
                "博客个人版",
                // 版本
                "v1.0",
                // 组织链接
                "https://github.com/langkye/blogs/tree/master/blogs-java",
                // 联系人信息
                contact,
                // 许可
                "Apach 2.0 许可",
                // 许可连接
                "许可链接",
                // 扩展
                new ArrayList<>()
        );
    }

}
