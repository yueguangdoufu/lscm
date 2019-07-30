package com.sanzu.controller.yonghu;

import com.sanzu.pojo.YongHu;
import com.sanzu.service.yonghu.YongHuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
public class YongHuController {

    @Autowired
    private YongHuService yongHuService;

    @RequestMapping(value = "/select_yonghu",method = RequestMethod.POST)
    public List<YongHu> selectYongHu(){
        return yongHuService.selectYongHu();
    }

    @RequestMapping(value = "/insert_yonghu",method = RequestMethod.POST)
    public Map<String,String> insertYongHu(@RequestBody Map<String,String> map) throws ParseException {
        System.out.println("map = " + map);
        return yongHuService.insertYongHu(map);
    }

    @RequestMapping(value = "/update_yonghu",method = RequestMethod.POST)
    public Map<String,String> updateYongHu(@RequestBody Map<String,String> map){
        System.out.println("map = "+map);
        return yongHuService.updateYongHu(map);
    }

    @RequestMapping(value = "/delete_yonghu",method = RequestMethod.POST)
    public Map<String,String> deleteYongHu(@RequestBody Map<String,String> map){
        System.out.println("map = "+map);
        return yongHuService.deleteYongHu(map);
    }
}
