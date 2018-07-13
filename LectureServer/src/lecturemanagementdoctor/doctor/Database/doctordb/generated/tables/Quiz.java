/*
 * This file is generated by jOOQ.
 */
package lecturemanagementdoctor.doctor.Database.doctordb.generated.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import lecturemanagementdoctor.doctor.Database.doctordb.generated.Indexes;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.Keys;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.Lecturedb;
import lecturemanagementdoctor.doctor.Database.doctordb.generated.tables.records.QuizRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
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
public class Quiz extends TableImpl<QuizRecord> {

    private static final long serialVersionUID = -1686385532;

    /**
     * The reference instance of <code>lecturedb.Quiz</code>
     */
    public static final Quiz QUIZ = new Quiz();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<QuizRecord> getRecordType() {
        return QuizRecord.class;
    }

    /**
     * The column <code>lecturedb.Quiz.quiz_id</code>.
     */
    public final TableField<QuizRecord, Integer> QUIZ_ID = createField("quiz_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>lecturedb.Quiz.quiz_name</code>.
     */
    public final TableField<QuizRecord, String> QUIZ_NAME = createField("quiz_name", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>lecturedb.Quiz</code> table reference
     */
    public Quiz() {
        this(DSL.name("Quiz"), null);
    }

    /**
     * Create an aliased <code>lecturedb.Quiz</code> table reference
     */
    public Quiz(String alias) {
        this(DSL.name(alias), QUIZ);
    }

    /**
     * Create an aliased <code>lecturedb.Quiz</code> table reference
     */
    public Quiz(Name alias) {
        this(alias, QUIZ);
    }

    private Quiz(Name alias, Table<QuizRecord> aliased) {
        this(alias, aliased, null);
    }

    private Quiz(Name alias, Table<QuizRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Quiz(Table<O> child, ForeignKey<O, QuizRecord> key) {
        super(child, key, QUIZ);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Lecturedb.LECTUREDB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.QUIZ_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<QuizRecord, Integer> getIdentity() {
        return Keys.IDENTITY_QUIZ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<QuizRecord> getPrimaryKey() {
        return Keys.KEY_QUIZ_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<QuizRecord>> getKeys() {
        return Arrays.<UniqueKey<QuizRecord>>asList(Keys.KEY_QUIZ_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Quiz as(String alias) {
        return new Quiz(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Quiz as(Name alias) {
        return new Quiz(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Quiz rename(String name) {
        return new Quiz(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Quiz rename(Name name) {
        return new Quiz(name, null);
    }
}
