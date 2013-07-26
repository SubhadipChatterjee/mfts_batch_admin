/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.types;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import poc.springbatch.types.Person;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int i) throws SQLException {
        Person person = new Person();
        person.setId(rs.getString("ID"));
        person.setFname(rs.getString("FIRST_NAME"));
        person.setLname(rs.getString("LAST_NAME"));
        person.setDoj(rs.getDate("JOINED_AT"));
        person.setDept(rs.getString("DEPARTMENT"));
        return person;
    }
}
