/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package heroku.java;

import java.sql.ResultSet;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HueAPI {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @RequestMapping(value = "/hue", produces = "application/json")
    String index() {
        final JSONArray jsArray = new JSONArray();
        final String sql = "SELECT tick FROM ticks";
        jdbcTemplate.query(sql, (final ResultSet rs) -> {
            while(rs.next()) {
                jsArray.put(rs.getTimestamp("tick"));
            }
        });
        return jsArray.toString();
    }
}