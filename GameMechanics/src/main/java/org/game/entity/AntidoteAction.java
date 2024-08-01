package org.game.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.game.entity.key.AntidoteActionKey;

/* This entity reflects the database table of the same name. It is a junction
* table for Antidote and ActionType*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AntidoteAction {

    @EmbeddedId
    AntidoteActionKey antidoteActionKey;
}
