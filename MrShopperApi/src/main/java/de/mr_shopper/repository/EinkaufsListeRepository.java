package de.mr_shopper.repository;

import de.mr_shopper.model.EinkaufsListe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EinkaufsListeRepository extends JpaRepository<EinkaufsListe, Long> {
    List<EinkaufsListe> findByAktiv(boolean aktiv);
    List<EinkaufsListe> findByBezeichnungContaining(String bezeichnung);
}
