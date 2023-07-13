package cn.daenx.yhchatDemo;


import cn.daenx.yhchatsdk.common.constant.ButtonActionTypeConstant;
import cn.daenx.yhchatsdk.common.constant.RecvTypeConstant;
import cn.daenx.yhchatsdk.framework.utils.ApiUtil;
import cn.daenx.yhchatsdk.framework.vo.v1.req.*;
import cn.daenx.yhchatsdk.framework.vo.v1.ret.*;
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
//        sendMsgDemo();

        //批量发送消息
//        sendMsgBatchDemo();

        //发送消息后再编辑消息示例
//        sendMsgAndEditMsgDemo();

        //设置看板
//        setBoardDemo();

        //取消设置看板
//        disBoardDemo();
    }

    /**
     * 发送消息示例
     */
    public static void sendMsgDemo() {
        ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1()
                .Text(RecvTypeConstant.USER, "4137637", "你好")
                .addButton("跳转", ButtonActionTypeConstant.JUMP_URL, "https://www.baidu.com/", null)
                .addButton("复制", ButtonActionTypeConstant.COPY, null, "复制成功啦")
                .addButton("上报", ButtonActionTypeConstant.REPORT, null, "上报成功啦");
        ApiSendMsgRetV1 apiSendRetV1 = ApiUtil.sendMsg(reqV1);
    }

    /**
     * 批量发送消息示例
     */
    public static void sendMsgBatchDemo() {
        ApiSendMsgBatchReqV1 reqBatchV1 = new ApiSendMsgBatchReqV1()
                .Text(RecvTypeConstant.GROUP, Arrays.asList("956034802", "123456789"), "你好2")
                .addButton("跳转", ButtonActionTypeConstant.JUMP_URL, "https://www.baidu.com/", null)
                .addButton("复制", ButtonActionTypeConstant.COPY, null, "复制成功啦")
                .addButton("上报", ButtonActionTypeConstant.REPORT, null, "上报成功啦");
        ApiSendMsgBatchRetV1 apiSendMsgBatchRetV1 = ApiUtil.sendMsgBatch(reqBatchV1);
    }

    /**
     * 发送消息后再编辑消息示例
     * 使用场景：点击按钮上报后，再进行动态修改
     */
    public static void sendMsgAndEditMsgDemo() {
        //发送消息
        ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1()
                .Text(RecvTypeConstant.USER, "4137637", "你好")
                .addButton("跳转", ButtonActionTypeConstant.JUMP_URL, "https://www.baidu.com/", null)
                .addButton("复制", ButtonActionTypeConstant.COPY, null, "复制成功啦")
                .addButton("上报", ButtonActionTypeConstant.REPORT, null, "上报成功啦");
        ApiSendMsgRetV1 apiSendRetV1 = ApiUtil.sendMsg(reqV1);
        String msgId = apiSendRetV1.getData().getMessageInfo().getMsgId();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //编辑消息
        ApiEditMsgReqV1 editV1 = new ApiEditMsgReqV1()
                .setMsgId(msgId)
                .Text(RecvTypeConstant.USER, "4137637", "哈哈哈哈哈哈")
                .addButton("速速跳转", ButtonActionTypeConstant.JUMP_URL, "https://www.baidu.com/", null);
        ApiEditMsgRetV1 apiEditMsgRetV1 = ApiUtil.editMsg(editV1);
    }


    /**
     * 设置看板
     */
    public static void setBoardDemo() {
        ApiSetBoardReqV1 boardReqV1 = new ApiSetBoardReqV1();
        //对单个用户设置看板
        boardReqV1.Text(RecvTypeConstant.USER, "4137637", "你好");
        //对全局用户设置看板
//        boardReqV1.Html(null, null, "<h1>我是全局看板，你好朋友</h1>");
        ApiSetBoardRetV1 apiSetBoardRetV1 = ApiUtil.setBoard(boardReqV1);
    }


    /**
     * 取消设置看板
     */
    public static void disBoardDemo() {
        ApiDisBoardReqV1 boardReqV1 = new ApiDisBoardReqV1();
        //取消对单个用户设置的看板
        boardReqV1.Dis(RecvTypeConstant.USER, "4137637");
        //取消对全局用户设置的看板
//        boardReqV1.Dis(null, null);
        ApiDisBoardRetV1 apiDisBoardRetV1 = ApiUtil.disBoard(boardReqV1);
    }
}
