package de.mr_shopper.controller;

import de.mr_shopper.model.EinkaufsItem;
import de.mr_shopper.model.EinkaufsListe;
import de.mr_shopper.repository.EinkaufsListeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EinkaufsListeController {

    @Autowired
    EinkaufsListeRepository einkaufsListeRepository;

    @GetMapping("/einkaufslisten")
    public ResponseEntity<List<EinkaufsListe>> getAllEinkaufsListen(@RequestParam(required = false) String bezeichnung) {
        try {
            List<EinkaufsListe> einkaufsListen = new ArrayList<EinkaufsListe>();

            if (bezeichnung == null) {
                einkaufsListeRepository.findAll().forEach(einkaufsListen::add);
            } else {
                einkaufsListeRepository.findByBezeichnungContaining(bezeichnung).forEach(einkaufsListen::add);
            }

            if (einkaufsListen.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(einkaufsListen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/einkaufslisten/{id}")
    public ResponseEntity<EinkaufsListe> getEinkaufsListeById(@PathVariable("id") long id) {
        Optional<EinkaufsListe> einkaufsListeData = einkaufsListeRepository.findById(id);

        if (einkaufsListeData.isPresent()) {
            return new ResponseEntity<>(einkaufsListeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/einkaufslisten")
    public ResponseEntity<EinkaufsListe> createEinkaufsListe(@RequestBody EinkaufsListe einkaufsListe) {
        try {
            EinkaufsListe _einkaufsListe = einkaufsListeRepository
                    .save(new EinkaufsListe(einkaufsListe.getBezeichnung(), einkaufsListe.getErstelltAm(), true));
            return new ResponseEntity<>(_einkaufsListe, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/einkaufslisten/{id}")
    public ResponseEntity<EinkaufsListe> updateEinkaufsListe(@PathVariable("id") long id, @RequestBody EinkaufsListe einkaufsListe) {
        Optional<EinkaufsListe> einkaufsListeData = einkaufsListeRepository.findById(id);

        if (einkaufsListeData.isPresent()) {
            EinkaufsListe _einkaufsListe = einkaufsListeData.get();
            _einkaufsListe.setBezeichnung(einkaufsListe.getBezeichnung());
            _einkaufsListe.setAktiv(einkaufsListe.isAktiv());

            return new ResponseEntity<>(einkaufsListeRepository.save(_einkaufsListe), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/einkaufslisten/{id}")
    public ResponseEntity<HttpStatus> deleteEinkaufsListe(@PathVariable("id") long id) {
        try {
            einkaufsListeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/einkaufslisten")
    public ResponseEntity<HttpStatus> deleteAllEinkaufsListen() {
        try {
            einkaufsListeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/einkaufslisten/aktiv")
    public ResponseEntity<List<EinkaufsListe>> findByPublished() {
        try {
            List<EinkaufsListe> einkaufsListen = einkaufsListeRepository.findByAktiv(true);

            if (einkaufsListen.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(einkaufsListen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
