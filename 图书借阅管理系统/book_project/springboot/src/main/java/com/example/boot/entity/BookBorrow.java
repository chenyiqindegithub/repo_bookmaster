package com.example.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import cn.hutool.core.annotation.Alias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
* <p>
* BookBorrow实体类
* </p>
*
* @author 
*/
@Getter
@Setter
@TableName("book_borrow")
@ApiModel(value = "BookBorrow对象", description = "")
public class BookBorrow implements Serializable {

private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 图书id
    @ApiModelProperty("图书id")
    @Alias("图书id")
    private Integer bookId;

    @TableField(exist = false)
    private String bookImg;

    @TableField(exist = false)
    private String bookName;

    // 借书人id
    @ApiModelProperty("借书人id")
    @Alias("借书人id")
    private Integer userId;

    // 借书人
    @ApiModelProperty("借书人")
    @Alias("借书人")
    private String username;

    // 借书数量
    @ApiModelProperty("借书数量")
    @Alias("借书数量")
    private Integer num;

    // 借书数量
    @ApiModelProperty("借书数量")
    @Alias("借书数量")
    private String date;

    // 是否归还
    @ApiModelProperty("是否归还")
    @Alias("是否归还")
    private String back;

    // 还书日期
    @ApiModelProperty("还书日期")
    @Alias("还书日期")
    private String backdate;

    // 审核
    private String status;
    private String reason;
}