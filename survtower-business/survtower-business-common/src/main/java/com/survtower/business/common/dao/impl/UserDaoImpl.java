package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.UserDao;
import com.survtower.business.common.domain.User;
import com.survtower.business.common.repository.UserRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class UserDaoImpl implements UserDao{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
       return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
       return userRepository.findAll();
    }

    @Override
    public User find(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUuid(String uuid) {
       return userRepository.findByUuid(uuid);
    }

    @Override
    public List<User> findUsersUpdatedAfter(Date afterDate) {
        return userRepository.findUsersUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
       return userRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<User> findUsersUpdatedAfter(Date afterDate, Date maxDate) {
        return userRepository.findUsersUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return userRepository.findMaximumUpdateDate();
    }

    @Override
    public List<User> findUsersUpdatedBefore(Date maxDate) {
      return userRepository.findUsersUpdatedBefore(maxDate);
    }
    
}
