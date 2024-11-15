package com.prodata.order_app.controller;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("product-app")
public interface ProductRestConsumer {

}
