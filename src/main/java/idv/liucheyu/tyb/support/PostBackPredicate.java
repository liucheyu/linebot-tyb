package idv.liucheyu.tyb.support;

import idv.liucheyu.tyb.model.PostBackModel;

import java.lang.reflect.Type;
import java.util.function.Predicate;

public class PostBackPredicate implements Predicate<PostBackModel> {
    private final Class<?> supportModel;

    public PostBackPredicate(Type modelType) {
        this.supportModel = (Class<? extends PostBackModel>)modelType;
    }

    @Override
    public boolean test(PostBackModel model) {
        if (supportModel.isAssignableFrom(model.getClass())) {
            return true;
        }
        return false;
    }
}
