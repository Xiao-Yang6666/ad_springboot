package top.xy23.ad_springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;
import top.xy23.ad_springboot.utils.DateUtils;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @author XiaoYang
 * @date 2023/3/30 20:33
 */
@RestController
public class BasController {
    protected final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(DateUtils.parseDate(text));
            }
        });
    }
}
