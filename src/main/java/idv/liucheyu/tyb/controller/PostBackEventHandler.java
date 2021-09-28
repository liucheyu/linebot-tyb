package idv.liucheyu.tyb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import idv.liucheyu.tyb.model.ActionModel;
import org.springframework.beans.factory.annotation.Autowired;


@LineMessageHandler
public class PostBackEventHandler {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    LineMessagingClient lineMessagingClient;

    @EventMapping
    public void postBackEvent(PostbackEvent event) throws JsonProcessingException {
        System.out.println(event.getPostbackContent().getData());
        ActionModel actionModel = objectMapper.readValue(event.getPostbackContent().getData(), ActionModel.class);
        switch (actionModel.getData().getMethod()){
            case "cash":
                lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), new TextMessage("現金記帳")));
                break;
            case "credit":
                lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), new TextMessage("信用卡記帳")));

                break;
            case "icard":
                lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), new TextMessage("電子票證記帳")));

                break;
        }
        System.out.println(actionModel);
        //return new TextMessage("ok");
    }

}
