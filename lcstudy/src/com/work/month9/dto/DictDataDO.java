package com.work.month9.dto;

import java.io.Serializable;

/**
 *@author fjc
 *@title
 *@description
 *@date 2023/9/9 14:37
 *@version
 */
public class DictDataDO implements Serializable {


    private Long id;

    private Integer sort;

    private String label;

    private String value;

    private String dictType;

    private Integer status;

    private String colorType;

    private String cssClass;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public DictDataDO(Long id, Integer sort, String label, String value, String dictType, Integer status, String colorType, String cssClass, String remark) {
        this.id = id;
        this.sort = sort;
        this.label = label;
        this.value = value;
        this.dictType = dictType;
        this.status = status;
        this.colorType = colorType;
        this.cssClass = cssClass;
        this.remark = remark;
    }

    public DictDataDO() {
    }
}
