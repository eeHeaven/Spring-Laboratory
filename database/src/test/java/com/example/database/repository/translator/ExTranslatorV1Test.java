package com.example.database.repository.translator;

import com.example.database.domain.Member;
import com.example.database.repository.exception.MyDBException;
import com.example.database.repository.exception.MyDuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import static com.example.database.connection.ConnectionConst.*;

@Slf4j
public class ExTranslatorV1Test {

    Repository repository;
    Service service;

    @BeforeEach
    void init(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,USERNAME,PASSWORD);
        repository = new Repository(dataSource);
        service = new Service(repository);
    }

    @Test
    void duplicateKeySave(){
        service.create("myId");
        service.create("myId");
    }

    @Slf4j
    @RequiredArgsConstructor
    static class Service{
        private final Repository repository;

        public void create(String memberId){
            try{
                repository.save(new Member(memberId, 1000));
                log.info("savedId={}",memberId);
            }catch(MyDuplicateKeyException e){
                log.info("키 중복, 복구 시도");
                String retryId = generateNewId(memberId);
                log.info("retry Id={}",retryId);
                repository.save(new Member(retryId,1000));
            }


        }
        private String generateNewId(String memberId){
            return memberId+ new Random().nextInt(10000);
        }
    }

    @RequiredArgsConstructor
    static class Repository{
        private final DataSource dataSource;

        public Member save(Member member) {
            String sql = "insert into member(member_id, money) values(?,?)";
            Connection con = null;
            PreparedStatement pstmt = null;

            try {
                con = dataSource.getConnection();
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1,member.getMemberId());
                pstmt.setInt(2,member.getMoney());
                pstmt.executeUpdate();
                return member;

            }catch(SQLException e){
                // h2 db
                if(e.getErrorCode() ==23505){//h2 pk 중복 에러코드
                    throw new MyDuplicateKeyException("중복 멤버입니다",e);
                }
                throw new MyDBException("db 커넥션 예외",e);

            }finally{
               JdbcUtils.closeStatement(pstmt);
               JdbcUtils.closeConnection(con);


            }
        }
    }
}
