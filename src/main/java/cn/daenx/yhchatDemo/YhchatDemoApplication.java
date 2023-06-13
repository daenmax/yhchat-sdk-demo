package cn.daenx.yhchatDemo;

import cn.daenx.yhchatsdk.common.constant.ButtonActionTypeConstant;
import cn.daenx.yhchatsdk.common.constant.RecvTypeConstant;
import cn.daenx.yhchatsdk.framework.utils.ApiUtil;
import cn.daenx.yhchatsdk.framework.vo.v1.req.ApiSendMsgBatchReqV1;
import cn.daenx.yhchatsdk.framework.vo.v1.req.ApiSendMsgReqV1;
import cn.daenx.yhchatsdk.framework.vo.v1.ret.ApiSendMsgBatchRetV1;
import cn.daenx.yhchatsdk.framework.vo.v1.ret.ApiSendMsgRetV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan({"cn.daenx.yhchatsdk", "cn.daenx.yhchatDemo"})
public class YhchatDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(YhchatDemoApplication.class, args);
//        ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1()
//                .Text(RecvTypeConstant.USER, "4137637", "你好")
//                .addButton("测试", ButtonActionTypeConstant.JUMP_URL, "https://www.baidu.com/", null)
//                .addButton("测试2", ButtonActionTypeConstant.JUMP_URL, "https://www.baidu.com/", null);
//        System.out.println(reqV1);
//        ApiSendMsgRetV1 apiSendRetV1 = ApiUtil.sendMsg(reqV1);
//        System.out.println(apiSendRetV1);

//        ApiSendMsgBatchReqV1 reqBatchV1 = new ApiSendMsgBatchReqV1()
//                .Text(RecvTypeConstant.USER, Arrays.asList("4137637", "123456789"), "你好2")
//                .addButton("测试", ButtonActionTypeConstant.JUMP_URL, "https://www.baidu.com/", null)
//                .addButton("测试2", ButtonActionTypeConstant.JUMP_URL, "https://www.baidu.com/", null);
//        System.out.println(reqBatchV1);
//        ApiSendMsgBatchRetV1 apiSendMsgBatchRetV1 = ApiUtil.sendMsgBatch(reqBatchV1);
//        System.out.println(apiSendMsgBatchRetV1);
    }

}
