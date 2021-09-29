package idv.liucheyu.tyb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import idv.liucheyu.tyb.model.ActionModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;


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
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode = objectNode.put("action", "payMethodType");
        switch (actionModel.getData().getMethod()){
            case "cash":
                lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), new TextMessage("現金記帳")));
                break;
            case "credit":
                lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), TemplateMessage.builder()
                        .altText("信用卡名稱")
                        .template(ButtonsTemplate.builder()
                                .title("信用卡")
                                .text("信用卡名稱")
                                .actions(Arrays.asList(
                                        PostbackAction.builder()
                                                .label("台灣銀行")
                                                .text("台灣銀行")
                                                .data(objectNode.set("data", objectMapper.createObjectNode()
                                                        .put("method", "credit")
                                                        .put("cardName", "台灣銀行")).toString())
                                                .build(),
                                        PostbackAction.builder()
                                                .label("台新銀行")
                                                .text("台新銀行")
                                                .data(objectNode.set("data", objectMapper.createObjectNode()
                                                        .put("method", "credit")
                                                        .put("cardName", "台新銀行")).toString())
                                                .build(),
                                        PostbackAction.builder()
                                                .label("國泰銀行")
                                                .text("國泰銀行")
                                                .data(objectNode.set("data", objectMapper.createObjectNode()
                                                        .put("method", "credit")
                                                        .put("cardName", "國泰銀行")).toString())
                                                .build()
                                        )
                                ).build()
                        ).build()));
                break;
            case "icard":
                lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), TemplateMessage.builder()
                        .altText("電子票證名稱")
                        .template(ButtonsTemplate.builder()
                                .title("電子票證")
                                .text("電子票證名稱")
                                .actions(Arrays.asList(
                                        PostbackAction.builder()
                                                .label("悠遊卡")
                                                .text("悠遊卡")
                                                .data(objectNode.set("data", objectMapper.createObjectNode()
                                                        .put("method", "icard")
                                                        .put("cardName", "悠遊卡")).toString())
                                                .build(),
                                        PostbackAction.builder()
                                                .label("一卡通")
                                                .text("一卡通")
                                                .data(objectNode.set("data", objectMapper.createObjectNode()
                                                        .put("method", "icard")
                                                        .put("cardName", "一卡通")).toString())
                                                .build()
                                        )
                                ).build()
                        ).build()));
                break;
        }
        System.out.println(actionModel);
        //return new TextMessage("ok");
    }

}
