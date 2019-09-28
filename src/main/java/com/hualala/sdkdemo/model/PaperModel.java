package com.hualala.sdkdemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文件实体类
 * </p>
 *
 * @author gaoh
 * @since 2019-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PaperModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String objId;//文件对象的Id 合同对象的Id或者UUid
    private String objType;//文件对象的类型  经营  外包
    private String fileName;//文件名称
    private String fileType;//文件类型(01 图片 02 PDF)
    private Integer fileSize;//文件大小  KB
    private String fileUrl;//文件路径  相对路径
    private String filePath;//所在文件夹
    private String suffixName;//后缀名 pdf jpg
    private Date createDate;//创建日期
    private String reserve1;//扩展字段1
    private String reserve2;//扩展字段2
    private String reserve3;//扩展字段3


}
