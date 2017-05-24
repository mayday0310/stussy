package com.zk.sbt.controller;

import com.zk.sbt.model.Model;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zk_chs on 4/20/17.
 */
@RestController
public class InitBinderController {

    @PostMapping(value = "binder", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String initBinder (@ModelAttribute Model model){
        System.out.println(model.getName() + " birthday is " + model.getDate());
        return "initBinder";
    }

    /**
     *  该注解必须在需要进行转换Controller中才行,
     *  否则不会进行属性转换
     */
    @InitBinder
    public void bind (WebDataBinder binder){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }

}
