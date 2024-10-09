package de.mr_shopper.repository;

import de.mr_shopper.model.EinkaufsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EinkaufsItemRepository extends JpaRepository<EinkaufsItem, Long> {

    List<EinkaufsItem> findByEinkaufsListeId(long einkaufsListeId);
    List<EinkaufsItem> findByAktiv(boolean aktiv);
    List<EinkaufsItem> findByBezeichnungContaining(String bezeichnung);

}
