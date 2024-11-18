package com.ruoyi.cms.fileInfo.service.impl;


import com.ruoyi.cms.fileInfo.domain.FileBlogInfo;
import com.ruoyi.cms.fileInfo.mapper.FileBlogInfoMapper;
import com.ruoyi.cms.fileInfo.service.IFileBlogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FileBlogInfoServiceImpl implements IFileBlogInfoService {

    @Autowired
    private FileBlogInfoMapper fileBlogInfoMapper;

    @Override
    public int batchFileBlog(List<FileBlogInfo> fileBlogList) {
        return fileBlogInfoMapper.batchFileBlog(fileBlogList);
    }

    @Override
    public int deleteFileBlogByBlogId(Long blogId) {
        return fileBlogInfoMapper.deleteFileBlogByBlogId(blogId);
    }

    @Override
    public int deleteFileBlog(Long[] ids) {
        return fileBlogInfoMapper.deleteFileBlog(ids);
    }

    @Override
    public List<FileBlogInfo> selectFileBlogList(Long blogId) {
        return fileBlogInfoMapper.selectFileBlogList(blogId);
    }
}
