package com.main.erobu.data.entry;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@Entity(name = "events")
public class Event {

    @Id
    @Column(name = "id_event")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_editor")
    private Editor editor;

    @Column(name = "name")
    private String name;

    @Column(name = "date_start")
    private Timestamp dateStart;

    @Column(name = "date_end")
    private Timestamp dateEnd;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "visualizations_no")
    private Integer visualizationNo;

    @ManyToOne
    @JoinColumn(name = "id_event_category")
    private EventCategory eventCategory;

    public Event() {
    }


    public Event(Integer id, Editor editor, String name, Timestamp dateStart, Timestamp dateEnd, String location, String description, EventCategory eventCategory,Integer visualizationNo) {
        this.id = id;
        this.editor = editor;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.location = location;
        this.description = description;
        this.eventCategory = eventCategory;
        this.visualizationNo=visualizationNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }
    public Integer getVisualizationNo() {
        return visualizationNo;
    }

    public void setVisualizationNo(Integer visualizationNo) {
        this.visualizationNo = visualizationNo;
    }

    public static class Builder {
        private Integer nestedId;
        private Editor nestedEditor;
        private String nestedName;
        private Timestamp nestedDateStart;
        private Timestamp nestedDateEnd;
        private String nestedLocation;
        private String nestedDescription;
        private EventCategory nestedEventCategory;
        private Integer nestedVisualizationNo;

        public Builder visualizationNo(Integer visualizationNo) {
            if(Objects.isNull(visualizationNo)){
               this.nestedVisualizationNo=0;
            }
           else{ this.nestedVisualizationNo = visualizationNo;}
            return this;
        }
        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder editorDTO(Editor editor) {
            this.nestedEditor = editor;
            return this;
        }

        public Builder dateStart(Timestamp dateStart) {
            this.nestedDateStart = dateStart;
            return this;
        }

        public Builder dateEnd(Timestamp dateEnd) {
            this.nestedDateEnd = dateEnd;
            return this;
        }

        public Builder name(String name) {
            this.nestedName = name;
            return this;
        }

        public Builder location(String location) {
            this.nestedLocation = location;
            return this;
        }

        public Builder description(String description) {
            this.nestedDescription = description;
            return this;
        }

        public Builder eventCategory(EventCategory eventCategory) {
            this.nestedEventCategory = eventCategory;
            return this;
        }



        public Event create() {
            return new Event(nestedId, nestedEditor, nestedName, nestedDateStart, nestedDateEnd, nestedLocation, nestedDescription, nestedEventCategory,nestedVisualizationNo);
        }

    }
}
