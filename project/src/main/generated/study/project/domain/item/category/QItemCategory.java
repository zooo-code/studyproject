package study.project.domain.item.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemCategory is a Querydsl query type for ItemCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemCategory extends EntityPathBase<ItemCategory> {

    private static final long serialVersionUID = -965841002L;

    public static final QItemCategory itemCategory = new QItemCategory("itemCategory");

    public final ListPath<Category, QCategory> categories = this.<Category, QCategory>createList("categories", Category.class, QCategory.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<study.project.domain.item.Item, study.project.domain.item.QItem> items = this.<study.project.domain.item.Item, study.project.domain.item.QItem>createList("items", study.project.domain.item.Item.class, study.project.domain.item.QItem.class, PathInits.DIRECT2);

    public QItemCategory(String variable) {
        super(ItemCategory.class, forVariable(variable));
    }

    public QItemCategory(Path<? extends ItemCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemCategory(PathMetadata metadata) {
        super(ItemCategory.class, metadata);
    }

}

