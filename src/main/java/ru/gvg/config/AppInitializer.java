package ru.gvg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
import java.io.File;

/**
 * @author Valeriy Gyrievskikh
 * @since 08.10.2020
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private int maxUploadSizeInMb = 1024 * 1024 * 1000; // 1000 MB

    private File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    private MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigElement multipartConfigElement = new
                MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);
        return multipartConfigElement;
    }

    @Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }
}
