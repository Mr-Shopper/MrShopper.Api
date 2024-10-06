package de.mr_shopper.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "einkaufsitems")
public class EinkaufsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Inkrementiert das Feld automatisch
    private long id;

    @Column(name = "bezeichnung")
    private String bezeichnung;

    @Column(name = "aktiv")
    private boolean aktiv;

    public EinkaufsItem(String bezeichnung, boolean aktiv) {
        this.bezeichnung = bezeichnung;
        this.aktiv = aktiv;
    }

    @Override
    public String toString() {
        return "EinkaufsItem [id=" + id + ", bezeichnung=" + bezeichnung + ", aktiv=" + aktiv + "]";
    }
}
