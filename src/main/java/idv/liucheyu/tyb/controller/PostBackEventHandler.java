package idv.liucheyu.tyb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import idv.liucheyu.tyb.annotation.PostBackComponent;
import idv.liucheyu.tyb.annotation.PostBackMethod;
import idv.liucheyu.tyb.model.PostBackModel;
import idv.liucheyu.tyb.support.PostBackActionSupporter;
import idv.liucheyu.tyb.support.PostBackHandlerMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@LineMessageHandler
public class PostBackEventHandler {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    LineMessagingClient lineMessagingClient;
    @Autowired
    PostBackActionSupporter postBackActionSupporter;


    @EventMapping
    public void postBackEvent(PostbackEvent event) throws JsonProcessingException, InvocationTargetException, IllegalAccessException {
        PostBackModel postBackModel = objectMapper.readValue(event.getPostbackContent().getData(), PostBackModel.class);
        lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), new TextMessage("got it")));

        Optional<PostBackHandlerMethod> methodOp = postBackActionSupporter.postBackMethods.stream()
                .filter(postBackHandlerMethod -> postBackHandlerMethod.getPostBackPredicate()
                        .test(postBackModel)).findFirst();


        if (methodOp.isPresent()) {
            PostBackHandlerMethod postBackHandlerMethod = methodOp.get();
            postBackHandlerMethod.getMethod().invoke(postBackHandlerMethod.getObject(), postBackModel);
        } else {
            lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), new TextMessage("????????????????????????")));
        }


//        System.out.println(event.getPostbackContent().getData());
//        ActionModel actionModel = objectMapper.readValue(event.getPostbackContent().getData(), ActionModel.class);
//        ObjectNode objectNode = objectMapper.createObjectNode();
//        objectNode = objectNode.put("action", "payMethodType");
//        switch (actionModel.getData().getMethod()){
//            case "cash":
//                lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), new TextMessage("????????????")));
//                break;
//            case "credit":
//                lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), TemplateMessage.builder()
//                        .altText("???????????????")
//                        .template(ButtonsTemplate.builder()
//                                .title("?????????")
//                                .text("???????????????")
//                                .actions(Arrays.asList(
//                                        PostbackAction.builder()
//                                                .label("????????????")
//                                                .text("????????????")
//                                                .data(objectNode.set("data", objectMapper.createObjectNode()
//                                                        .put("method", "credit")
//                                                        .put("cardName", "????????????")).toString())
//                                                .build(),
//                                        PostbackAction.builder()
//                                                .label("????????????")
//                                                .text("????????????")
//                                                .data(objectNode.set("data", objectMapper.createObjectNode()
//                                                        .put("method", "credit")
//                                                        .put("cardName", "????????????")).toString())
//                                                .build(),
//                                        PostbackAction.builder()
//                                                .label("????????????")
//                                                .text("????????????")
//                                                .data(objectNode.set("data", objectMapper.createObjectNode()
//                                                        .put("method", "credit")
//                                                        .put("cardName", "????????????")).toString())
//                                                .build()
//                                        )
//                                ).build()
//                        ).build()));
//                break;
//            case "icard":
//                lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), TemplateMessage.builder()
//                        .altText("??????????????????")
//                        .template(ButtonsTemplate.builder()
//                                .title("????????????")
//                                .text("??????????????????")
//                                .actions(Arrays.asList(
//                                        PostbackAction.builder()
//                                                .label("?????????")
//                                                .text("?????????")
//                                                .data(objectNode.set("data", objectMapper.createObjectNode()
//                                                        .put("method", "icard")
//                                                        .put("cardName", "?????????")).toString())
//                                                .build(),
//                                        PostbackAction.builder()
//                                                .label("?????????")
//                                                .text("?????????")
//                                                .data(objectNode.set("data", objectMapper.createObjectNode()
//                                                        .put("method", "icard")
//                                                        .put("cardName", "?????????")).toString())
//                                                .build()
//                                        )
//                                ).build()
//                        ).build()));
//                break;
//        }
    }

}
