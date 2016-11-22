package com.timestudio.usedshop.network;

/**
 * @description 此接口中包含了易淘的所有网络连接路径
 */
public interface ShopApi {

    /*易淘总路径*/
    String BASE_URL = "http://wx.feicuiedu.com:9094/yitao/";
    /*图片的基路径*/
    String IMAGE_URL = "http://wx.feicuiedu.com:9094/";
    /*登录路径*/
    String LOGIN = "UserWeb?method=login";
    /*注册路径*/
    String REGISTER = "UserWeb?method=register";
    /*更新路径*/
    String UPDATE = "UserWeb?method=update";
    /*获取名字路径*/
    String GET_NAMES = "UserWeb?method=getNames";
    /*获取用户路径*/
    String GET_USER = "UserWeb?method=getUsers";

    /*获取所有商品路径*/
    String ALL_GOODS = "GoodsServlet?method=getAll";
    /*添加商品路径*/
    String ADD = "GoodsServlet?method=add";
    /*商品详情路径*/
    String DETAIL = "GoodsServlet?method=view";
    /*删除商品路径*/
    String DELETE = "GoodsServlet?method=delete";
}
