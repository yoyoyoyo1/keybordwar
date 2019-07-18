package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Follow;
import com.oracle.demo.respository.FollowDao;
import com.oracle.demo.service.FollowService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

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
    public List<Integer> followMeList(int id)
    {
        return followDao.followMeList(id);
    }
    public List<Integer> followingList(int id)
    {
        return followDao.followingList(id);
    }
    public Follow isFollow(int follower,int following)
    {
        return followDao.isFollow(follower,following);
    }
    public Follow doFollow(Follow follow)
    {
        return followDao.save(follow);
    }
    public int undoFollow(int follower, int following) {
        return followDao.undoFollow(follower,following);
    }
}
