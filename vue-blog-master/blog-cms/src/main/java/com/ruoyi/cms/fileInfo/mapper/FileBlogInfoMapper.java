package com.ruoyi.cms.fileInfo.mapper;

import com.ruoyi.cms.fileInfo.domain.FileBlogInfo;

import java.util.List;

/**
 * @Description: 〈blog文件关联 数据层〉
 */
public interface FileBlogInfoMapper {
    /**
     * 批量新增
     */
    public int batchFileBlog(List<FileBlogInfo> fileBlogList);
    /**
     * 通过blogID删除blog文件关联
     */
    public int deleteFileBlogByBlogId(Long blogId);
    /**
     * 批量删除blog文件关联
     */
    public int deleteFileBlog(Long[] ids);
    /**
     * 查询文件列表
     */
    public List<FileBlogInfo> selectFileBlogList(Long blogId);
}
