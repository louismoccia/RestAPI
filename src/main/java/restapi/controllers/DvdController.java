package restapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import restapi.data.DvdDao;

import org.springframework.web.bind.annotation.*;
import restapi.models.Dvd;

import java.util.List;

@RestController
@RequestMapping("restapi")
public class DvdController {

    private final DvdDao dao;

    public DvdController(DvdDao dao) {
        this.dao = dao;
    }

    @GetMapping("/{dvds}")
    public List<Dvd> all(){
        return dao.getAll();
    }

    @GetMapping("/dvd/{id}")
    public ResponseEntity<Dvd> findDvdById(@PathVariable int id){

        Dvd searched = dao.findDvdById(id);
        if(searched == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(searched);
    }

    @GetMapping("/dvds/title/{title}")
    public ResponseEntity<List<Dvd>> findDvdByTitle(@PathVariable String title){

        List<Dvd> SearchTitle = dao.findDvdByTitle(title);
        if(SearchTitle == null){
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(SearchTitle);
    }

    @GetMapping("/dvds/year/{releaseYear}")
    public ResponseEntity<List<Dvd>> findDvdByReleaseYear(@PathVariable int year){

        List<Dvd> SearchYear = dao.findDvdByReleaseYear(year);
        if(SearchYear == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(SearchYear);

    }

    @GetMapping("/dvds/Director/{Name}")
    public ResponseEntity<List<Dvd>> findDvdByDirector(@PathVariable String Director){

        List<Dvd> SearchDirector = dao.findDvdByDirector(Director);
        if(SearchDirector == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(SearchDirector);

    }

    @GetMapping("/dvds/Rating/{Rating}")
    public ResponseEntity<List<Dvd>> findDvdByRating(@PathVariable String Rating){

        List<Dvd> SearchRating = dao.findDvdByRating(Rating);
        if(SearchRating == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(SearchRating);

    }

    @PostMapping("/dvd")
    @ResponseStatus(HttpStatus.CREATED)
    public Dvd add(@RequestBody Dvd dvd){
        return dao.add(dvd);
    }

    @PutMapping("/dvd/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody Dvd dvd){
        ResponseEntity updatedDvd = new ResponseEntity(HttpStatus.NOT_FOUND);

        if(id != dvd.getDvdId()){
            updatedDvd = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if(dao.update(dvd)){
            updatedDvd = new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return updatedDvd;
    }

    @DeleteMapping("/dvd/{id}")
    public ResponseEntity delete(@PathVariable int id){

        if(dao.delete(id)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
