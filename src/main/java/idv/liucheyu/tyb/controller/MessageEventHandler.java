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
import idv.liucheyu.tyb.service.MeesageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.regex.Pattern;

@LineMessageHandler
public class MessageEventHandler {

    @Autowired
    MeesageService meesageService;
    @Autowired
    LineMessagingClient lineMessagingClient;

    Pattern pricePattern = Pattern.compile("#{1}\\d*\\.?\\d*");
    Pattern reportPattern = Pattern.compile("#{1}報表");
    Pattern intPattern = Pattern.compile("\\d*");

    @EventMapping
    public void textMessage(MessageEvent<TextMessageContent> event) {
        String text = event.getMessage().getText();
        if (pricePattern.matcher(text).matches()) {
            text = text.replace("#", "");
            lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(),
                    meesageService.getPayMethodQuetsionMeesage(text)));
            return;
        }

        if(reportPattern.matcher(text).matches()) {
            lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(),
                    new TextMessage("report")));
            return;
        }

        if((Event)event instanceof PostbackEvent) {
            return;
        }

        lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), meesageService.getDefaultMessage()));
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
