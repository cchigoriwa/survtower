package com.survtower.business.member.config;

import com.survtower.business.member.integration.SurveillanceIntegrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerBean;

/**
 *
 * @author Charles Chigoriwa
 */
@Configuration
public class DynamicSchedulerConfiguration {
    
    @Autowired
    private SurveillanceIntegrator surveillanceIntegrator;
    
    @Bean
    public MethodInvokingJobDetailFactoryBean createMethodInvokingJobDetailFactoryBean(){
        MethodInvokingJobDetailFactoryBean bean=new MethodInvokingJobDetailFactoryBean();
        bean.setTargetObject(surveillanceIntegrator);
        bean.setTargetMethod("push");
        bean.setConcurrent(false);
        return bean;
    }
    
    @Bean
    public SimpleTriggerBean createSimpleTriggerBean(){
        SimpleTriggerBean bean=new SimpleTriggerBean();
        bean.setJobDetail(createMethodInvokingJobDetailFactoryBean().getObject());
        bean.setStartDelay(15000);
        bean.setRepeatInterval(50000);
        return bean;
    }
    
    @Bean
    public SchedulerFactoryBean createSchedulerFactoryBean(){
        SchedulerFactoryBean bean=new SchedulerFactoryBean();
        bean.setTriggers(createSimpleTriggerBean());      
        return bean;
    }
    
}
