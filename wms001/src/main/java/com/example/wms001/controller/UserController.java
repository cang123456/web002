package com.example.wms001.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms001.common.QueryQageParam;
import com.example.wms001.common.Result;
import com.example.wms001.entity.User;
import com.example.wms001.service.IUserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms001
 * @since 2026-02-02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return  userService.list();
    }


    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return  userService.save(user)?Result.suc():Result.fail();
    }
    //修改
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        return  userService.updateById(user)?Result.suc():Result.fail();
    }
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user) {
        return  userService.updateById(user);
    }
    //新增或者修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user) {
        return  userService.saveOrUpdate(user);
    }
    //删除
    @GetMapping("/delete")
    public Result delete(Integer id) {
        return  userService.removeById(id)?Result.suc():Result.fail();
    }
    //查询 （模糊，匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(user.getName())){
            queryWrapper.like(User::getName, user.getName());
        }
//        queryWrapper.eq(User::getName, user.getName());
        return Result.suc(userService.list(queryWrapper));
    }
    @GetMapping("/findByNo")
    public Result checkDuplicate(@RequestParam String no) {
//        System.out.println("no==="+no);
        List list = userService.lambdaQuery().eq(User::getNo,no).list();
//        System.out.println(list.size());
        return list.size()>0?Result.suc(list):Result.fail();
    }

    @PostMapping("/listPage")
    public List<User> listPage(@RequestBody QueryQageParam query) {
        System.out.println("num==="+query.getPageNum());
        System.out.println("size==="+query.getPageSize());
        HashMap param = query.getParam();

        String name = param.get("name").toString();
//        System.out.println("name==="+param.get("name"));
//        System.out.println("no==="+param.get("no"));
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.like(User::getName, user.getName());
////        queryWrapper.eq(User::getName, user.getName());
//        return userService.list(queryWrapper);
        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName,name);

        IPage result = userService.page(page, lambdaQueryWrapper);

        System.out.println("total==="+result.getTotal());

        return result.getRecords();
    }

    @PostMapping("/listPageC")
    public List<User> listPageC(@RequestBody QueryQageParam query) {
//        System.out.println("num==="+query.getPageNum());
//        System.out.println("size==="+query.getPageSize());
        HashMap param = query.getParam();
        String name = param.get("name").toString();

        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

//        IPage result = userService.pageC(page);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName,name);
        IPage result = userService.pageCC(page, lambdaQueryWrapper);

        System.out.println("total==="+result.getTotal());

        return result.getRecords();
    }
    @PostMapping("/listPageC1")
    public Result listPageC1(@RequestBody QueryQageParam query) {
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        String sex = (String)param.get("sex");

        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(User::getName,name);
        }
        if(StringUtils.isNotBlank(sex)){
            lambdaQueryWrapper.eq(User::getSex,sex);
        }
        IPage result = userService.pageCC(page, lambdaQueryWrapper);

//        System.out.println("total==="+result.getTotal());

        return Result.suc(result.getRecords(),result.getTotal());
    }

}
