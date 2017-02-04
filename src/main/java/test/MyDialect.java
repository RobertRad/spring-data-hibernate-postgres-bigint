package test;

import org.hibernate.dialect.PostgreSQL94Dialect;
import org.hibernate.type.StandardBasicTypes;

import java.sql.Types;

public class MyDialect extends PostgreSQL94Dialect {

	public MyDialect() {
		//registerHibernateType( Types.BIGINT, StandardBasicTypes.LONG.getName() );
	}
}
