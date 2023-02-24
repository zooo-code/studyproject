package study.project.web.item.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * study.project.web.item.dto.QMemberItemDto is a Querydsl Projection type for MemberItemDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberItemDto extends ConstructorExpression<MemberItemDto> {

    private static final long serialVersionUID = -2096900132L;

    public QMemberItemDto(com.querydsl.core.types.Expression<String> loginId, com.querydsl.core.types.Expression<Long> itemId, com.querydsl.core.types.Expression<String> itemName, com.querydsl.core.types.Expression<Integer> stockQuantity, com.querydsl.core.types.Expression<String> createItemTime, com.querydsl.core.types.Expression<Integer> price) {
        super(MemberItemDto.class, new Class<?>[]{String.class, long.class, String.class, int.class, String.class, int.class}, loginId, itemId, itemName, stockQuantity, createItemTime, price);
    }

}

