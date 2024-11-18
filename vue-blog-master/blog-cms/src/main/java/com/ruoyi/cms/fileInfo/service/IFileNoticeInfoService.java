package com.ruoyi.cms.fileInfo.service;

import com.ruoyi.cms.fileInfo.domain.FileNoticeInfo;

import java.util.List;

/**
 * @Description: 〈Service接口〉
 */
public interface IFileNoticeInfoService {
    /**
     * 批量新增
     */
    public int batchFileNotice(List<FileNoticeInfo> fileNoticeList);
    /**
     * 通过通知公告ID删除通知公告和文件关联
     */
    public int deleteFileNoticeByNoticeId(Long noticeId);
    /**
     * 批量删除通知公告和文件关联
     */
    public int deleteFileNotice(Long[] ids);
    /**
     * 查询文件列表
     */
    public List<FileNoticeInfo> selectFileNoticeList(Long noticeId);
}
