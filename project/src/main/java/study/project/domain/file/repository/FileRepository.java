package study.project.domain.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.project.domain.file.UploadFile;

public interface FileRepository extends JpaRepository<UploadFile, Long> {
}
