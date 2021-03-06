package idv.liucheyu.tyb.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonTypeName("payMethod")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayModel implements PostBackModel{
    private String method;
    private Double amount;

}
