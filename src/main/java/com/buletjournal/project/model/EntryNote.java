package com.buletjournal.project.model;


import jakarta.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Table(name = "journal")

public class EntryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "note", nullable = false)

    private String note;


    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Progress progress;


    @Column(name = "important")
    private Boolean important;


    @Column(name = "dateForNote")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public EntryNote() {
    }

    public EntryNote(String note, Type type, Progress progress, Boolean important, Date date
    ) {
        super();
        this.note = note;
        this.type = type;
        this.progress = progress;
        this.important = important;
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public Boolean getImportant() {
        return important;
    }

    public void setImportant(Boolean important) {
        this.important = important;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
