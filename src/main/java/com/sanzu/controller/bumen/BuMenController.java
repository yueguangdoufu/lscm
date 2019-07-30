package com.sanzu.controller.bumen;

import com.sanzu.pojo.BuMen;
import com.sanzu.service.bumen.BuMenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
public class BuMenController {

    @Autowired
    private BuMenService buMenService;

    @RequestMapping(value = "/insert_bumen",method = RequestMethod.POST)
    public Map<String,String> insertBuMen(@RequestBody Map<String,String> map)throws ParseException {
        System.out.println("map = " +map);
        return buMenService.insertBuMen(map);
    }

    @RequestMapping(value = "/select_bumen",method = RequestMethod.POST)
    public List<BuMen> selectBuMen(){
        return buMenService.selectBuMen();
    }


}
