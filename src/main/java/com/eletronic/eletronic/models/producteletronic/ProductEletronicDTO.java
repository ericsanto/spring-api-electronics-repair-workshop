package com.eletronic.eletronic.models.producteletronic;

import com.eletronic.eletronic.models.user.UserEntity;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductEletronicDTO(
        @Enumerated
        TypeEletronic typeEletronic,

        @Enumerated
        Mark mark,

        @NotNull
        @NotBlank
        String model,

        @NotNull
        @NotBlank
        String numberOfSerie,

        UserEntity idClient)
{


        public ProductEletronicEntity transformDtoForProductEletronicEntity() {
                ProductEletronicEntity prod = new ProductEletronicEntity();

                prod.setTypeEletronic(this.typeEletronic);
                prod.setModel(this.model);
                prod.setClient(this.idClient);
                prod.setMark(this.mark);
                prod.setNumberOfSerie(this.numberOfSerie);

                return prod;

        }

}
