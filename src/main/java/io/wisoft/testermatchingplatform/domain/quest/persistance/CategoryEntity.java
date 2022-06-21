package io.wisoft.testermatchingplatform.domain.quest.persistance;

import io.wisoft.testermatchingplatform.domain.quest.Category;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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

    public CategoryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CategoryEntity from(final Category category) {
        return new CategoryEntity(
                category.getId(),
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
