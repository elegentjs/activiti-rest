package com.thinvent.nj.activiti.conf;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.thinvent.nj.mybatis.conf.AbstractDubboConfig;
import com.thinvent.nj.uc.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dubbo
 * @author liupeijun
 */
@Configuration
public class DubboConfig extends AbstractDubboConfig {

    @Value("${dubbo.version}")
    private String version;

    @Bean
    public ReferenceBean<OrgService> r1() { return ref(OrgService.class, version); }
    @Bean
    public ReferenceBean<UserService> r2() { return ref(UserService.class, version); }
    @Bean
    public ReferenceBean<RoleService> r3() { return ref(RoleService.class, version); }
    @Bean
    public ReferenceBean<ResourceService> r4() { return ref(ResourceService.class, version); }
    @Bean
    public ReferenceBean<ServerService> r5() { return ref(ServerService.class, version); }
    @Bean
    public ReferenceBean<DictService> r6() { return ref(DictService.class, version); }
    @Bean
    public ReferenceBean<ScheduleJobService> r7() { return ref(ScheduleJobService.class, version); }
    @Bean
    public ReferenceBean<FileService> r8() { return ref(FileService.class, version); }
}
