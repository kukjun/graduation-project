package io.wisoft.testermatchingplatform.domain.category;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    public CategoryEntity() {

    }

    public CategoryEntity(String name) {
        this.name = name;
    }

    public static CategoryEntity from(final Category category) {
        return new CategoryEntity(
                category.getName()
        );
    }

    public Category toDomain() {
        return new Category(
                this.id,
                this.name
        );
    }
}
