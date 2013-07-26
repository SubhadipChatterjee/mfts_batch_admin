/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.jdbc.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
public class PersonFinderQuerySetter implements PreparedStatementSetter {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());    
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setValues(PreparedStatement ps) throws SQLException {
        if (logger.isInfoEnabled()) {
                logger.info("Input to query->[firstName]=" + firstName);
            }
        ps.setString(1, firstName);
    }
}
