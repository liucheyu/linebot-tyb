package idv.liucheyu.tyb.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeName("bankMethod")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankModel implements PostBackModel{
    private String bankName;

}
