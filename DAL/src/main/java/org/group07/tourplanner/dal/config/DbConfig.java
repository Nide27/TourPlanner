package org.group07.tourplanner.dal.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DbConfig {
    @Getter
    private String dbURL;
    @Getter
    private String dbUser;
    @Getter
    private String dbPassword;
}
