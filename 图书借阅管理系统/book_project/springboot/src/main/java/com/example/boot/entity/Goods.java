package com.example.boot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* <p>
* Book实体类
* </p>
*
* @author 
*/
@Getter
@Setter
@ApiModel(value = "Good对象", description = "")
public class Goods implements Serializable {

private static final long serialVersionUID = 1L;

    // id
    @ApiModelProperty("id")
    @Alias("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 名称
    @ApiModelProperty("名称")
    @Alias("名称")
    private String name;

    // 价格
    @ApiModelProperty("价格")
    @Alias("价格")
    private Integer price;

    // 封面
    @ApiModelProperty("封面")
    @Alias("封面")
    private String img;

    // 数量
    @ApiModelProperty("数量")
    @Alias("数量")
    private Integer store;

    private Boolean active;
}