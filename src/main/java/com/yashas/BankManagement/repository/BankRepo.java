package com.yashas.BankManagement.repository;

import com.yashas.BankManagement.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BankRepo {

    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }
    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public void save(Bank b){
        String insert_query = "insert into account values (?,?,?,?,?)";
        int rows=jdbc.update(insert_query,b.getAccno(),b.getName(),b.getAadhaarno(),b.getPanno(),b.getBalance());
        System.out.println(rows);
    }

    public void delete(Bank b,String aadhaarNumber){
        String delete_query="delete from account where aadhaarno=?";
        int rows = jdbc.update(delete_query,aadhaarNumber);
        System.out.println(rows);
    }

    public void update(Bank b){
        String update_query = "update account set balance=? where aadhaarno=? and panno=?";
        jdbc.update(update_query,b.getBalance(),b.getAadhaarno(),b.getPanno());
    }

    public List<Bank> fetchDetails(){
        String fetch_query = "select * from account";
        RowMapper<Bank> mapper = new RowMapper<Bank>() {
            @Override
            public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
                Bank b = new Bank();
                b.setAccno(rs.getString("accno"));
                b.setName(rs.getString("name"));
                b.setAadhaarno(rs.getString("aadhaarno"));
                b.setPanno(rs.getString("panno"));
                b.setBalance(rs.getInt("balance"));
                return b;
            }
        };
        return jdbc.query(fetch_query,mapper);
    }
}
