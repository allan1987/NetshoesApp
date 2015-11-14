package mnidersoft.com.br.netshoes.controller.module;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mnidersoft.com.br.netshoes.Constant;
import mnidersoft.com.br.netshoes.service.RestClient;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

@Module
public class CustomModule {

    @Provides
    @Singleton
    RestClient provideRestClient() {
        Executor executor = Executors.newCachedThreadPool();
        return new RestAdapter.Builder()
                .setExecutors(executor, executor)
                .setConverter(new JacksonConverter())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Constant.ROOT_URL)
                .build()
                .create(RestClient.class);
    }
}
