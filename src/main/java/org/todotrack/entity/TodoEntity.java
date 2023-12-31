package org.todotrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "todo")
public class TodoEntity
{
        public enum Status {
                WIP, DONE
        }
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @NotBlank
        @Column(name = "title", nullable = false)
        private String title;

        @NotBlank
        @Column(name = "description", nullable = false)
        private String description;

        @NotNull
        @Column(name = "startDate", nullable = false)
        private LocalDate startDate;

        @NotNull
        @Column(name = "targetDate", nullable = false)
        private LocalDate targetDate;

        @Enumerated(EnumType.STRING)
        @Column(name = "status", columnDefinition = "varchar(255) default 'WIP'")
        private Status status = Status.WIP;


        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public LocalDate getStartDate() {
                return startDate;
        }

        public void setStartDate(LocalDate startDate) {
                this.startDate = startDate;
        }

        public LocalDate getTargetDate() {
                return targetDate;
        }

        public void setTargetDate(LocalDate targetDate) {
                this.targetDate = targetDate;
        }

        public Status getStatus() {
                return status;
        }

        public void setStatus(Status status) {
                this.status = status;
        }
}
