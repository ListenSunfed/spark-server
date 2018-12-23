/**************************************
 * Copyright (C), Navinfo
 * Package: com.navinfo.sparkserver.controller
 * Author: wulongyue06158
 * Date: Created in 2018/12/21 11:32
 **************************************/
package com.navinfo.sparkserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*************************************
 * Class Name: viewController
 * Description:〈配置viewController〉
 * @author wulongyue
 * @create 2018/12/21
 * @since 1.0.0
 ************************************/
@CrossOrigin("*")
@Controller
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置viewController,提供映射路径
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/webSocket").setViewName("/webSocket");
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/chat").setViewName("/chat");
    }
}
