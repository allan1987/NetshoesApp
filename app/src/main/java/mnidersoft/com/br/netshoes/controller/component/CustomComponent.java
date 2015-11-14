package mnidersoft.com.br.netshoes.controller.component;

import javax.inject.Singleton;

import dagger.Component;
import mnidersoft.com.br.netshoes.controller.module.CustomModule;
import mnidersoft.com.br.netshoes.service.RestClient;

@Singleton
@Component(modules = {CustomModule.class})
public interface CustomComponent {

    public RestClient provideRestClient();
}
