package com.example.boot.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.entity.Notice;
import com.example.boot.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {

    @Override
    public boolean save(Notice entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(Notice entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Notice entity) {
        return super.removeById(entity);
    }

    @Override
    public boolean removeBatchByIds(Collection<?> list) {
        return super.removeBatchByIds(list);
    }

    @Override
    public List<Notice> list() {
        return super.list();
    }

    @Override
    public Notice getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public <E extends IPage<Notice>> E page(E page, Wrapper<Notice> queryWrapper) {
        return super.page(page, queryWrapper);
    }

}
