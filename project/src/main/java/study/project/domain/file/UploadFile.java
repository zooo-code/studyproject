package study.project.domain.file;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.project.domain.item.Item;

import javax.persistence.*;

import static javax.persistence.FetchType.*;


@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UploadFile {

    @GeneratedValue
    @Id  @Column(name = "uploadFile_id")
    private Long id;
    private String uploadFileName;
    private String storeFileName;

    @OneToOne(mappedBy = "imageFile",fetch = LAZY)
    private Item item;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
