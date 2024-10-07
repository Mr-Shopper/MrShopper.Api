package de.mr_shopper.controller;

import de.mr_shopper.model.EinkaufsItem;
import de.mr_shopper.repository.EinkaufsItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EinkaufsItemController {

    @Autowired
    EinkaufsItemRepository einkaufsItemRepository;

    @GetMapping("/einkaufsitems")
    public ResponseEntity<List<EinkaufsItem>> getAllEinkaufsItems(@RequestParam(required = false) String bezeichnung) {
        try {
            List<EinkaufsItem> einkaufsItems = new ArrayList<EinkaufsItem>();

            if (bezeichnung == null) {
                einkaufsItemRepository.findAll().forEach(einkaufsItems::add);
            } else {
                einkaufsItemRepository.findByBezeichnungContaining(bezeichnung).forEach(einkaufsItems::add);
            }

            if (einkaufsItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(einkaufsItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/einkaufsitems/{id}")
    public ResponseEntity<EinkaufsItem> getEinkaufsItemById(@PathVariable("id") long id) {
        Optional<EinkaufsItem> einkaufsItemData = einkaufsItemRepository.findById(id);

        if (einkaufsItemData.isPresent()) {
            return new ResponseEntity<>(einkaufsItemData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/einkaufsitems")
    public ResponseEntity<EinkaufsItem> createEinkaufsItem(@RequestBody EinkaufsItem einkaufsItem) {
        try {
            EinkaufsItem _einkaufsitem = einkaufsItemRepository
                    .save(new EinkaufsItem(einkaufsItem.getBezeichnung(), true));
            return new ResponseEntity<>(_einkaufsitem, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/einkaufsitems/{id}")
    public ResponseEntity<EinkaufsItem> updateEinkaufsItem(@PathVariable("id") long id, @RequestBody EinkaufsItem einkaufsItem) {
        Optional<EinkaufsItem> einkaufsItemData = einkaufsItemRepository.findById(id);

        if (einkaufsItemData.isPresent()) {
            EinkaufsItem _einkaufsItem = einkaufsItemData.get();
            _einkaufsItem.setBezeichnung(einkaufsItem.getBezeichnung());
            _einkaufsItem.setAktiv(einkaufsItem.isAktiv());

            return new ResponseEntity<>(einkaufsItemRepository.save(_einkaufsItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/einkaufsitems/{id}")
    public ResponseEntity<HttpStatus> deleteEinkaufsItem(@PathVariable("id") long id) {
        try {
            einkaufsItemRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/einkaufsitems")
    public ResponseEntity<HttpStatus> deleteAllEinkaufsItems() {
        try {
            einkaufsItemRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/einkaufsitems/aktiv")
    public ResponseEntity<List<EinkaufsItem>> findByPublished() {
        try {
            List<EinkaufsItem> einkaufsItems = einkaufsItemRepository.findByAktiv(true);

            if (einkaufsItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(einkaufsItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
