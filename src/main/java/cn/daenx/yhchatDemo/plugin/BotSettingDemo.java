package cn.daenx.yhchatDemo.plugin;

import cn.daenx.yhchatsdk.framework.eventInterface.EventBotSetting;
import cn.daenx.yhchatsdk.framework.vo.EventMsgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;


/**
 * 机器人设置事件插件
 *
 * @author DaenMax
 */
@Slf4j
@Service
@Order(1)//相同事件插件中的优先级，越小越优先
public class BotSettingDemo implements EventBotSetting {
    /**
     * 返回-1，后面的实现类将不再执行
     * 返回0，后面的实现类继续执行
     *
     * @param eventMsgVo
     * @return
     */
    @Override
    public Integer handle(EventMsgVo eventMsgVo) {
        String chatId = eventMsgVo.getEvent().getChatId();
        String groupId = eventMsgVo.getEvent().getGroupId();
        String groupName = eventMsgVo.getEvent().getGroupName();
        //群头像
        String avatarUrl = eventMsgVo.getEvent().getAvatarUrl();
        //json字符串，自行解析
        String settingJson = eventMsgVo.getEvent().getSettingJson();
        log.info("【机器人设置事件】群号[{}]（{}），机器人ID[{}]，表单内容：{}", groupId, groupName, chatId, settingJson);
        //返回-1则不再投递后面的同事件插件
        //返回0则继续投递给后面的同事件插件处理
        return 0;
    }
}
