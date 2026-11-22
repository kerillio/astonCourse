package Step2.Module2.DTO;

import Step2.Module2.Models.User;

public record UserCreateDto(
        String name,
        String email,
        Integer age
) {
    public User toEntity() {
        User user = new User();

        user.setName(this.name);
        user.setEmail(this.email);
        user.setAge(this.age);

        return user;
    }
}
