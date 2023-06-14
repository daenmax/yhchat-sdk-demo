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

/**
 * 启动类
 *
 * @author DaenMax
 */
@SpringBootApplication
//"cn.daenx.yhchatsdk" 是core的路径，切勿修改
//"cn.daenx.yhchatDemo" 是当前demo的包路径
@ComponentScan({"cn.daenx.yhchatsdk", "cn.daenx.yhchatDemo"})
public class YhchatDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(YhchatDemoApplication.class, args);
        //发送消息
        ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1()
                .Text(RecvTypeConstant.USER, "4137637", "你好")
                .addButton("跳转", ButtonActionTypeConstant.JUMP_URL, "https://www.baidu.com/", null)
                .addButton("复制", ButtonActionTypeConstant.COPY, null, "复制成功啦")
                .addButton("上报", ButtonActionTypeConstant.REPORT, null, "上报成功啦");
        ApiSendMsgRetV1 apiSendRetV1 = ApiUtil.sendMsg(reqV1);

        //批量发送消息
        ApiSendMsgBatchReqV1 reqBatchV1 = new ApiSendMsgBatchReqV1()
                .Text(RecvTypeConstant.GROUP, Arrays.asList("956034802", "123456789"), "你好2")
                .addButton("跳转", ButtonActionTypeConstant.JUMP_URL, "https://www.baidu.com/", null)
                .addButton("复制", ButtonActionTypeConstant.COPY, null, "复制成功啦")
                .addButton("上报", ButtonActionTypeConstant.REPORT, null, "上报成功啦");
        ApiSendMsgBatchRetV1 apiSendMsgBatchRetV1 = ApiUtil.sendMsgBatch(reqBatchV1);
    }

}
