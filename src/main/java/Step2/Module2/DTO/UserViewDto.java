package Step2.Module2.DTO;

import Step2.Module2.Models.User;

public record UserViewDto(
        Long id,
        String name,
        String email,
        Integer age,
        String created_at
) {
    public static UserViewDto fromEntity(User user) {
        return new UserViewDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getCreated_at()
        );
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%d лет) — %s (создан: %s)",
                id, name, age, email, created_at);
    }
}
