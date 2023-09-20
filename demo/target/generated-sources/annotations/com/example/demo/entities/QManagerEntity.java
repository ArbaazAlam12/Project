package com.example.demo.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QManagerEntity is a Querydsl query type for ManagerEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QManagerEntity extends EntityPathBase<ManagerEntity> {

    private static final long serialVersionUID = -583857571L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QManagerEntity managerEntity = new QManagerEntity("managerEntity");

    public final StringPath contact = createString("contact");

    public final QDepartmentEntity department_id;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath status = createString("status");

    public QManagerEntity(String variable) {
        this(ManagerEntity.class, forVariable(variable), INITS);
    }

    public QManagerEntity(Path<? extends ManagerEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QManagerEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QManagerEntity(PathMetadata metadata, PathInits inits) {
        this(ManagerEntity.class, metadata, inits);
    }

    public QManagerEntity(Class<? extends ManagerEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department_id = inits.isInitialized("department_id") ? new QDepartmentEntity(forProperty("department_id")) : null;
    }

}

