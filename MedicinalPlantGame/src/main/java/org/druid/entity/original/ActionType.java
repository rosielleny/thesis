package org.druid.entity.original;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* This entity reflects the database table which stores the types of gameplay actions
 * that may be involved in making an antidote. One antidote can have many associated actions, but
 * could only have one. */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionType {

    @Id
    private int actionId;
    private String actionType; // Eg. Cut, Mash, Stir
}
