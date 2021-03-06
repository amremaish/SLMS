/*
 * This file is generated by jOOQ.
 */
package lecturemanagementdoctor.doctor.Database.doctordb.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import lecturemanagementdoctor.doctor.Database.doctordb.Doctordb;
import lecturemanagementdoctor.doctor.Database.doctordb.Indexes;
import lecturemanagementdoctor.doctor.Database.doctordb.Keys;
import lecturemanagementdoctor.doctor.Database.doctordb.tables.records.DoctorRecord;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Doctor extends TableImpl<DoctorRecord> {

    private static final long serialVersionUID = 1803636239;

    /**
     * The reference instance of <code>doctordb.Doctor</code>
     */
    public static final Doctor DOCTOR = new Doctor();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DoctorRecord> getRecordType() {
        return DoctorRecord.class;
    }

    /**
     * The column <code>doctordb.Doctor.doctor_id</code>.
     */
    public final TableField<DoctorRecord, Integer> DOCTOR_ID = createField("doctor_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>doctordb.Doctor.doctor_name</code>.
     */
    public final TableField<DoctorRecord, String> DOCTOR_NAME = createField("doctor_name", SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>doctordb.Doctor.doctor_password</code>.
     */
    public final TableField<DoctorRecord, String> DOCTOR_PASSWORD = createField("doctor_password", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>doctordb.Doctor.doctor_email</code>.
     */
    public final TableField<DoctorRecord, String> DOCTOR_EMAIL = createField("doctor_email", SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>doctordb.Doctor.doctor_gender</code>.
     */
    public final TableField<DoctorRecord, String> DOCTOR_GENDER = createField("doctor_gender", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>doctordb.Doctor.doctor_course</code>.
     */
    public final TableField<DoctorRecord, String> DOCTOR_COURSE = createField("doctor_course", SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>doctordb.Doctor.doctor_phone</code>.
     */
    public final TableField<DoctorRecord, String> DOCTOR_PHONE = createField("doctor_phone", SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>doctordb.Doctor.doctor_department</code>.
     */
    public final TableField<DoctorRecord, String> DOCTOR_DEPARTMENT = createField("doctor_department", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>doctordb.Doctor.doctor_database</code>.
     */
    public final TableField<DoctorRecord, String> DOCTOR_DATABASE = createField("doctor_database", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>doctordb.Doctor</code> table reference
     */
    public Doctor() {
        this(DSL.name("Doctor"), null);
    }

    /**
     * Create an aliased <code>doctordb.Doctor</code> table reference
     */
    public Doctor(String alias) {
        this(DSL.name(alias), DOCTOR);
    }

    /**
     * Create an aliased <code>doctordb.Doctor</code> table reference
     */
    public Doctor(Name alias) {
        this(alias, DOCTOR);
    }

    private Doctor(Name alias, Table<DoctorRecord> aliased) {
        this(alias, aliased, null);
    }

    private Doctor(Name alias, Table<DoctorRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Doctordb.DOCTORDB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.DOCTOR_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<DoctorRecord, Integer> getIdentity() {
        return Keys.IDENTITY_DOCTOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DoctorRecord> getPrimaryKey() {
        return Keys.KEY_DOCTOR_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DoctorRecord>> getKeys() {
        return Arrays.<UniqueKey<DoctorRecord>>asList(Keys.KEY_DOCTOR_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Doctor as(String alias) {
        return new Doctor(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Doctor as(Name alias) {
        return new Doctor(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Doctor rename(String name) {
        return new Doctor(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Doctor rename(Name name) {
        return new Doctor(name, null);
    }
}
