package idv.liucheyu.tyb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeesageService {

    @Autowired
    ObjectMapper objectMapper;

    public Message getPayMethodQuetsionMeesage(String price) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode = objectNode.put("action", "payMethod");
        return TemplateMessage.builder()
                .altText("記帳方式")
                .template(ButtonsTemplate.builder()
                        .title("記帳")
                        .text("記帳方式")
                        .actions(Arrays.asList(
                                PostbackAction.builder()
                                        .label("現金")
                                        .text("現金")
                                        .data(objectNode
                                                .put("method", "cash")
                                                .put("amount", price).toString())
                                        .build(),
                                PostbackAction.builder()
                                        .label("信用卡")
                                        .text("信用卡")
                                        .data(objectNode
                                                .put("method", "credit")
                                                .put("amount", price).toString())
                                        .build(),
                                PostbackAction.builder()
                                        .label("電子票證")
                                        .text("電子票證")
                                        .data(objectNode
                                                .put("method", "icard")
                                                .put("amount", price).toString())
                                        .build()
                                )
                        ).build()
                ).build();
    }

    public Message getBankSelectMessage(List<String> bankCardNames) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode = objectNode.put("action", "bankCardSelect");
        ObjectNode finalObjectNode = objectNode;
        return TemplateMessage.builder()
                .altText("請選擇銀行")
                .template(ButtonsTemplate.builder()
                        .title("銀行")
                        .text("選擇銀行卡")
                        .actions(bankCardNames.stream().map(b -> PostbackAction.builder()
                                .label(b)
                                .text(b)
                                .data(finalObjectNode
                                        .put("bankCardName", b).toString())
                                .build()).collect(Collectors.toList())
                        ).build()

                ).build();
    }

    public Message getDefaultMessage() {
        StringBuffer sb = new StringBuffer();
        sb.append("1.記帳請輸入[#金額]（ex:#85").append("\n").append("2.報表請輸入[#報表]").append("\n");
        return new TextMessage(sb.toString());
    }
}
