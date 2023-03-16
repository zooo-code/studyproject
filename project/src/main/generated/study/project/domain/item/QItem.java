package study.project.domain.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = -1896718530L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItem item = new QItem("item");

    public final StringPath createItemTime = createString("createItemTime");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUploadFile imageFile;

    public final study.project.domain.item.category.QItemCategory itemCategory;

    public final StringPath itemName = createString("itemName");

    public final study.project.domain.member.QMember member;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> stockQuantity = createNumber("stockQuantity", Integer.class);

    public QItem(String variable) {
        this(Item.class, forVariable(variable), INITS);
    }

    public QItem(Path<? extends Item> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItem(PathMetadata metadata, PathInits inits) {
        this(Item.class, metadata, inits);
    }

    public QItem(Class<? extends Item> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.imageFile = inits.isInitialized("imageFile") ? new QUploadFile(forProperty("imageFile"), inits.get("imageFile")) : null;
        this.itemCategory = inits.isInitialized("itemCategory") ? new study.project.domain.item.category.QItemCategory(forProperty("itemCategory")) : null;
        this.member = inits.isInitialized("member") ? new study.project.domain.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

