package com.mobo.mobosubway.source;



import com.alibaba.fastjson.JSON;
import com.mobo.mobosubway.data.SubwayDataCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Order(2)
@Slf4j
public class AmapQueryService implements SubwaySourceQueryService{

    private final String url = "http://map.amap.com/service/subway?_1469083453978&srhdata=1100_drw_beijing.json";
    WebClient webClient = WebClient.create();

    @Override
    public SubwayDataCollection parse() {
        Mono<String> stringMono = webClient.get().uri(url).retrieve()
                .bodyToMono(String.class);
        stringMono.subscribe(resp -> {
            Map<String, Object> json = (Map<String, Object>) JSON.parse(resp);
            System.out.println(json);
        });
        return null;
    }
}
