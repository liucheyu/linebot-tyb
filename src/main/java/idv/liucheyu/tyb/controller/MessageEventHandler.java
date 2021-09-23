package idv.liucheyu.tyb.controller;

import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.*;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import java.util.Arrays;

@LineMessageHandler
public class MessageEventHandler {


    @EventMapping
    public Message textMessage(MessageEvent<TextMessageContent> event) {
        if (event.getMessage().getText().trim().equals("記帳")) {
            return TemplateMessage.builder()
                    .altText("記帳方式")
                    .template(ButtonsTemplate.builder()
                            .title("記帳")
                            .text("記帳方式")
                            .actions(Arrays.asList(
                                    PostbackAction.builder()
                                            .label("現金")
                                            .text("現金")
                                            //.displayText("現金d")
                                            .data("action=track&method=cash").build())
                            ).build()
                    ).build();
        }
        System.out.printf("event: %s", event);
        return new TextMessage("");
    }

    @EventMapping
    public TextMessage audioMessage(MessageEvent<AudioMessageContent> event) {
        System.out.printf("event: %s", event);
        return new TextMessage("");
    }

    @EventMapping
    public TextMessage fileMessage(MessageEvent<FileMessageContent> event) {
        System.out.printf("event: %s", event);
        return new TextMessage("");
    }

    @EventMapping
    public TextMessage imageMessage(MessageEvent<ImageMessageContent> event) {
        System.out.printf("event: %s", event);
        return new TextMessage("");
    }

    @EventMapping
    public TextMessage locationMessage(MessageEvent<LocationMessageContent> event) {
        System.out.printf("event: %s", event);
        return new TextMessage("");
    }

    @EventMapping
    public TextMessage stickerMessage(MessageEvent<StickerMessageContent> event) {
        System.out.printf("event: %s", event);
        return new TextMessage("");
    }

    @EventMapping
    public TextMessage videoMessage(MessageEvent<VideoMessageContent> event) {
        System.out.printf("event: %s", event);
        return new TextMessage("");
    }





}
