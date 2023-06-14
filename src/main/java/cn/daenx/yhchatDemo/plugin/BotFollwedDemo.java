package cn.daenx.yhchatDemo.plugin;

import cn.daenx.yhchatsdk.framework.eventInterface.EventBotFollwed;
import cn.daenx.yhchatsdk.framework.vo.EventMsgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;


/**
 * 关注机器人事件
 */
@Slf4j
@Service
@Order(1)//相同事件插件中的优先级，越小越优先
public class BotFollwedDemo implements EventBotFollwed {
    @Override
    public Integer handle(EventMsgVo eventMsgVo) {
        String chatId = eventMsgVo.getEvent().getChatId();
        String userId = eventMsgVo.getEvent().getUserId();
        String nickname = eventMsgVo.getEvent().getNickname();
        //用户头像
        String avatarUrl = eventMsgVo.getEvent().getAvatarUrl();
        log.info("【关注机器人事件】机器人ID[{}]被用户[{}]（{}）关注了", chatId, userId, nickname);
        //返回-1则不再投递后面的同事件插件
        //返回0则继续投递给后面的同事件插件处理
        return 0;
    }
}
