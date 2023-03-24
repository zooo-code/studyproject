package study.project.domain.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.project.domain.file.repository.FileRepository;

@Service
@RequiredArgsConstructor
public class FileServiceV1 implements  FileService{

    private final FileRepository fileRepository;


    @Override
    public void deleteFile(Long fileId) {
        fileRepository.deleteById(fileId);
    }
}
