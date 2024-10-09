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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "einkaufsListeId")
    private long einkaufsListeId;

    @Column(name = "bezeichnung")
    private String bezeichnung;

    @Column(name = "menge")
    private double menge;

    @Column(name = "mengenEinheit")
    private String mengenEinheit;

    @Column(name = "aktiv")
    private boolean aktiv;

    public EinkaufsItem(long einkaufsListeId, String bezeichnung, double menge, String mengenEinheit, boolean aktiv) {
        this.einkaufsListeId = einkaufsListeId;
        this.bezeichnung = bezeichnung;
        this.menge = menge;
        this.mengenEinheit = mengenEinheit;
        this.aktiv = aktiv;
    }

    @Override
    public String toString() {
        return "EinkaufsItem [id=" + id + ", bezeichnung=" + bezeichnung + ", aktiv=" + aktiv + "]";
    }
}
