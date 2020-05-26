package com.main.erobu.dto;

import java.sql.Date;


public class EventDTO {

    private Integer id;
    private EditorDTO editorDTO;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private String location;
    private String description;
    private Double startingPrice;
    private EventCategoryDTO eventCategoryDTO;



    private Integer visualizationNo;
    public EventDTO() {
    }


    public EventDTO(Integer id, EditorDTO editorDTO, String name, Date dateStart, Date dateEnd, String location, String description, Double startingPrice, EventCategoryDTO eventCategoryDTO,Integer visualizationNo) {
        this.id = id;
        this.editorDTO = editorDTO;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.location = location;
        this.description = description;
        this.startingPrice = startingPrice;
        this.eventCategoryDTO = eventCategoryDTO;
        this.visualizationNo=visualizationNo;
    }

    public Integer getVisualizationNo() {
        return visualizationNo;
    }

    public void setVisualizationNo(Integer visualizationNo) {
        this.visualizationNo = visualizationNo;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EditorDTO getEditorDTO() {
        return editorDTO;
    }

    public void setEditorDTO(EditorDTO editorDTO) {
        this.editorDTO = editorDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
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

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public EventCategoryDTO getEventCategoryDTO() {
        return eventCategoryDTO;
    }

    public void setEventCategoryDTO(EventCategoryDTO eventCategoryDTO) {
        this.eventCategoryDTO = eventCategoryDTO;
    }


    public static class Builder {
        private Integer nestedId;
        private EditorDTO nestedEditorDTO;
        private String nestedName;
        private Date nestedDateStart;
        private Date nestedDateEnd;
        private String nestedLocation;
        private String nestedDescription;
        private Double nestedStartingPrice;
        private EventCategoryDTO nestedEventCategoryDTO;
        private Integer nestedVisualizationNo;
        public Builder visualizationNo(Integer visualizationNo) {
            this.nestedVisualizationNo = visualizationNo;
            return this;
        }
        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder editorDTO(EditorDTO editorDTO) {
            this.nestedEditorDTO = editorDTO;
            return this;
        }

        public Builder dateStart(Date dateStart) {
            this.nestedDateStart = dateStart;
            return this;
        }

        public Builder dateEnd(Date dateEnd) {
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

        public Builder startingPrice(Double startingPrice) {
            this.nestedStartingPrice = startingPrice;
            return this;
        }

        public Builder eventCategoryDTO(EventCategoryDTO eventCategoryDTO) {
            this.nestedEventCategoryDTO = eventCategoryDTO;
            return this;
        }

        public EventDTO create() {
            return new EventDTO(nestedId, nestedEditorDTO, nestedName, nestedDateStart, nestedDateEnd, nestedLocation, nestedDescription, nestedStartingPrice, nestedEventCategoryDTO,nestedVisualizationNo);
        }

    }

    @Override
    public boolean equals(Object obj) {
        // self check
        if (this == obj)
            return true;
        // null check
        if (obj == null)
            return false;
        // type check and cast
        if (getClass() != obj.getClass())
            return false;
        EventDTO eventDTO = (EventDTO) obj;
        // field comparison
        return this.id.equals(eventDTO.getId()) &&
                this.editorDTO.equals(eventDTO.getEditorDTO()) &&
                this.name.equals(eventDTO.getName()) &&
                this.dateStart.equals(eventDTO.getDateStart()) &&
                this.dateEnd.equals(eventDTO.getDateEnd()) &&
                this.location.equals(eventDTO.getLocation()) &&
                this.description.equals(eventDTO.getDescription()) &&
                this.startingPrice.equals(eventDTO.getStartingPrice()) &&
                this.eventCategoryDTO.equals(eventDTO.getEventCategoryDTO());
    }
}
