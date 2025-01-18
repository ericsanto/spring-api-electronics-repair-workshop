package com.eletronic.eletronic.user;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = UserEntity.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "telephone")
    @NotNull
    @NotBlank
    private String telephone;

    @Column(name = "address")
    @NotNull
    @NotBlank
    private String address;

    @Column(name = "email")
    @NotNull
    @NotBlank
    private String email;


}
