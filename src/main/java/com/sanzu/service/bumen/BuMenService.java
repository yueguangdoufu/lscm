package com.sanzu.service.bumen;

import com.sanzu.pojo.BuMen;
import com.sanzu.pojo.BuMenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BuMenService {

    @Autowired
    private BuMenMapper buMenMapper;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Transactional(rollbackFor = Throwable.class)
    public Map<String,String> insertBuMen(Map<String,String> map) throws ParseException {
        BuMen buMen = new BuMen();
        //var obj = {"depotId": id, "depotName": depName, "depotSetTime": date, "depotDescribe": describe};
        buMen.setId(UUID.randomUUID().toString().replace("-",""));
        buMen.setBumenmiaoshu(map.get("depotDescribe"));
        buMen.setBumenmingcheng(map.get("depotName"));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        buMen.setChenglishijian(dateFormat.parse(map.get("depotSetTime")));
        buMenMapper.insert(buMen);
        mongoTemplate.insert(buMen,"bumen");
        map.put("Status","ok");
        map.put("Text","插入数据成功");
        return map;
    }

    @Transactional(rollbackFor = Throwable.class)
    public List<BuMen> selectBuMen() {
        return mongoTemplate.findAll(BuMen.class,"bumen");
    }
}
