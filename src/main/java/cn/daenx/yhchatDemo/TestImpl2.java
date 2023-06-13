package cn.daenx.yhchatDemo;

import cn.daenx.yhchatsdk.common.vo.EventMsgVo;
import cn.daenx.yhchatsdk.framework.eventInterface.EventMessageReceiveNormal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 普通消息事件插件2
 */
@Slf4j
@Service
@Order(2)//插件优先级，越小越优先
public class TestImpl2 implements EventMessageReceiveNormal {
    @Override
    public Integer handle(EventMsgVo eventMsgVo) {
        System.out.println("我是普通消息事件插件2，在这里执行业务代码");
        //返回-1则不再投递后面的插件
        return 0;
    }
}
