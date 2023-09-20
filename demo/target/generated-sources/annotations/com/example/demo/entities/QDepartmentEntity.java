package com.example.demo.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDepartmentEntity is a Querydsl query type for DepartmentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDepartmentEntity extends EntityPathBase<DepartmentEntity> {

    private static final long serialVersionUID = 118422088L;

    public static final QDepartmentEntity departmentEntity = new QDepartmentEntity("departmentEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QDepartmentEntity(String variable) {
        super(DepartmentEntity.class, forVariable(variable));
    }

    public QDepartmentEntity(Path<? extends DepartmentEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDepartmentEntity(PathMetadata metadata) {
        super(DepartmentEntity.class, metadata);
    }

}

