package com.skillsync.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.skillsync.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findByFolderNameIgnoreCase(String folderName);
    boolean existsByTitleAndUrl(String title, String url);
    List<Resource> findBySkillId(Long skillId);
    List<Resource> findBySkillIdIn(List<Long> skillIds);
    long countBySkillId(Long skillId);
    long countBySkillIdAndIsCompletedTrue(Long skillId);

}
