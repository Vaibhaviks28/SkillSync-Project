package com.skillsync.service;

import java.util.List;
import java.util.Optional;

import com.skillsync.model.Resource;

public interface ResourceService {
    Resource createResource(Resource resource);
    List<Resource> getResourcesBySkillId(Long skillId);
    Optional<Resource> getResourceById(Long id);
    void deleteResource(Long id);
}
