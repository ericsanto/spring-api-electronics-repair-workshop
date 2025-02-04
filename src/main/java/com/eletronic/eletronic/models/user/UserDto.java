package com.eletronic.eletronic.models.user;

public record UserDto(
        String name,
        String telephone,
        String email,
        String address) {

    public UserEntity tranformForUserEntity() {

        UserEntity userEntity = new UserEntity();

        userEntity.setName(this.name);
        userEntity.setAddress(this.address);
        userEntity.setEmail(email);
        userEntity.setTelephone(telephone);

        return  userEntity;

    }


}
