package com.company.ellRes.configuration;

import com.company.ellRes.dataService.dataFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path.image}")
    private String uploadPathImage;
    @Value("${upload.path.document}")
    private String uploadPathDocument;
    @Value("${upload.path.caption}")
    private String uploadPathCaption;
    @Value("${upload.path.resolve}")
    private String uploadPathResolution;

    @Autowired
    private dataFile dataFile;

    public MvcConfig() {
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/img/**"}).addResourceLocations(new String[]{"file:" + this.uploadPathImage + "/" + dataFile.geteTodayYer() + "/"});
        registry.addResourceHandler(new String[]{"/caption/**"}).addResourceLocations(new String[]{"file:" + this.uploadPathCaption + "/"});
        registry.addResourceHandler(new String[]{"/pdf/**"}).addResourceLocations(new String[]{"file:" + this.uploadPathDocument + "/" + dataFile.geteTodayYer() + "/"});
        registry.addResourceHandler(new String[]{"/resolve/**"}).addResourceLocations(new String[]{"file:" + this.uploadPathResolution + "/" + dataFile.geteTodayYer() + "/"});
        registry.addResourceHandler(new String[]{"/static/**"}).addResourceLocations(new String[]{"classpath:/static/"});

    }

}
