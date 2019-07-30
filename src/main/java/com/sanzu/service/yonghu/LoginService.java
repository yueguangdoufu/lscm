package com.sanzu.service.yonghu;

import com.sanzu.pojo.YongHu;
import com.sanzu.pojo.YongHuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private YongHuMapper yongHuMapper;

    /**
     *
     * @param yonghuming   { login: login, pwd: pwd, code: code };
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public YongHu selectByYongHuMing(String yonghuming) {
        return yongHuMapper.selectByYongHuMing(yonghuming);
    }
}
