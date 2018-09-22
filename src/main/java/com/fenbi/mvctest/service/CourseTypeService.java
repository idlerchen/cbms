/**
 * @(#)CourseTypeService.java, 2018年3月8日. 
 * 
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.mvctest.service;

import java.util.List;

import com.fenbi.mvctest.entity.CourseType;


/**
 * @author heqiang
 *
 */
public interface CourseTypeService {
    /**
     * 
     * @return 直播课类型列表
     */
    List<CourseType> queryAll();

}
