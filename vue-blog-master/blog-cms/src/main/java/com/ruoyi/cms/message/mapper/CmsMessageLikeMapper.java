package com.ruoyi.cms.message.mapper;

import com.ruoyi.cms.message.domain.CmsMessageLike;

import java.util.List;

/**
 * @Description: 〈数据层〉
 */
public interface CmsMessageLikeMapper {
    /**
     * 查询列表
     */
    public List<CmsMessageLike> selectCmsMessageLikeList(CmsMessageLike cmsMessageLike);
    /**
     * 新增
     */
    public int addCmsMessageLike(CmsMessageLike cmsMessageLike);
    /**
     * 删除关联
     */
    public int deleteCmsMessageLike(CmsMessageLike cmsMessageLike);
}
