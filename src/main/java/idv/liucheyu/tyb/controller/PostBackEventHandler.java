package idv.liucheyu.tyb.controller;

import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class PostBackEventHandler {

    @EventMapping
    public TextMessage postBackEvent(PostbackEvent event) {
        System.out.printf("event: %s", event);
        return new TextMessage("");
    }

}
