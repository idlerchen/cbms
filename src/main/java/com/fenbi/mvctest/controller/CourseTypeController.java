/**
 * @(#)CourseTypeController.java, 2018年3月8日. 
 * 
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.mvctest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenbi.mvctest.entity.CourseType;
import com.fenbi.mvctest.entity.FenbiResult;
import com.fenbi.mvctest.service.CourseTypeService;

@Controller
@RequestMapping("/courseType")
public class CourseTypeController {
    @Autowired
    CourseTypeService courseTypeService;
    
    /*
     * @Description: 获取所有的课程分类
    * @return: 返回json，包含课程分类的list
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public FenbiResult queryAll() {
        List<CourseType> list = courseTypeService.queryAll();
        return new FenbiResult(list);
    }
}
