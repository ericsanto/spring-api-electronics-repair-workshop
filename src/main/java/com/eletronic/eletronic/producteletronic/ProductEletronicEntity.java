package com.eletronic.eletronic.producteletronic;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name = "product_eletronic")
@AllArgsConstructor //gera um construtor com todos os parâmetros
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class    ProductEletronicEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Gera o id automaticamente
    @NotNull
    @Column(name = "id")
    private long id;

    @Column(name = "type_eletronic", nullable = false)
    @Enumerated(EnumType.STRING)// indica que o atributo typeEletronic é do tipo enum que é do tipo string
    @NotNull
    private TypeEletronic typeEletronic;

    @Column
    @Enumerated(EnumType.STRING)
    private Mark mark;

    @Column
    @NotNull
    @NotBlank
    private String model;


    @Column
    @NotNull
    @NotBlank
    private String numberOfSerie;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)// Não retorna o campo isDeleted no Get
    private boolean isDeleted = false;

    public ProductEletronicEntity(ProductEletronicDTO data) {
        this.typeEletronic = data.typeEletronic();
        this.mark = data.mark();
        this.model = data.model();
        this.numberOfSerie = data.numberOfSerie();

    }
}

