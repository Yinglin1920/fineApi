package com.example.demo.common;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/7 10:41
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageListDto<T> {

    /**
     * 第几页
     */
    private int pageNo;
    /**
     * 总页数
     */
    private int pageSize;
    /**
     * 总条数
     */
    private BigDecimal totalCount;
    /**
     * 总分页数
     */
    private int totalPage;
    /**
     * 是否有上一页
     */
    private boolean hasPreviousPage;
    /**
     * 是否有下一页
     */
    private boolean hasNextPage;
    /**
     * 扩展字段：汇总信息等
     */
    private Object extraData;
    /**
     * 页数据
     */
    private List<T> data = null;

    public PageListDto(List<T> list) {
        PageInfo pageInfo = new PageInfo<>(list);
        this.pageNo = pageInfo.getPageNum();
        this.pageSize=pageInfo.getPageSize();
        this.data=pageInfo.getList();
        this.hasNextPage=pageInfo.isHasNextPage();
        this.hasPreviousPage=pageInfo.isHasPreviousPage();
        this.totalCount=new BigDecimal(pageInfo.getTotal());
        this.totalPage=pageInfo.getPages();

    }
}
