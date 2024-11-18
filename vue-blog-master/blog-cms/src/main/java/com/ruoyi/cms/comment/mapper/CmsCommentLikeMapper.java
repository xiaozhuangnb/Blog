package com.ruoyi.cms.comment.mapper;

import com.ruoyi.cms.comment.domain.CmsCommentLike;

import java.util.List;

public interface CmsCommentLikeMapper {
    /**
     * 查询列表
     */
    public List<CmsCommentLike> selectCmsCommentLikeList(CmsCommentLike cmsCommentLike);
    /**
     * 新增
     */
    public int addCmsCommentLike(CmsCommentLike cmsCommentLike);
    /**
     * 删除关联
     */
    public int deleteCmsCommentLike(CmsCommentLike cmsCommentLike);
}
