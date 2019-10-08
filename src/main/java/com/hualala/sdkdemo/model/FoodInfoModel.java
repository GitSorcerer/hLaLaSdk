package com.hualala.sdkdemo.model;

import lombok.Data;

import java.util.List;

/**
 * @author gaoh
 * @version 1.0
 * @date 2019/10/8 19:33
 * --foodInfo菜品信息字段列表：
 */
@Data
public class FoodInfoModel {
    private Boolean isRecommend;//是/否是推荐菜
    private String giftValidPeriod;
    private String salesCount;
    private Boolean isAutoAdd;
    private Boolean isDiscount;
    private String recentClickAmount;
    private Boolean isActive;
    private String initClickAmount;
    private String foodSortIndex;
    private Boolean isBatching;
    private String specialPrice5;
    private String specialPrice4;
    private String foodName;
    private String specialPrice6;
    private String minOrderCount;
    private String specialPrice3;
    private String specialPrice2;
    private String onlineWmPrice;
    private Boolean makingMethodIsRequired;//作法是/否必选项 0：不限 1：必选
    private Boolean makingMethodIsMultiple;//作法是/否多选项 0：不是 1：是
    private String foodCategoryKey;
    private Boolean isNeedConfirmFoodNumber;
    private String salesCommission;
    private String onlineZtPrice;
    private String foodSubjectName;
    private Boolean isShowInEBook;
    private String imageHWP;
    private Boolean isSingleSale;
    private String foodType;
    private String foodID;
    private String groupID;
    private String takeawayTag;
    private String parentFoodID;
    private String batchingIsFoodNumberRate;
    private String batchingFoodCategoryID;
    private String taxRate;
    private String foodCategoryID;
    private Boolean isOpen;
    private String foodSubjectKey;
    private String wdPrice;
    private String shopID;
    private Boolean isNews;
    private String starLevel;
    private String incrementUnit;
    private String foodSubjectCode;
    private Boolean tasteIsRequired;
    private Boolean tasteIsMultiple;//口味是/否多选
    private String sortIndex;
    private String materialFor3DHWP;
    private String hotTag;
    private Boolean isHasImage;
 /*   private String foodID;//菜品ID
    private String foodKey;// 菜品Key
    private String groupID;// 集团ID
    private String shopID;// 店铺ID
    private String foodCategoryID;// 分类ID
    private String foodCategoryKey;// 分类Key
    private String foodCategoryName;// 分类的名称
    private Boolean isBatching;// 是/否是配料，false：不是 true：是
    private Boolean isSingleSale;// 如果是配料,这个配料是否允许单点(true:允许，false:不允许)
    private Boolean foodSubjectKey;// 此菜品关联的收入科目key，例如冷菜收入、热菜收入、酒水收入
    private String departmentKeyLst;// 对应出品部门Key列表
    private String batchingFoodCategoryKey;// 配菜的分类key,在这里选择配菜
    private String foodName;// 菜品名称
    private String foodCode;// 菜品编号 可用于与三方菜品数据做映射
    private String foodAliasName;// 菜品别名
    private String foodMnemonicCode;// 菜品注记码
    private Boolean isDiscount;// 是/否打折
    private Boolean isComments;// 是/否评论
    private Boolean isActive;// 是/否启用
    private Boolean isOpen;// 是/否对用户开放
    private Boolean isSetFood;// 是/否是套餐
    private String setFoodDetailJson;// 套餐明细
    private Boolean isTempFood;// 是/否临时菜
//    private String units;// [unit]	规格
    private String py;// 菜品拼音
    private String description;// 描述
    private String minOrderCount;// 起售份数
    private String tasteList;// 口味，多个间逗号分隔（英文逗号）
    private String makingMethodList;// 作法，多个间用逗号隔开（如果菜品设置了做法，则在点菜时必须选择做法）
    private Boolean isSpecialty;// 是/否店家招牌菜
    private Boolean isRecommend;// 是/否是推荐菜
    private Boolean isNews;// 是/否是新品
    private String hotTag;// 辣度,0-3递增,(不辣,微辣,中辣,重辣)
    private String salesCount;// 最近售卖的数量
    private Boolean isNeedConfirmFoodNumber;// 是/否需要确认点菜
    private String takeoutPackagingFee;// 打包费
    private String incrementUnit;// 最小增加的单元,例如加半分
    private String takeawayTag;// 外卖表示, 0：不外送 1：可外送2：仅外送 （默认为 1可外送）
    private String workingLunchTag;// 工作餐标记 0：非工作餐 1：可做工作餐 2：仅做工作餐 （默认为0，不能做工作餐）
    private String adsID;// 详细介绍页面的ID
    private String imgePath;// 菜品图片地址，路径前加http://res.hualala.com
    private Boolean isHasImage;// 是否有图片
    private String imageHWP;// 图片的高宽比例
    private String starLevel;// 星级
    private String foodTagIDs;// 菜品标签ID
    private String parentFoodID;// 父菜品ID（依赖菜品ID）
    private String foodEnName;// 英文名
    private Boolean isAutoAdd;// 是/否默认自动加入
    private Boolean isCanRefund;// 是/否支持退订退款，0：不支持 1：支持（默认）
    private String setPerson;// 套餐标准用餐人数
    private Boolean tasteIsRequired;// 口味是/否必选项 0：不限 1：必选
    private Boolean tasteIsMultiple;// 口味是/否多选
    private Boolean makingMethodIsRequired;// 作法是/否必选项 0：不限 1：必选
    private Boolean makingMethodIsMultiple;// 作法是/否多选项 0：不是 1：是
    private String initClickAmount;// 初始点击
    private String actualClickAmount;// 实际点击数量
    private String recentClickAmount;// 最近点击
    private String clickAlertMess;// 点菜的时候提醒
    private String sourceFoodID;// 源菜品Id
    private String salesCommission;// 销售提成
    private String foodKeyElementLst;// 菜品主料名称列表 多个间用英文逗号隔开，例如： 青椒,土豆
    private String sortIndex;// 菜品类型排序
    private String foodSortIndex;// 菜品排序
    private String foodSubjectName;// 科目名称
    private String foodSubjectCode;// 科目code
    private String departmentID;// 出品部门ID
    private String departmentKey;// 出品部门key
    private String material_2DUrl;// 2D素材路径，菜品电子菜谱2D图片路径
    private String material_2DHWP;// 2D素材宽高比（视频默认为0）
    private String material_3DUrl;// 3D素材路径，菜品电子菜谱3D图片路径
    private String material_3DHWP;// 3D素材宽高比（视频默认为0）
    private Boolean isShowInEBook;// 是/否在电子菜谱中显示 0：不显示 1：显示
    private String EBookVersionNo;// 电子菜谱版本号，当出现以下情况时，更新此版本号 1、菜品的2D、3D素材发生变化时；2、电子菜谱设置发生变化时（包括封面图、设备编号、菜品分类布局等）
    private String batchingFoodCategoryID;// 配料分类Key
    private String batchingFoodTagID;// 配料标签ID ，关于配菜的关联，这个接口还会返回FoodTag列表，可以通过batchingFoodTagID这个字段关联FoodTag里面的itemID 得到foodIDs，foodIDs存的是这个主菜所有配菜foodID
    private String taxRate;// 菜品税率
    private String supportFood;// 配菜数量，例：1-8 以-为间隔，最小值和最大值均可为空
    private String assistUnit;// 辅助规格
    private String popularity;// 菜品热度
    private String detailSplit;// 多份套餐时明细拆分 0：不拆分 1：拆分 默认不拆分
    private String batchingIsFoodNumberRate;// 配料与菜品数量是否成倍关系 0：不（烤鱼模式） 1：是（默认）（奶茶模式）
    private String foodCategoryCode;// 分类编号
    private String foodCategoryEnName;// 分类英文名
    private String foodCategoryGroupName;// 分类分组名称
    private String specialPrice;// 特价
    private String specialPrice2;// 特价2
    private String specialPrice3;// 特价3
    private String specialPrice4;// 特价4
    private String specialPrice5;// 特价5
    private String specialPrice6;// 特价6
    private String tagIDs;// 标签ids
    private String tagNames;// 标签名称
    private String giftItemID;// 礼品券ID
    private String giftValidPeriod;// 礼品券有效期
    private String materialFor3DHWP;*/

    private List<UnitModel> units;
}
