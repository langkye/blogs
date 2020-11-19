package com.langkye.service.impl;

import com.langkye.entity.Blog;
import com.langkye.mapper.BlogMapper;
import com.langkye.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Langkye
 * @since 2020-11-20
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
