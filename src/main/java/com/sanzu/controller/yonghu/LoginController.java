package com.sanzu.controller.yonghu;

import com.sanzu.service.bumen.BuMenService;
import com.sanzu.service.yonghu.LoginService;
import com.sanzu.service.yonghu.YongHuService;
import com.sanzu.service.zhiwei.ZhiWeiService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {

//    @Autowired
//    private LoginService loginService;
//    @Autowired
//    private BuMenService buMenService;
//    @Autowired
//    private ZhiWeiService zhiWeiService;
//    @Autowired
//    private YongHuService yongHuService;
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//
//
//
//    private void insertMongodb(){
//        mongoTemplate.insert(yongHuService.selectYongHu(), "yonghu");
//        mongoTemplate.insert(buMenService.selectBuMen(), "bumen");
//        mongoTemplate.insert(zhiWeiService.selectZhiWei(), "zhiwei");
//
//    }


    @RequestMapping(value = "/login2",method = RequestMethod.POST)
    public Map<String, String> login(@RequestBody Map<String, String> map) {
        //insertMongodb();
        // shiro要求密码必须是加密又加盐，这需要保证数据库中的密码是加密和加盐处理的内容才可以被匹配
        // username应该在这里做验证

        /*
        shiro 三大组件  subject(Controller体现) securityManager(IOC容器) realm(自定义代码)
         */
        // 获取用户请求过来的用户名
        String yonghuming = map.get("login");
        // 获取用户请求过来的密码(未加密)
        String mima = map.get("pwd");
        // shiro权限认证主体对象  主体对象是否能得到，由IOC注入成功与失败决定
        Subject currentUser = SecurityUtils.getSubject();
        // 判断用户是否是登陆状态

        //Query query = new Query(Criteria.where("password").is("123456").and("username").is("tk02"));

        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken upToken = new UsernamePasswordToken(yonghuming, mima);    // shiro权限认证类型
            upToken.setRememberMe(true);                                                     // 用户登录时效性
            try {
                currentUser.login(upToken);    // 调用realm认证用户权限
                // 如果try处理时，没有异常，那么代表认证成功，也就是登录成功
                map.put("Status","ok");
                map.put("Text","登录成功!");
                return map;
            } catch (IncorrectCredentialsException ice) {
                System.out.println("用户名/密码不匹配！");
                map.put("Status","error");
                map.put("Text","登录失败!用户名或者密码错误!");
            } catch (LockedAccountException lae) {
                System.out.println("账户已被冻结！");
                map.put("Status","error");
                map.put("Text","登录失败!账户已被冻结!");
            } catch (UnknownAccountException uae) {
                System.out.println("账户不存在！");
                map.put("Status","error");
                map.put("Text","登录失败!账户不存在!");
            } catch (AuthenticationException ae) {
                System.out.println(ae.getMessage());
                System.out.println("没有登录权限！");
                map.put("Status","error");
                map.put("Text","登录失败!没有登录权限!");
            }
        }
        return map;
    }

}
