package com.oracle.demo.service.impl;

import com.oracle.demo.respository.FollowDao;
import com.oracle.demo.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    FollowDao followDao;
    @Override
    public int showFollowing(int id)
    {   System.out.println(id+"----查询关注信息----");
        return followDao.showFollowing(id);
    }
    public int showFollower(int id)
    {
        return followDao.showFollower(id);
    }

}
