package idv.liucheyu.tyb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayModel {
    private String method;
    private Double amount;
}
