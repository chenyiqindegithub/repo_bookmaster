package com.example.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import cn.hutool.core.annotation.Alias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
* <p>
* Book实体类
* </p>
*
* @author 
*/
@Getter
@Setter
@ApiModel(value = "Book对象", description = "")
public class Book implements Serializable {

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

    // 简介
    @ApiModelProperty("简介")
    @Alias("简介")
    private String descr;

    // 价格
    @ApiModelProperty("价格")
    @Alias("价格")
    private String price;

    // 封面
    @ApiModelProperty("封面")
    @Alias("封面")
    private String img;

    // 作者
    @ApiModelProperty("作者")
    @Alias("作者")
    private String author;

    // 出版日期
    @ApiModelProperty("出版日期")
    @Alias("出版日期")
    private String date;

    // 出版社
    @ApiModelProperty("出版社")
    @Alias("出版社")
    private String publisher;

    // 数量
    @ApiModelProperty("数量")
    @Alias("数量")
    private Integer store;

    private Boolean active;
}