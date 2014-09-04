package com.survtower.business.common.service.impl;

import com.survtower.business.common.dao.ProgramDao;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.ProgramService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("programService")
@Transactional(readOnly = true)
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramDao programDao;

    @Transactional
    @Override
    public Program save(Program program) {
        program.setUpdateDate(new Date());
        return programDao.save(program);
    }

    @Override
    public List<Program> findAll() {
        return programDao.findAll();
    }

    @Override
    public Program find(Long id) {
        return programDao.find(id);
    }

    @Override
    public Program findByUuid(String uuid) {
        return programDao.findByUuid(uuid);
    }

    @Override
    public List<Program> findProgramsUpdatedAfter(Date afterDate) {
        return programDao.findProgramsUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
        return programDao.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Program> findProgramsUpdatedAfter(Date afterDate, Date maxDate) {
        return programDao.findProgramsUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return programDao.findMaximumUpdateDate();
    }

    @Override
    public List<Program> findProgramsUpdatedBefore(Date maxDate) {
        return programDao.findProgramsUpdatedBefore(maxDate);
    }
}
