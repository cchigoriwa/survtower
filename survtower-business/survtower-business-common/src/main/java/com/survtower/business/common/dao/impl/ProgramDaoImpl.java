package com.survtower.business.common.dao.impl;

import com.survtower.business.common.dao.ProgramDao;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.repository.ProgramRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Charles Chigoriwa
 */
@Repository
public class ProgramDaoImpl implements ProgramDao{
    
    @Autowired
    private ProgramRepository programRepository;

    @Override
    public Program save(Program program) {
       return programRepository.save(program);
    }

    @Override
    public List<Program> findAll() {
       return programRepository.findAll();
    }

    @Override
    public Program find(Long id) {
        return programRepository.findOne(id);
    }

    @Override
    public Program findByUuid(String uuid) {
       return programRepository.findByUuid(uuid);
    }

    @Override
    public List<Program> findProgramsUpdatedAfter(Date afterDate) {
        return programRepository.findProgramsUpdatedAfter(afterDate);
    }

    @Override
    public Date findMaximumUpdateDate(Date afterDate) {
       return programRepository.findMaximumUpdateDate(afterDate);
    }

    @Override
    public List<Program> findProgramsUpdatedAfter(Date afterDate, Date maxDate) {
        return programRepository.findProgramsUpdatedAfter(afterDate, maxDate);
    }

    @Override
    public Date findMaximumUpdateDate() {
        return programRepository.findMaximumUpdateDate();
    }

    @Override
    public List<Program> findProgramsUpdatedBefore(Date maxDate) {
      return programRepository.findProgramsUpdatedBefore(maxDate);
    }
    
}
