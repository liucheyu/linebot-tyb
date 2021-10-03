package idv.liucheyu.tyb.support;

import idv.liucheyu.tyb.annotation.PostBackComponent;
import idv.liucheyu.tyb.annotation.PostBackMethod;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PostBackActionSupporter {

    private final ConfigurableApplicationContext appContext;
    public static List<PostBackHandlerMethod> postBackMethods;

    public PostBackActionSupporter(ConfigurableApplicationContext applicationContext) {
        appContext = applicationContext;

        appContext.addApplicationListener(applicationEvent -> {
            if (applicationEvent instanceof ContextRefreshedEvent) {
                Map<String, Object> beansWithAnnotation = appContext.getBeansWithAnnotation(PostBackComponent.class);
                postBackMethods = beansWithAnnotation.values()
                        .stream().flatMap((Object bean) -> {
                    Method[] uniqueDeclaredMethods = ReflectionUtils.getUniqueDeclaredMethods(bean.getClass());
                    return Arrays.stream(uniqueDeclaredMethods).filter(method -> {
                        PostBackMethod mergedAnnotation = AnnotatedElementUtils.getMergedAnnotation(method, PostBackMethod.class);
                        if (mergedAnnotation == null) {
                            return false;
                        }
                        if (method.getParameterCount() == 0) {
                            return false;
                        }

                        return true;
                    }).map(method -> {
                        Type type = method.getGenericParameterTypes()[0];
                        PostBackPredicate postBackPredicate = new PostBackPredicate(type);
                        return new PostBackHandlerMethod(method, bean, postBackPredicate);
                    });
                }).collect(Collectors.toList());

            }
        });
    }


}
