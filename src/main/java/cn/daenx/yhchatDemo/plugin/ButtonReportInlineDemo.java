package cn.daenx.yhchatDemo.plugin;

import cn.daenx.yhchatsdk.common.constant.ChatTypeConstant;
import cn.daenx.yhchatsdk.common.constant.ContentTypeConstant;
import cn.daenx.yhchatsdk.common.constant.RecvTypeConstant;
import cn.daenx.yhchatsdk.framework.eventInterface.EventButtonReportInline;
import cn.daenx.yhchatsdk.framework.eventInterface.EventMessageReceiveNormal;
import cn.daenx.yhchatsdk.framework.vo.EventMsgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 消息中按钮点击事件
 */
@Slf4j
@Service
@Order(1)//相同事件插件中的优先级，越小越优先
public class ButtonReportInlineDemo implements EventButtonReportInline {
    @Override
    public Integer handle(EventMsgVo eventMsgVo) {
        String recvType = eventMsgVo.getEvent().getRecvType();
        String recvId = eventMsgVo.getEvent().getRecvId();
        String senderId = eventMsgVo.getEvent().getSenderId();
        String value = eventMsgVo.getEvent().getValue();
        if (RecvTypeConstant.GROUP.equals(recvType)) {
            log.info("【消息中按钮点击事件】群号[{}]，用户ID[{}]触发了按钮上报，消息：{}", recvId, senderId, value);
        } else if (RecvTypeConstant.USER.equals(recvType)) {
            log.info("【消息中按钮点击事件】用户ID[{}]触发了按钮上报，消息：{}", recvId, value);
        }
        //返回-1则不再投递后面的相同事件插件
        //返回0则继续投递给后面的相同事件插件处理
        return 0;
    }
}
