package idv.liucheyu.tyb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionModel {
    private String action;
    private PayModel data;

}
