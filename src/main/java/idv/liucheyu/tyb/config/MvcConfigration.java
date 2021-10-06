package idv.liucheyu.tyb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"idv.liucheyu.tyb.controller", "idv.liucheyu.tyb.support", "idv.liucheyu.tyb.service"})
public class MvcConfigration {

}
