package lecturemanagementdoctor.doctor.Database;

import lecturemanagementdoctor.doctor.Database.doctordb.Doctordb;
import lecturemanagementdoctor.doctor.Database.doctordb.tables.Doctor;
import lecturemanagementdoctor.doctor.Database.doctordb.tables.records.DoctorRecord;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lecturemanagementdoctor.doctor.ref.create_outerDB;

/**
 * @author Saad Alenany
 */
public class OuterDataBaseManager {

    public OuterDataBaseManager() {
        try {
            Class.forName("org.h2.Driver");
            String DBURL = "jdbc:h2:databases/doctordb";
            Connection connection = DriverManager.getConnection(DBURL);
            create_outerDB = DSL.using(connection, SQLDialect.H2);

            createTableDoctor();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InnerDataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createTableDoctor() {
        create_outerDB.createSchemaIfNotExists(Doctordb.DOCTORDB).execute();
        create_outerDB.createTableIfNotExists(Doctor.DOCTOR)
                .column(Doctor.DOCTOR.DOCTOR_ID)
                .column(Doctor.DOCTOR.DOCTOR_NAME)
                .column(Doctor.DOCTOR.DOCTOR_PASSWORD)
                .column(Doctor.DOCTOR.DOCTOR_EMAIL)
                .column(Doctor.DOCTOR.DOCTOR_GENDER)
                .column(Doctor.DOCTOR.DOCTOR_COURSE)
                .column(Doctor.DOCTOR.DOCTOR_PHONE)
                .column(Doctor.DOCTOR.DOCTOR_DEPARTMENT)
                .column(Doctor.DOCTOR.DOCTOR_DATABASE)
                .constraints(
                        DSL.constraint("PK_DOCTOR_ID").primaryKey(Doctor.DOCTOR.DOCTOR_ID),
                        DSL.unique(Doctor.DOCTOR.DOCTOR_NAME),
                        DSL.unique(Doctor.DOCTOR.DOCTOR_EMAIL),
                        DSL.unique(Doctor.DOCTOR.DOCTOR_COURSE)
                ).execute();
    }

//    @Description(" This will insert a Pojo object of a database table," +
//            " note that if the pojo is a subclass of another pojo," +
//            " then the Super class must be inserted before the sub")
    public Record insert(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String tableName = Doctordb.DOCTORDB + "." + DSL.table(DSL.name(o.getClass().getSimpleName()));
        String SQL = "INSERT INTO " + tableName + " ( ";
        for (int i = 0; i < fields.length; i++) {
            if (i == 0) {
                continue;
            }
            SQL += "\"" + DSL.field(fields[i].getName()) + "\"";
            if (i != fields.length - 1) {
                SQL += " , ";
            }
        }
        SQL += " ) VALUES ( ";
        for (int i = 0; i < fields.length; i++) {
            if (i == 0) {
                continue;
            }
            try {
                if (fields[i].getType() == Integer.class || fields[i].getType() == int.class || fields[i].getType() == boolean.class) {
                    SQL += fields[i].get(o);
                } else {
                    SQL += "'" + fields[i].get(o) + "'";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (i != fields.length - 1) {
                SQL += " , ";
            }
        }
        SQL += " );";
        System.out.println(SQL);
        if (create_outerDB.query(SQL).execute() == 1) {
            Result<Record> r = create_outerDB.selectFrom(tableName).fetch();
            return r.get(r.size() - 1);
        }
        return null;
    }

    //    @Description(" This will update a Pojo object of a database table," +
//            " note that if the pojo is a subclass of another pojo," +
//            " then the Super class must be updated as well")
    public Record update(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String tableName = Doctordb.DOCTORDB + ".\"" + DSL.table(o.getClass().getSimpleName()) + "\"";
        String SQL = "UPDATE " + tableName + " SET ";
        for (int i = 0; i < fields.length; i++) {
            SQL += DSL.field(tableName + "." + DSL.name(fields[i].getName())) + " = ";

            try {
                if (fields[i].getType() == Integer.class || fields[i].getType() == int.class || fields[i].getType() == boolean.class) {
                    SQL += fields[i].get(o);
                } else {
                    SQL += "'" + fields[i].get(o) + "'";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (i != fields.length - 1) {
                SQL += " , ";
            }
        }
        try {
            SQL += " WHERE " + DSL.field(tableName + "." + DSL.name(fields[0].getName().toLowerCase())) + " = " + fields[0].get(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        SQL += " ;";
        System.out.println(SQL);
        if (create_outerDB.query(SQL).execute() == 1) {
            Result<Record> r = create_outerDB.selectFrom(tableName).fetch();
            return r.get(r.size() - 1);
        }
        return null;
    }

//    @Description(" This will delete a Pojo object of a database table," +
//            " note that if the pojo is a subclass of another pojo," +
//            " then the Super class must be deleted after the sub")
    public boolean delete(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String tableName = Doctordb.DOCTORDB + "." + DSL.table(DSL.name(o.getClass().getSimpleName()));
        String SQL = null;
        try {
            SQL = "DELETE FROM " + tableName + " WHERE " + DSL.field(tableName + "." + DSL.name(fields[0].getName().toLowerCase())) + " = " + fields[0].get(o) + " ;";
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(SQL);
        return create_outerDB.query(SQL).execute() == 1;
    }

    //    @Description(" This will return a List of Record objects of a database table ")
    public Result<Record> getAll(String tableName) {
        return create_outerDB.selectFrom(Doctordb.DOCTORDB + "." + DSL.field(DSL.name(tableName))).fetch();
    }

    public DoctorRecord checkUsernameAndPassword(String username, String password){
        return create_outerDB.selectFrom(Doctor.DOCTOR).where(Doctor.DOCTOR.DOCTOR_NAME.eq(username)).and(Doctor.DOCTOR.DOCTOR_PASSWORD.eq(password)).fetchAny();
    }

}
