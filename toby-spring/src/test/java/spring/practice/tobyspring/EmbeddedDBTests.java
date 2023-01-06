package spring.practice.tobyspring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;
import java.util.Map;

public class EmbeddedDBTests {
    //Datasource의 서브 인터페이스
    EmbeddedDatabase db;
    JdbcTemplate template;

    @BeforeEach
    void setUp(){
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:schema.sql")
                .addScript("classpath:data.sql")
                .build();

        template = new JdbcTemplate(db);
    }
    @AfterEach
    void tearDown(){
        db.shutdown(); //EmbeddedDatabase가 제공하는 기능
    }
    @Test
    public void initData(){
        assert(template.queryForObject("select count(*) from sqlmap",Integer.class).equals(2));
        List<Map<String,Object>> list = template.queryForList("select * from sqlmap order by key_");
        assert(list.get(0).get("key_").equals("KEY1"));
        assert(list.get(0).get("sql_").equals("SQL1"));
        assert(list.get(1).get("key_").equals("KEY2"));
        assert(list.get(1).get("sql_").equals("SQL2"));

    }
    @Test
    public void insert(){
        template.update("insert into sqlmap(key_,sql_) values(?,?)","KEY3","SQL3");
        assert(template.queryForObject("select count(*) from sqlmap",Integer.class).equals(3));
    }

}
