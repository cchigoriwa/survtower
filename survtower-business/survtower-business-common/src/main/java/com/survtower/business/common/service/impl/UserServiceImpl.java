package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.UserDao;
import com.survtower.business.common.domain.User;
import com.survtower.business.common.service.UserService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public User save(User user) {
        user.setUpdateDate(new Date());
        return userDao.save(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User find(Long id) {
        return userDao.find(id);
    }

    @Override
    public User findByUuid(String uuid) {
        return userDao.findByUuid(uuid);
    }

    @Override
    public List<User> findUsersUpdatedAfter(Date afterDate) {
        return userDao.findUsersUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return userDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<User> findUsersUpdatedAfter(Date afterDate, Date maxDate) {
        return userDao.findUsersUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return userDao.findMaximumUpdateDate();
    }

    @Override
    public List<User> findUsersUpdatedBefore(Date maxDate) {
        return userDao.findUsersUpdatedBefore(maxDate);
    }
}
