package com.czw.spring.proxy;

/**
 * Created by zevi on 2017/7/14.
 */
public class GoodsServiceImpl implements IGoodsService{


    @Override
    public String getGoodsInfo(String goodsId) {
        System.out.println("GooodsServiceImpl.getGoodsInfo goodsId="+goodsId);
        return goodsId+":美食";
    }
}
