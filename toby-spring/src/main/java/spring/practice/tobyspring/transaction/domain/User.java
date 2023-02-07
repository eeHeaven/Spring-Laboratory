package spring.practice.tobyspring.transaction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    String id;
    Level level;
    int point;
}
