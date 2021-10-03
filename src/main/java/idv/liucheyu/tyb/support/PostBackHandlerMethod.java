package idv.liucheyu.tyb.support;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

@Data
@AllArgsConstructor
public class PostBackHandlerMethod {
    private Method method;
    private Object object;
    private PostBackPredicate postBackPredicate;

}
