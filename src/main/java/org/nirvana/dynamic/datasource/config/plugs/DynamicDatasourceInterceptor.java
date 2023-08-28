package org.nirvana.dynamic.datasource.config.plugs;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.nirvana.dynamic.datasource.DynamicDataSourceType;
import org.nirvana.dynamic.datasource.config.selector.DynamicDataSourceSelector;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Component
@Intercepts(@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class DynamicDatasourceInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Object[] args = invocation.getArgs();
        log.info("method-name: {}, args: {}", method.getName(), args);
        DynamicDataSourceSelector.setDataSourceType(DynamicDataSourceType.READ);

        return invocation.proceed();
    }

}
