package de.mr_shopper.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "einkaufslisten")
public class EinkaufsListe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "bezeichnung")
    private String bezeichnung;

    @Column(name = "erstelltAm")
    private Date erstelltAm;

    @Column(name = "aktiv")
    private boolean aktiv;

    public EinkaufsListe (String bezeichnung, Date erstelltAm, boolean aktiv) {
        this.bezeichnung = bezeichnung;
        this.erstelltAm = erstelltAm;
        this.aktiv = aktiv;
    }

    @Override
    public String toString() {
        return "EinkaufsListe [id=" + id + ", bezeichnung=" + bezeichnung + ", erstelltAm=" + erstelltAm + "]";
    }
}
