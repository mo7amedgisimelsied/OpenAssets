package OpenAssets.demo.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /// Configures to allow cross-origin requests
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST")
                .allowedHeaders("*")
                .exposedHeaders(HttpHeaders.CONTENT_DISPOSITION);
    }

    /// injects storage path from application.properties
    @Value("${app.storage.path}")
    private String storagePath;


    /// Configures resource handlers
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        
        String fileSystemPathUri = Paths.get(storagePath).toUri().toString();
        String finalLocation = fileSystemPathUri.endsWith("/") ? fileSystemPathUri : fileSystemPathUri + "/";
        
        /// map any requests starting with /files/ to the location on the file system defined by finalLocation
        registry.addResourceHandler("/files/**")
                .addResourceLocations(finalLocation);
    }

}
