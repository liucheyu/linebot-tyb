package idv.liucheyu.tyb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.*;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.regex.Pattern;

@LineMessageHandler
public class MessageEventHandler {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    LineMessagingClient lineMessagingClient;

    Pattern intPattern = Pattern.compile("\\d*");

    @EventMapping
    public void textMessage(MessageEvent<TextMessageContent> event) {
        String text = event.getMessage().getText();
        if (intPattern.matcher(text).matches()) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode = objectNode.put("action", "payMethod");
            lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(),
                    TemplateMessage.builder()
                            .altText("記帳方式")
                            .template(ButtonsTemplate.builder()
                                    .title("記帳")
                                    .text("記帳方式")
                                    .actions(Arrays.asList(
                                            PostbackAction.builder()
                                                    .label("現金")
                                                    .text("現金")
                                                    .data(objectNode.set("data", objectMapper.createObjectNode()
                                                            .put("method", "cash")
                                                            .put("amount", text)).toString())
                                                    .build(),
                                            PostbackAction.builder()
                                                    .label("信用卡")
                                                    .text("信用卡")
                                                    .data(objectNode.set("data", objectMapper.createObjectNode()
                                                            .put("method", "credit")
                                                            .put("amount", text)).toString())
                                                    .build(),
                                            PostbackAction.builder()
                                                    .label("電子票證")
                                                    .text("電子票證")
                                                    .data(objectNode.set("data", objectMapper.createObjectNode()
                                                            .put("method", "icard")
                                                            .put("amount", text)).toString())
                                                    .build()
                                            )
                                    ).build()
                            ).build()));
        }

        if (text.equals("現金") || text.equals("信用卡") || text.equals("電子票證")) {
        } else {
            lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), new TextMessage("請先輸入金額")));
        }

        //System.out.printf("event: %s", event);
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
