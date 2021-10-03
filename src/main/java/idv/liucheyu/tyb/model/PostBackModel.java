package idv.liucheyu.tyb.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonSubTypes({
        @JsonSubTypes.Type(value = PayModel.class, name = "payMethod"),
        @JsonSubTypes.Type(value = BankModel.class, name = "bankMethod")
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property="action",
        visible = true)
public interface PostBackModel {

}
