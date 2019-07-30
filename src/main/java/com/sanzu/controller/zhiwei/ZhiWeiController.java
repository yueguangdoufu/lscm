package com.sanzu.controller.zhiwei;

import com.sanzu.pojo.ZhiWei;
import com.sanzu.service.zhiwei.ZhiWeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
public class ZhiWeiController {

    @Autowired
    private ZhiWeiService zhiWeiService;

    @RequestMapping(value = "/insert_zhiwei",method = RequestMethod.POST)
    public Map<String,String> insertZhiWei(@RequestBody Map<String,String> map)throws ParseException {
        System.out.println("map = " +map);
        return zhiWeiService.insertZhiWei(map);
    }

    @RequestMapping(value = "/select_zhiwei",method = RequestMethod.POST)
    public List<ZhiWei> selectZhiWei(){
        return zhiWeiService.selectZhiWei();
    }
}
