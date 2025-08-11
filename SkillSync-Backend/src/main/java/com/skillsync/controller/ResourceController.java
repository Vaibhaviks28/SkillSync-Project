package com.skillsync.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.skillsync.exception.ResourceNotFound;
import com.skillsync.model.Resource;
import com.skillsync.model.UserSkill;
import com.skillsync.repository.ResourceRepository;
import com.skillsync.repository.UserSkillRepository;
import com.skillsync.service.ResourceService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private UserSkillRepository userSkillRepository;

    @PostMapping
    public ResponseEntity<?> createResource(@RequestBody Map<String, Object> resourceData) {
        try {
            String title = (String) resourceData.get("title");
            String type = (String) resourceData.get("type");
            String url = (String) resourceData.get("url");
            Long skillId = Long.parseLong(resourceData.get("skillId").toString());
            String folderName = resourceData.containsKey("folderName") ? (String) resourceData.get("folderName") : null;

            Resource resource = resourceService.createResource(title, type, url, skillId, folderName);
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Invalid resource data", "message", e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}/resources")
    public ResponseEntity<?> getUserResources(@PathVariable("userId") Long userId) {
        try {
            List<UserSkill> userSkills = userSkillRepository.findByUserId(userId);
            List<Long> skillIds = userSkills.stream().map(us -> us.getSkill().getId()).collect(Collectors.toList());
            List<Resource> resources = resourceRepository.findBySkillIdIn(skillIds);
            return ResponseEntity.ok(resources);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching user resources: " + e.getMessage());
        }
    }

    @GetMapping("/folder/{folderName}")
    public ResponseEntity<List<Resource>> getResourcesByFolder(@PathVariable String folderName) {
        return ResponseEntity.ok(resourceService.getResourcesByFolderName(folderName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        Optional<Resource> r = resourceService.getResourceById(id);
        return r.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        if (!resourceService.existsById(id)) {
            throw new ResourceNotFound("Resource with ID " + id + " not found");
        }
        resourceService.deleteResource(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/skill/{skillId}")
    public ResponseEntity<List<Resource>> getResourcesBySkill(@PathVariable Long skillId) {
        List<Resource> resources = resourceService.getResourcesBySkillId(skillId);
        if (resources == null || resources.isEmpty()) {
            throw new ResourceNotFound("No resources found for the selected skill.");
        }
        return ResponseEntity.ok(resources);
    }

    @GetMapping
    public ResponseEntity<List<Resource>> getAllResources() {
        return ResponseEntity.ok(resourceService.getAllResources());
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadResourceFile(@RequestParam("file") MultipartFile file,
                                                @RequestParam("title") String title,
                                                @RequestParam("type") String type,
                                                @RequestParam("skillId") Long skillId,
                                                @RequestParam(value = "folderName", required = false) String folderName) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected to upload");
        }
        try {
            Resource resource = resourceService.storeFile(file, folderName, skillId);
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }

    // ✅ Mark resource complete and update user progress
    @PutMapping("/{id}/complete")
    public ResponseEntity<Resource> markResourceCompleted(@PathVariable Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Resource not found with id: " + id));

        resource.setIsCompleted(true);
        Resource updated = resourceRepository.save(resource);

        Long skillId = resource.getSkill().getId();
        long total = resourceRepository.countBySkillId(skillId);
        long done  = resourceRepository.countBySkillIdAndIsCompletedTrue(skillId);
        int percent = (int) ((done * 100.0) / total);

        // update all UserSkill entries having this skillId
        List<UserSkill> userSkills = userSkillRepository.findBySkillId(skillId);
        for (UserSkill us : userSkills) {
            us.setProgressPercentage((float) percent);
            userSkillRepository.save(us);
        }
        return ResponseEntity.ok(updated);
    }
}
