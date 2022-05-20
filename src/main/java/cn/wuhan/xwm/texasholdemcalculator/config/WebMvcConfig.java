package cn.wuhan.xwm.texasholdemcalculator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Wei Ming
 * @Date: 2022/5/20
 * @Contact: swimming.xwm@gmail.com
 * @Description 全剧跨域
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("Content-Type","X-Requested-With","accept,Origin","Access-Control-Request-Method","Access-Control-Request-Headers","token")
                .allowedMethods("*")
                .allowedOrigins("*");
//                .allowCredentials(true);
    }
}
