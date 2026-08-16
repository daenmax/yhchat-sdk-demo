package cn.daenx.yhchatDemo;


import cn.daenx.yhchatsdk.common.constant.ButtonActionTypeConstant;
import cn.daenx.yhchatsdk.common.constant.RecvTypeConstant;
import cn.daenx.yhchatsdk.framework.utils.BoardUtil;
import cn.daenx.yhchatsdk.framework.utils.GroupUtil;
import cn.daenx.yhchatsdk.framework.utils.MessageUtil;
import cn.daenx.yhchatsdk.framework.vo.v1.req.*;
import cn.daenx.yhchatsdk.framework.vo.v1.ret.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Arrays;

/**
 * 启动类
 *
 * @author DaenMax
 */
@SpringBootApplication
//"cn.daenx.yhchatsdk" 是core的路径，切勿修改
//"cn.daenx.yhchatDemo.plugin" 是当前demo的插件所在包路径
@ComponentScan({"cn.daenx.yhchatsdk", "cn.daenx.yhchatDemo.plugin"})
public class YhchatDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(YhchatDemoApplication.class, args);


//——————————————————消息接口——————————————————————开始
        //发送消息
//        sendMsgDemo();
        //批量发送消息
//        sendMsgBatchDemo();
        //发送消息后再撤回消息示例
//        sendMsgAndRecllMsgDemo();
        //发送消息后再编辑消息示例
//        sendMsgAndEditMsgDemo();
        //流式发送消息
//        sendMsgStreamDemo();
        //发送图片消息
//        sendImageDemo();
        //发送视频消息
//        sendVideoDemo();
        //发送文件消息
//        sendFileDemo();
        //发送HTML消息
//        sendHtmlDemo();
        //消息列表
//        messageNormalDemo();
//——————————————————消息接口——————————————————————结束






//——————————————————看板接口——————————————————————开始
        //设置看板
//        setBoardDemo();

        //取消设置看板
//        disBoardDemo();
//——————————————————看板接口——————————————————————结束








//——————————————————群组管理接口——————————————————————开始
        //创建标签
//        createTagDemo();
        //修改标签
//        editTagDemo();
        //获取群标签列表
//        tagListDemo();
        //给用户添加标签
//        addUserTagDemo();
        //给用户移除标签
//        removeUserTagDemo();
        //删除标签
//        deleteTagDemo();
        //移除群成员
//        removeMemberDemo();
        //群成员禁言（600秒=10分钟）
//        gagMemberDemo();
        //群内消息类型控制
//        msgTypeLimitDemo();
//——————————————————群组管理接口——————————————————————结束





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
        ApiSendMsgRetV1 apiSendRetV1 = MessageUtil.sendMsg(reqV1);
    }


    /**
     * 流式发送消息示例
     */
    public static void sendMsgStreamDemo() {
        try {
            PipedInputStream pipedInputStream = new PipedInputStream();
            PipedOutputStream pipedOutputStream = new PipedOutputStream(pipedInputStream);

            // 异步 开个线程模拟一下
            new Thread(() -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        String data = "Message " + (i + 1) + "\n";
                        pipedOutputStream.write(data.getBytes("UTF-8"));
                        pipedOutputStream.flush();
                        // 模拟延迟
                        Thread.sleep(1000);
                    }
                    pipedOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            ApiSendMsgStreamReqV1 reqV1 = new ApiSendMsgStreamReqV1()
                    .Text(RecvTypeConstant.USER, "4137637", pipedInputStream);
            ApiSendMsgStreamRetV1 apiSendMsgStreamRetV1 = MessageUtil.sendMsgStream(reqV1);
            System.out.println(apiSendMsgStreamRetV1);

            pipedInputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送图片示例
     */
    public static void sendImageDemo() {
        ApiUploadReqV1 reqV1Upload = new ApiUploadReqV1()
                .Image("C:\\Users\\13301\\Desktop\\111.png");
        ApiUploadRetV1 uploadRetV1 = MessageUtil.upload(reqV1Upload);
        if (uploadRetV1.getCode() != 1) {
            System.out.println("上传图片失败：" + uploadRetV1.getCode() + ":" + uploadRetV1.getMsg());
            return;
        }
        System.out.println(uploadRetV1);
        ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1()
                .Image(RecvTypeConstant.USER, "4137637", uploadRetV1.getData().getImageKey());
        ApiSendMsgRetV1 apiSendRetV1 = MessageUtil.sendMsg(reqV1);
        System.out.println(apiSendRetV1);
    }


    /**
     * 发送视频示例
     */
    public static void sendVideoDemo() {
        ApiUploadReqV1 reqV1Upload = new ApiUploadReqV1()
                .Video("C:\\Users\\13301\\Desktop\\222.mp4");
        ApiUploadRetV1 uploadRetV1 = MessageUtil.upload(reqV1Upload);
        if (uploadRetV1.getCode() != 1) {
            System.out.println("上传视频失败：" + uploadRetV1.getCode() + ":" + uploadRetV1.getMsg());
            return;
        }
        System.out.println(uploadRetV1);
        ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1()
                .Video(RecvTypeConstant.USER, "4137637", uploadRetV1.getData().getVideoKey());
        ApiSendMsgRetV1 apiSendRetV1 = MessageUtil.sendMsg(reqV1);
        System.out.println(apiSendRetV1);
    }


    /**
     * 发送文件示例
     */
    public static void sendFileDemo() {
        ApiUploadReqV1 reqV1Upload = new ApiUploadReqV1()
                .File("C:\\Users\\13301\\Desktop\\333.txt");
        ApiUploadRetV1 uploadRetV1 = MessageUtil.upload(reqV1Upload);
        if (uploadRetV1.getCode() != 1) {
            System.out.println("上传文件失败：" + uploadRetV1.getCode() + ":" + uploadRetV1.getMsg());
            return;
        }
        System.out.println(uploadRetV1);
        ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1()
                .File(RecvTypeConstant.USER, "4137637", uploadRetV1.getData().getFileKey());
        ApiSendMsgRetV1 apiSendRetV1 = MessageUtil.sendMsg(reqV1);
        System.out.println(apiSendRetV1);
    }


    /**
     * 发送HTML示例
     */
    public static void sendHtmlDemo() {
        ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1()
                .Html(RecvTypeConstant.GROUP, "956034802", "<del>删除线</del>");
        ApiSendMsgRetV1 apiSendRetV1 = MessageUtil.sendMsg(reqV1);
        System.out.println(apiSendRetV1);
    }


    /**
     * 发送HTML示例
     */
    public static void messageNormalDemo() {
        ApiMessagesReqV1 reqV1 = new ApiMessagesReqV1(RecvTypeConstant.GROUP, "956034802", null, 20, null);
        ApiMessagesRetV1 apiMessagesRetV1 = MessageUtil.messages(reqV1);
        System.out.println(apiMessagesRetV1);
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
        ApiSendMsgBatchRetV1 apiSendMsgBatchRetV1 = MessageUtil.sendMsgBatch(reqBatchV1);
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
        ApiSendMsgRetV1 apiSendRetV1 = MessageUtil.sendMsg(reqV1);
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
        ApiEditMsgRetV1 apiEditMsgRetV1 = MessageUtil.editMsg(editV1);
    }


    /**
     * 发送消息后再撤回消息示例
     */
    public static void sendMsgAndRecllMsgDemo() {
        //发送消息
        ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1()
                .Text(RecvTypeConstant.USER, "4137637", "你好")
                .addButton("跳转", ButtonActionTypeConstant.JUMP_URL, "https://www.baidu.com/", null)
                .addButton("复制", ButtonActionTypeConstant.COPY, null, "复制成功啦")
                .addButton("上报", ButtonActionTypeConstant.REPORT, null, "上报成功啦");
        ApiSendMsgRetV1 apiSendRetV1 = MessageUtil.sendMsg(reqV1);
        String msgId = apiSendRetV1.getData().getMessageInfo().getMsgId();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //撤回消息
        ApiRecallMsgReqV1 editV1 = new ApiRecallMsgReqV1(msgId, RecvTypeConstant.USER, "4137637");
        ApiRecallMsgRetV1 apiRecallMsgRetV1 = MessageUtil.recallMsg(editV1);
        System.out.println(apiRecallMsgRetV1.toString());
    }


    /**
     * 设置看板示例
     */
    public static void setBoardDemo() {
        ApiSetBoardReqV1 boardReqV1 = new ApiSetBoardReqV1();
        //对单个用户设置看板
        boardReqV1.Text(RecvTypeConstant.USER, "4137637", "你好");
        //对全局用户设置看板
//        boardReqV1.Html(null, null, "<h1>我是全局看板，你好朋友</h1>");
        ApiSetBoardRetV1 apiSetBoardRetV1 = BoardUtil.setBoard(boardReqV1);
    }


    /**
     * 取消设置看板示例
     */
    public static void disBoardDemo() {
        ApiDisBoardReqV1 boardReqV1 = new ApiDisBoardReqV1();
        //取消对单个用户设置的看板
        boardReqV1.Dis(RecvTypeConstant.USER, "4137637");
        //取消对全局用户设置的看板
//        boardReqV1.Dis(null, null);
        ApiDisBoardRetV1 apiDisBoardRetV1 = BoardUtil.disBoard(boardReqV1);
    }

    /**
     * 创建标签示例
     */
    public static void createTagDemo() {
        ApiGroupTagCreateReqV1 reqV1 = new ApiGroupTagCreateReqV1("956034802", "VIP用户3", "#FF5733", "VIP会员用户标签2", 1L);
        ApiGroupTagCreateRetV1 apiGroupTagCreateRetV1 = GroupUtil.createTag(reqV1);
        System.out.println(apiGroupTagCreateRetV1);
    }


    /**
     * 修改标签示例（把“VIP用户”改名为“SVIP用户”）
     */
    public static void editTagDemo() {
        ApiGroupTagEditReqV1 reqV1 = new ApiGroupTagEditReqV1("956034802", "VIP用户", "SVIP用户", "#FF5733", "VIP会员用户标签（已更新）", 2L);
        ApiGroupTagEditRetV1 apiGroupTagEditRetV1 = GroupUtil.editTag(reqV1);
        System.out.println(apiGroupTagEditRetV1);
    }


    /**
     * 获取群标签列表示例
     */
    public static void tagListDemo() {
        ApiGroupTagListReqV1 reqV1 = new ApiGroupTagListReqV1("956034802");
        ApiGroupTagListRetV1 apiGroupTagListRetV1 = GroupUtil.tagList(reqV1);
        for (ApiGroupTagListRetV1.RetData.TagInfo tagInfo : apiGroupTagListRetV1.getData().getList()) {
            //tag=VIP用户2, desc=VIP会员用户标签2, color=#FF5733, sort=1
            System.out.println(tagInfo.getTag() + "," + tagInfo.getDesc() + "," + tagInfo.getColor() + "," + tagInfo.getSort());
        }
        System.out.println(apiGroupTagListRetV1);
    }


    /**
     * 给用户添加标签示例
     */
    public static void addUserTagDemo() {
        ApiGroupTagUserRelateReqV1 reqV1 = new ApiGroupTagUserRelateReqV1("1638117", "VIP用户2", "956034802");
        ApiGroupTagUserRelateRetV1 apiGroupTagUserRelateRetV1 = GroupUtil.addUserTag(reqV1);
        System.out.println(apiGroupTagUserRelateRetV1);
    }


    /**
     * 给用户移除标签示例
     */
    public static void removeUserTagDemo() {
        ApiGroupTagUserRelateCancelReqV1 reqV1 = new ApiGroupTagUserRelateCancelReqV1("1638117", "VIP用户2", "956034802");
        ApiGroupTagUserRelateCancelRetV1 apiGroupTagUserRelateCancelRetV1 = GroupUtil.removeUserTag(reqV1);
        System.out.println(apiGroupTagUserRelateCancelRetV1);
    }


    /**
     * 删除标签示例
     */
    public static void deleteTagDemo() {
        ApiGroupTagDeleteReqV1 reqV1 = new ApiGroupTagDeleteReqV1("VIP用户2", "956034802");
        ApiGroupTagDeleteRetV1 apiGroupTagDeleteRetV1 = GroupUtil.deleteTag(reqV1);
        System.out.println(apiGroupTagDeleteRetV1);
    }


    /**
     * 移除群成员示例
     */
    public static void removeMemberDemo() {
        ApiGroupRemoveMemberReqV1 reqV1 = new ApiGroupRemoveMemberReqV1("1638117", "956034802");
        ApiGroupRemoveMemberRetV1 apiGroupRemoveMemberRetV1 = GroupUtil.removeMember(reqV1);
        System.out.println(apiGroupRemoveMemberRetV1);
    }


    /**
     * 群成员禁言示例（600秒=10分钟，0为解除禁言，-1为永久禁言）
     */
    public static void gagMemberDemo() {
        ApiGroupGagMemberReqV1 reqV1 = new ApiGroupGagMemberReqV1("1638117", "956034802", 600L);
        ApiGroupGagMemberRetV1 apiGroupGagMemberRetV1 = GroupUtil.gagMember(reqV1);
        System.out.println(apiGroupGagMemberRetV1);
    }


    /**
     * 群内消息类型控制示例（传空字符串表示不限制任何消息类型）
     */
    public static void msgTypeLimitDemo() {
        ApiGroupMsgTypeLimitReqV1 reqV1 = new ApiGroupMsgTypeLimitReqV1("956034802", "text,image,video");
        ApiGroupMsgTypeLimitRetV1 apiGroupMsgTypeLimitRetV1 = GroupUtil.msgTypeLimit(reqV1);
        System.out.println(apiGroupMsgTypeLimitRetV1);
    }
}