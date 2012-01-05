package dbmigrate.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import dbmigrate.model.db.Column;
import dbmigrate.model.db.DbConnector;
import dbmigrate.model.db.IColumn;
import dbmigrate.model.db.Table;
import dbmigrate.model.db.TypeEnum;
import dbmigrate.model.operation.CreateTableOperationDescriptor;
import dbmigrate.model.operation.DropTableOperationDescriptor;

public class CreateDropTablePostgres extends TestCase {
	private Connection connection = null;
	private String tableName = "TabelaKamilaIKonrada";
	private String columnName = "KolumnaKamilaIKonrada";

	@Override
	public void setUp() {
		try {
			connection = new DbConnector().getConnection("postgresql", "149.156.205.250:13833", "dbmigrate", "dbmigrate", "dbmigrate");
		} catch (Exception e) {
			System.out.println("Connection to database failed.");
			e.printStackTrace();
		}
	}

	public void testAddTable() {
		Column column = new Column();
		column.setName(columnName);
		column.setType(TypeEnum.INT);

		Table table = new Table();
		table.setName(tableName);
		List<IColumn> columns = new ArrayList<IColumn>();
		columns.add(column);
		table.setColumns(columns);

		CreateTableOperationDescriptor cto = new CreateTableOperationDescriptor();
		cto.setTable(table);

		CreateTableExecutor cte = new CreateTableExecutor(null);
		cte.setConnection(connection);

		System.out.println("Stworzony SQL:" + cte.createSql(cto));
		try {
			cte.execute(cto);
			System.out.println("IF EXISTS: " + connection.createStatement().execute(checkIfColumnExist(tableName, columnName)));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			DropTableOperationDescriptor descriptor = new DropTableOperationDescriptor(table);
			try {
				(new DropTableExecutor(connection)).execute(descriptor);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private String checkIfColumnExist(String tableName, String columnName) {
		return String.format("SELECT attname FROM pg_attribute WHERE attrelid = (SELECT oid FROM pg_class WHERE relname = '%s') AND attname = '%s';", tableName, columnName);
	}
}
