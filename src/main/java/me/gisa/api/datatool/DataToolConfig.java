package me.gisa.api.datatool;

import feign.Feign;
import feign.Retryer;
import me.gisa.api.datatool.rss.RssClient;
import me.gisa.api.datatool.sisemi.SisemeClient;
import me.gisa.api.datatool.naver.NaverClient;
import me.gisa.api.datatool.sisemi.SisemeClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients
@Configuration
public class DataToolConfig {
    @Bean
    public SisemeClient sisemeClient() {
        return Feign.builder()
                    .contract(new SpringMvcContract())
                    .retryer(new Retryer.Default())
                    .target(SisemeClient.class, "api-client");
    }

    @Bean
    public NaverClient naverClient() {
        return Feign.builder()
                    .contract(new SpringMvcContract())
                    .retryer(new Retryer.Default())
                    .target(NaverClient.class, "naver-client");
    }
    @Bean
    public RssClient rssClient() {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .retryer(new Retryer.Default())
                .target(RssClient.class, "rss-client");
    }
}
