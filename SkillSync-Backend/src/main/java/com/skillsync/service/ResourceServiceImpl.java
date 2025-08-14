package com.skillsync.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skillsync.exception.ResourceNotFound;
import com.skillsync.model.Resource;
import com.skillsync.model.Skill;
import com.skillsync.repository.ResourceRepository;
import com.skillsync.repository.SkillRepository;
import com.skillsync.repository.UserSkillRepository;

import jakarta.transaction.Transactional;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserSkillRepository userSkillRepository;

    @Transactional
    public Resource createResource(String title, String type, String url,
                                   Long skillId, String folderName) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type is required");
        }
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL is required");
        }
        if (skillId == null) {
            throw new IllegalArgumentException("Skill ID is required");
        }

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));

        Resource resource = new Resource();
        resource.setTitle(title);
        resource.setType(type);
        resource.setUrl(url);
        resource.setSkill(skill);

        if (folderName != null) {
            resource.setFolderName(folderName);
        }

        return resourceRepository.save(resource);
    }

    @Override
    public List<Resource> getResourcesByFolderName(String folderName) {
        return resourceRepository.findByFolderNameIgnoreCase(folderName);
    }

    @Override
    public Optional<Resource> getResourceById(Long id) {
        return resourceRepository.findById(id);
    }

    @Override
    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return resourceRepository.existsById(id);
    }

    @Override
    public boolean existsByTitleAndUrl(String title, String url) {
        return resourceRepository.existsByTitleAndUrl(title, url);
    }

    @Override
    public List<Resource> getResourcesBySkillId(Long skillId) {
        return resourceRepository.findBySkillId(skillId);
    }

    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public Resource storeFile(MultipartFile file, String folderName, Long skillId) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String uploadDir = "uploads/resources/";
        String originalFilename = file.getOriginalFilename();
        String storedFilename = System.currentTimeMillis() + "_" + originalFilename;

        Path path = Paths.get(uploadDir + storedFilename);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));

        Resource resource = new Resource();
        resource.setTitle(originalFilename);
        resource.setType(file.getContentType());
        resource.setUrl("/files/" + storedFilename);
        resource.setFolderName(folderName);
        resource.setSkill(skill);

        return resourceRepository.save(resource);
    }

    @Override
    public Resource markResourceCompleted(Long resourceId) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFound("Resource not found with id: " + resourceId));

        // Step 1 → mark resource complete
        resource.setIsCompleted(true);
        Resource updated = resourceRepository.save(resource);

        // Step 2 → calculate updated progress %
        Long skillId = resource.getSkill().getId();
        long total = resourceRepository.countBySkillId(skillId);
        long completed = resourceRepository.countBySkillIdAndIsCompletedTrue(skillId);

        int percentValue = (int) ((completed * 100.0) / total);  // e.g. 60%

        // Step 3 → update in UserSkill (expects Float, so use (float) cast)
        userSkillRepository.findBySkillId(skillId).forEach(us -> {
            us.setProgressPercentage((float) percentValue);  // ✅ cast to float
            userSkillRepository.save(us);
        });

        return updated;
    }

}
