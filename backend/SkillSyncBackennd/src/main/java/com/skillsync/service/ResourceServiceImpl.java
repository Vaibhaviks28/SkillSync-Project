package com.skillsync.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillsync.model.Resource;
import com.skillsync.repository.ResourceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

	@Autowired
    private ResourceRepository resourceRepository;

    @Override
    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public List<Resource> getResourcesBySkillId(Long skillId) {
        return resourceRepository.findBySkillId(skillId);
    }

    @Override
    public Optional<Resource> getResourceById(Long id) {
        return resourceRepository.findById(id);
    }

    @Override
    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }
}
