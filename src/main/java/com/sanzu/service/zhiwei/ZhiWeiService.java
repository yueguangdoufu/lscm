package com.sanzu.service.zhiwei;

import com.sanzu.pojo.ZhiWei;
import com.sanzu.pojo.ZhiWeiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ZhiWeiService {

    @Autowired
    private ZhiWeiMapper zhiWeiMapper;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Transactional(rollbackFor = Throwable.class)
    public Map<String,String> insertZhiWei(Map<String,String> map) throws ParseException {
        ZhiWei zhiWei = new ZhiWei();
        //var obj = {
        //            "workName": Name , "workDescribe": Describe, "workSearch": Search, "workAssessment": Assessment,
        //            "workSales": Sales, "workPrivilege": Privilege, "workBackstage": Backstage
        //        }
        //"workName": "秘书长",
        ////        "workDescribe": "对整个公司进行管理",
        ////        "workSearch": "是",
        ////        "workAssessment": "是",
        ////        "workSales":"是",
        ////        "workPrivilege": "是",
        ////        "workBackstage": "是"
        zhiWei.setId(UUID.randomUUID().toString().replace("-",""));
        zhiWei.setChaxunquanxina(map.get("workSearch"));
        zhiWei.setHoutaiguanli(map.get("workBackstage"));
        zhiWei.setKaohequanxian(map.get("workAssessment"));
        zhiWei.setZhiweimiaoshu(map.get("workDescribe"));
        zhiWei.setZhiweimingcheng(map.get("workName"));
        zhiWei.setQuanxianguanli(map.get("workPrivilege"));
        zhiWei.setTongjichaxun(map.get("workSales"));

        zhiWeiMapper.insert(zhiWei);
        mongoTemplate.insert(zhiWei,"zhiwei");
        map.put("Status","ok");
        map.put("Text","插入数据成功");
        return map;
    }

    @Transactional(rollbackFor = Throwable.class)
    public List<ZhiWei> selectZhiWei(){
        return mongoTemplate.findAll(ZhiWei.class,"zhiwei");
    }
}
