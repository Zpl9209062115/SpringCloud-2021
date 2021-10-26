package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    PaymentService paymentService;


    @GetMapping("/payment/{id}")
    public CommonResult find(@PathVariable("id") long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment != null){
            return new CommonResult(200,"查询成功,serverPort" + serverPort,payment);
        }else{
            return new CommonResult(444,"查询失败,serverPort" + serverPort,null);
        }
    }

    @PostMapping("/payment/create")
    public CommonResult add(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if (result > 0){
            return new CommonResult(200,"插入数据库成功,serverPort" + serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败,serverPort" + serverPort,null);
        }
    }

}
