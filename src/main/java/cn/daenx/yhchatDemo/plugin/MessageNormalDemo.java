package cn.daenx.yhchatDemo.plugin;

import cn.daenx.yhchatsdk.common.constant.ButtonActionTypeConstant;
import cn.daenx.yhchatsdk.common.constant.ChatTypeConstant;
import cn.daenx.yhchatsdk.common.constant.ContentTypeConstant;
import cn.daenx.yhchatsdk.common.constant.RecvTypeConstant;
import cn.daenx.yhchatsdk.framework.eventInterface.EventMessageReceiveNormal;
import cn.daenx.yhchatsdk.framework.utils.ApiUtil;
import cn.daenx.yhchatsdk.framework.vo.EventMsgVo;
import cn.daenx.yhchatsdk.framework.vo.v1.req.ApiSendMsgReqV1;
import cn.daenx.yhchatsdk.framework.vo.v1.ret.ApiSendMsgRetV1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 普通消息事件插件
 *
 * @author DaenMax
 */
@Slf4j
@Service
@Order(1)//相同事件插件中的优先级，越小越优先
public class MessageNormalDemo implements EventMessageReceiveNormal {
    /**
     * 返回-1，后面的实现类将不再执行
     * 返回0，后面的实现类继续执行
     *
     * @param eventMsgVo
     * @return
     */
    @Override
    public Integer handle(EventMsgVo eventMsgVo) {
        String chatType = eventMsgVo.getEvent().getMessage().getChatType();
        String chatId = eventMsgVo.getEvent().getChat().getChatId();
        String senderNickname = eventMsgVo.getEvent().getSender().getSenderNickname();
        String senderId = eventMsgVo.getEvent().getSender().getSenderId();
        String contentType = eventMsgVo.getEvent().getMessage().getContentType();
        String msg = null;
        if (ContentTypeConstant.TEXT.equals(contentType) || ContentTypeConstant.MARKDOWN.equals(contentType)) {
            msg = eventMsgVo.getEvent().getMessage().getContent().getText();
        } else if (ContentTypeConstant.IMAGE.equals(contentType)) {
            msg = eventMsgVo.getEvent().getMessage().getContent().getImageUrl() + " | " + eventMsgVo.getEvent().getMessage().getContent().getImageName() + " | " + eventMsgVo.getEvent().getMessage().getContent().getEtag();
        } else if (ContentTypeConstant.FILE.equals(contentType)) {
            msg = eventMsgVo.getEvent().getMessage().getContent().getFileUrl() + " | " + eventMsgVo.getEvent().getMessage().getContent().getFileName() + " | " + eventMsgVo.getEvent().getMessage().getContent().getFileSize() + " | " + eventMsgVo.getEvent().getMessage().getContent().getEtag();
        }
        if (ChatTypeConstant.GROUP.equals(chatType)) {
            log.info("【群聊普通消息】群号[{}]，用户[{}]（{}）发的[{}]消息：{}", chatId, senderId, senderNickname, contentType, msg);
            ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1()
                    .Text(RecvTypeConstant.GROUP, chatId, "我已经收到了你发的群聊消息：" + msg)
                    .addButton("跳转", ButtonActionTypeConstant.JUMP_URL, "https://www.baidu.com/", null)
                    .addButton("复制", ButtonActionTypeConstant.COPY, null, "复制成功啦")
                    .addButton("上报", ButtonActionTypeConstant.REPORT, null, "上报成功啦");
            ApiSendMsgRetV1 apiSendRetV1 = ApiUtil.sendMsg(reqV1);
            if (apiSendRetV1.getCode() != 1) {
                log.error("消息发送失败");
            }
        } else if (ChatTypeConstant.BOT.equals(chatType)) {
            log.info("【私聊普通消息】用户[{}]（{}）发的[{}]消息：{}", senderId, senderNickname, contentType, msg);
            ApiSendMsgReqV1 reqV1 = new ApiSendMsgReqV1()
                    .Text(RecvTypeConstant.USER, senderId, "我已经收到了你发的私聊消息：" + msg)
                    .addButton("跳转", ButtonActionTypeConstant.JUMP_URL, "https://www.baidu.com/", null)
                    .addButton("复制", ButtonActionTypeConstant.COPY, null, "复制成功啦")
                    .addButton("上报", ButtonActionTypeConstant.REPORT, null, "上报成功啦");
            ApiSendMsgRetV1 apiSendRetV1 = ApiUtil.sendMsg(reqV1);
            if (apiSendRetV1.getCode() != 1) {
                log.error("消息发送失败");
            }
        }
        //返回-1则不再投递后面的相同事件插件
        //返回0则继续投递给后面的相同事件插件处理
        return 0;
    }
}
