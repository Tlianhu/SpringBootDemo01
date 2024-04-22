package com.tlh.yk.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tlh.yk.commonutils.R;
import com.tlh.yk.entity.EduTeacher;
import com.tlh.yk.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 田连虎Java
 * @since 2024-04-18
 */
@RestController //控制器注解
@RequestMapping("teacher")  //当前类访问的url
@Controller
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    //查询所有
    @ApiOperation(value = "查询所有讲师")
    @GetMapping("list")
    public R list(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }




    //逻辑删除
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name="id",value = "讲师ID",required = true) @PathVariable String id){
        teacherService.removeById(id);
        return R.ok();
    }

    //分页查询
//    @GetMapping("list")
//    public R list(){
//        IPage<EduTeacher> list = teacherService.page(new Page<>(1,5),null);
//        return R.ok().data("items",list);
//    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page",value = "当前页码",required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable Long limit){
        Page<EduTeacher> pageParam=new Page<EduTeacher>(page,limit);
        teacherService.page(pageParam,null);
        List<EduTeacher> records=pageParam.getRecords();
        long total=pageParam.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }



}

