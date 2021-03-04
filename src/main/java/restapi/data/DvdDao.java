package restapi.data;

import restapi.models.Dvd;

import java.util.List;

public interface DvdDao {

    Dvd add(Dvd dvd);

    List<Dvd> getAll();

    Dvd findDvdById(int id);

    List<Dvd> findDvdByTitle(String title);

    List<Dvd> findDvdByReleaseYear(int year);

    List<Dvd> findDvdByDirector(String name);

    List<Dvd> findDvdByRating(String rating);

    boolean update(Dvd dvd);

    boolean delete(int id);
}
