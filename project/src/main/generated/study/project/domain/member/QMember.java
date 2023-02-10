package study.project.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1347651230L;

    public static final QMember member = new QMember("member1");

    public final DateTimePath<java.time.LocalDateTime> createMemberTime = createDateTime("createMemberTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<study.project.domain.item.Item, study.project.domain.item.QItem> items = this.<study.project.domain.item.Item, study.project.domain.item.QItem>createList("items", study.project.domain.item.Item.class, study.project.domain.item.QItem.class, PathInits.DIRECT2);

    public final StringPath loginId = createString("loginId");

    public final StringPath password = createString("password");

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

