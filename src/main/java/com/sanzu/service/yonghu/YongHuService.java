package com.sanzu.service.yonghu;

import com.sanzu.pojo.YongHu;
import com.sanzu.pojo.YongHuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class YongHuService {

    @Autowired
    private YongHuMapper yongHuMapper;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Transactional(rollbackFor = Throwable.class)
    public List<YongHu> selectYongHu(){
        return mongoTemplate.findAll(YongHu.class,"yonghu");
    }

    @Transactional(rollbackFor = Throwable.class)
    public Map<String,String> insertYongHu(Map<String,String> map) throws ParseException {
        YongHu yongHu = new YongHu();


        /*id, yonghuming, mima, xingming, xingbie, chushengriqi, wenhuachengdu,
        lianxifangshi, suoshuzhuanye, suozaibum, gongzuozhiwei, jiatingdizhi, zhengzhimianmao, ruzhishijian

        "empId": 1,"empName": "张三","empSex": "男", "empBirth": "1990-10-1","empEducation": "高中",
            "empTel": "13392876531","depotId": 1,"depotName": "人事部","workId": 2,"workName": "经理",
            "empMajor": "计算机","empAddress": "成都高新","empOutlook": "党员","empEntryTime": "2000-1-1"
            */
        yongHu.setId(UUID.randomUUID().toString().replace("-",""));
        yongHu.setXingming(map.get("empName"));
        yongHu.setXingbie(map.get("empSex"));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        yongHu.setChushengriqi(dateFormat.parse(map.get("empBirth")));
        yongHu.setWenhuachengdu(map.get("empEducation"));
        yongHu.setLianxifangshi(map.get("empTel"));
        yongHu.setSuozaibum(map.get("depotName"));
        yongHu.setGongzuozhiwei(map.get("workName"));
        yongHu.setSuoshuzhuanye(map.get("empMajor"));
        yongHu.setJiatingdizhi(map.get("empAddress"));
        yongHu.setZhengzhimianmao(map.get("empOutlook"));
        yongHu.setRuzhishijian(dateFormat.parse(map.get("empEntryTime")));

        yongHuMapper.insert(yongHu);

        mongoTemplate.insert(yongHu,"yonghu");

        map.put("Status","ok");
        map.put("Text","插入数据成功");
        map.put("empId",yongHu.getId());
        return map;
    }

    @Transactional(rollbackFor = Throwable.class)
    public Map<String,String> updateYongHu(Map<String,String> map){
        /*id, yonghuming, mima, xingming, xingbie, chushengriqi, wenhuachengdu,
        lianxifangshi, suoshuzhuanye, suozaibum, gongzuozhiwei, jiatingdizhi, zhengzhimianmao, ruzhishijian

        "empId": 1,"empName": "张三","empSex": "男", "empBirth": "1990-10-1","empEducation": "高中",
            "empTel": "13392876531","depotId": 1,"depotName": "人事部","workId": 2,"workName": "经理",
            "empMajor": "计算机","empAddress": "成都高新","empOutlook": "党员","empEntryTime": "2000-1-1"
            */
        System.out.println("更新的 = "+map);

        YongHu yongHu;

        yongHu = yongHuMapper.selectByPrimaryKey(map.get("empId"));

        yongHu.setLianxifangshi(map.get("empTel"));
        yongHu.setZhengzhimianmao(map.get("empOutlook"));
        yongHu.setSuozaibum(map.get("depotName"));
        yongHu.setGongzuozhiwei(map.get("workName"));

        yongHuMapper.updateByPrimaryKeySelective(yongHu);
        Query query = new Query(Criteria.where("_id").is(map.get("empId")));
        mongoTemplate.remove(query,"yonghu");
        mongoTemplate.insert(yongHu,"yonghu");

        map.put("Status","ok");
        map.put("Text","插入数据成功");
        return map;
    }

    @Transactional(rollbackFor = Throwable.class)
    public Map<String,String> deleteYongHu(Map<String,String> map){
        yongHuMapper.deleteByPrimaryKey(map.get("empId"));

        Query query = new Query(Criteria.where("_id").is(map.get("empId")));
        mongoTemplate.remove(query,"yonghu");

        map.put("Status","ok");
        map.put("Text","插入数据成功");
        return map;
    }
}
