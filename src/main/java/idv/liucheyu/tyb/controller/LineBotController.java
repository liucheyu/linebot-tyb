package idv.liucheyu.tyb.controller;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class LineBotController {

    @EventMapping
    public TextMessage textMessage(MessageEvent<TextMessageContent> textMessageEvent) {
        System.out.printf("event: %s", textMessageEvent);
        return new TextMessage(textMessageEvent.getMessage().getText());
    }

//    @EventMapping
//    public TextMessage defaultEvent(Event event) {
//        System.out.printf("event: %s", event);
//        return new TextMessage(event.toString());
//    }
}
