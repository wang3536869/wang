package com.wjs.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Wjs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("book")
public class Book {
    private Integer id;
    private String name;
    private BigDecimal price;
    private  String author;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    private String cover;
}
