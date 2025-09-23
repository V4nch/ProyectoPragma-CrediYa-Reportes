package co.com.pragma.powerup.api;

import co.com.pragma.powerup.model.report.utils.Constants;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @RouterOperations({
    @RouterOperation(
            path = Constants.PATH_REPORT,
            method = {RequestMethod.GET},
            produces = { Constants.CONTENT_TYPE },
            beanClass = Handler.class,
            beanMethod = Constants.GET_NAME_FUNCTION
    )})
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET(Constants.PATH_REPORT), handler::getReport);
    }
}
