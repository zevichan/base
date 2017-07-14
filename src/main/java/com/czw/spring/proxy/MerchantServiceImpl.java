package com.czw.spring.proxy;

/**
 * Created by zevi on 2017/7/14.
 */
public class MerchantServiceImpl implements IMerchantService {
    @Override
    public String getMerchant(String merchantId) {
        System.out.println("MerchantServiceImpl.getMerchant merchantId=" + merchantId);
        return merchantId + ":麦当劳";
    }
}
