package com.example.boot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* <p>
* BookBorrow实体类
* </p>
*
* @author 
*/
@Getter
@Setter
@TableName("goods_exchange")
@ApiModel(value = "GoodsExchange对象", description = "")
public class GoodsExchange implements Serializable {

private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 图书id
    @ApiModelProperty("商品id")
    @Alias("商品id")
    private Integer goodsId;

    @TableField(exist = false)
    private String goodsImg;

    @TableField(exist = false)
    private String goodsName;

    // 兑换人id
    @ApiModelProperty("兑换人id")
    @Alias("兑换人id")
    private Integer userId;

    // 兑换人
    @ApiModelProperty("兑换人")
    @Alias("兑换人")
    private String username;

    // 兑换数量
    @ApiModelProperty("兑换数量")
    @Alias("兑换数量")
    private Integer num;

    // 兑换数量
    @ApiModelProperty("兑换日期")
    @Alias("兑换日期")
    private String date;

    @ApiModelProperty("扣除积分")
    @Alias("扣除积分")
    private Integer account;
}