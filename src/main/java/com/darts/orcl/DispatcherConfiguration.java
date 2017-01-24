package com.darts.orcl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jpc on 1/19/17.
 */
@Configuration
@EnableWebMvc
@ComponentScan({"com.darts.orcl"})
public class DispatcherConfiguration extends WebMvcConfigurerAdapter {
}
