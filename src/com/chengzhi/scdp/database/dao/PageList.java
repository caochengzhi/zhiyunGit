package com.chengzhi.scdp.database.dao;

import java.util.List;

import com.chengzhi.scdp.database.dao.AbstractModel;

/**
 * 分页结果集对象
 * @author beisi
 *
 * @param <T>
 */
public class PageList<T extends AbstractModel> {
	 
    private int page;
    private int rows;
    private int total;
    private List<T> list;
 
    public PageList(int page, int rows, int total, List<T> list) {
        this.page = page;
        this.rows = rows;
        this.total = total;
        this.list = list;
    }
 
    public int getPage() {
        return page;
    }
 
    public void setPage(int page) {
        this.page = page;
    }
 
    public int getRows() {
        return rows;
    }
 
    public void setRows(int rows) {
        this.rows = rows;
    }
 
    public List<T> getList() {
        return list;
    }
 
    public void setList(List<T> list) {
        this.list = list;
    }
 
    public int size(){
        return null==list?0:list.size();
    }
 
    public int getTotal() {
        return total;
    }
 
    public void setTotal(int total) {
        this.total = total;
    }
}
