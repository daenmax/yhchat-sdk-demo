package cn.daenx.yhchatDemo;

import cn.daenx.yhchatsdk.common.vo.ApiRetVo;
import cn.daenx.yhchatsdk.framework.utils.ApiUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"cn.daenx.yhchatsdk", "cn.daenx.yhchatDemo"})
public class YhchatDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(YhchatDemoApplication.class, args);
        //http://172.16.111.207:8035/demo/event/test
        ApiRetVo apiRetVo = ApiUtil.sendMsg();
        System.out.println(apiRetVo.toString());
    }

}
