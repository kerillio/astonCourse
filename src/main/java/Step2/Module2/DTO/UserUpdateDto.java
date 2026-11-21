package Step2.Module2.DTO;

import Step2.Module2.Models.User;

public record UserUpdateDto(
        Integer id,
        String name,
        String email,
        int age,
        String created_at
) {
    public void applyTo(User existingUser) {

        existingUser.setName(this.name);
        existingUser.setEmail(this.email);
        existingUser.setAge(this.age);
    }

}
