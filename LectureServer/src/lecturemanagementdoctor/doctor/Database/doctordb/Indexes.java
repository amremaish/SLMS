/*
 * This file is generated by jOOQ.
 */
package lecturemanagementdoctor.doctor.Database.doctordb;


import javax.annotation.Generated;

import lecturemanagementdoctor.doctor.Database.doctordb.tables.Doctor;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>doctordb</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index DOCTOR_PRIMARY = Indexes0.DOCTOR_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index DOCTOR_PRIMARY = Internal.createIndex("PRIMARY", Doctor.DOCTOR, new OrderField[] { Doctor.DOCTOR.DOCTOR_ID }, true);
    }
}