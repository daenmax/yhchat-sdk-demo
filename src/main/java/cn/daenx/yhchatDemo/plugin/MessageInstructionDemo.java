package cn.daenx.yhchatDemo.plugin;

import cn.daenx.yhchatsdk.common.constant.ChatTypeConstant;
import cn.daenx.yhchatsdk.common.constant.ContentTypeConstant;
import cn.daenx.yhchatsdk.framework.eventInterface.EventMessageReceiveInstruction;
import cn.daenx.yhchatsdk.framework.vo.EventMsgVo;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 指令消息事件插件
 *
 * @author DaenMax
 */
@Slf4j
@Service
@Order(1)//相同事件插件中的优先级，越小越优先
public class MessageInstructionDemo implements EventMessageReceiveInstruction {
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
        Integer instructionId = eventMsgVo.getEvent().getMessage().getInstructionId();
        String instructionName = eventMsgVo.getEvent().getMessage().getInstructionName();
        String msg = null;
        if (ContentTypeConstant.TEXT.equals(contentType)) {
            msg = eventMsgVo.getEvent().getMessage().getContent().getText();
        } else if (ContentTypeConstant.FORM.equals(contentType)) {
            HashMap<String, HashMap<String, Object>> formJson = eventMsgVo.getEvent().getMessage().getContent().getFormJson();
            //这里自行解析，我只是简单的toJsonStr方便打印而已
            msg = JSONUtil.toJsonStr(formJson);
        }
        if (ChatTypeConstant.GROUP.equals(chatType)) {
            log.info("【群聊指令消息】群号[{}]，用户[{}]（{}）触发了指令[{}]({})，附带内容为：{}", chatId, senderId, senderNickname, instructionId, instructionName, msg);
        } else if (ChatTypeConstant.BOT.equals(chatType)) {
            log.info("【私聊指令消息】用户[{}]（{}）触发了指令[{}]({})，附带内容为：{}", senderId, senderNickname, instructionId, instructionName, msg);
        }
        //返回-1则不再投递后面的同事件插件
        //返回0则继续投递给后面的同事件插件处理
        return 0;
    }
}
