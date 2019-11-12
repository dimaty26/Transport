package org.ncstudy.authentication.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTasks {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(SchedulerTasks.class);

    public SchedulerTasks(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(cron = "0 10 3 ? * SAT") // Every Saturday at 3:10
    public void clearRefreshTokens() {
        jdbcTemplate.execute("TRUNCATE TABLE oauth_refresh_token");
        log.info("Refresh tokens were deleted");
    }

    @Scheduled(cron = "0 0 3 * * ?") // Every day at 3:00
    public void clearAccessTokens() {
        jdbcTemplate.execute("TRUNCATE TABLE oauth_access_token");
        log.info("Access tokens were deleted");
    }

//    @Scheduled(cron = "0 */2 * * * ?")
//    public void clearAccessTokensTest() {
//        jdbcTemplate.execute("TRUNCATE TABLE oauth_access_token");
//    }
}
