package sql;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Sql(scripts = {"/database/sql/truncate.sql",
        "/database/sql/employee_init.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public @interface SqlEmployeeInit {
}
