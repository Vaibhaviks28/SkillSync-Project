package com.skillsync.service;

import java.util.List;
import java.util.Optional;

import com.skillsync.model.Resource;

import org.springframework.web.multipart.MultipartFile;

//...

public interface ResourceService {

	List<Resource> getResourcesByFolderName(String folderName);

	Optional<Resource> getResourceById(Long id);

	void deleteResource(Long id);

	boolean existsById(Long id);

	boolean existsByTitleAndUrl(String title, String url);

	List<Resource> getResourcesBySkillId(Long skillId);

	Resource createResource(String title, String type, String url, Long skillId, String folderName);

	List<Resource> getAllResources();

	Resource storeFile(MultipartFile file, String folderName, Long skillId) throws Exception;

	Resource markResourceCompleted(Long resourceId);


}
