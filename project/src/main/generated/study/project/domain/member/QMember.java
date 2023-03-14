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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final study.project.domain.address.QAddress address;

    public final DateTimePath<java.time.LocalDateTime> createMemberTime = createDateTime("createMemberTime", java.time.LocalDateTime.class);

    public final EnumPath<Grade> grade = createEnum("grade", Grade.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<study.project.domain.item.Item, study.project.domain.item.QItem> items = this.<study.project.domain.item.Item, study.project.domain.item.QItem>createList("items", study.project.domain.item.Item.class, study.project.domain.item.QItem.class, PathInits.DIRECT2);

    public final StringPath loginId = createString("loginId");

    public final ListPath<study.project.domain.order.Order, study.project.domain.order.QOrder> orders = this.<study.project.domain.order.Order, study.project.domain.order.QOrder>createList("orders", study.project.domain.order.Order.class, study.project.domain.order.QOrder.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final StringPath username = createString("username");

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new study.project.domain.address.QAddress(forProperty("address")) : null;
    }

}

