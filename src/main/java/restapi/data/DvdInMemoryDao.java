package restapi.data;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import restapi.models.Dvd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("memory")
public class DvdInMemoryDao implements DvdDao {

    private static final List<Dvd> dvds = new ArrayList<>();

    @Override
    public Dvd add(Dvd dvd) {
        int nextId = dvds.stream()
                .mapToInt(i -> i.getDvdId())
                .max()
                .orElse(0) + 1;

        dvd.setDvdId(nextId);
        dvds.add(dvd);

        return dvd;
    }

    @Override
    public List<Dvd> getAll() {
        return new ArrayList<>(dvds);
    }

    @Override
    public Dvd findDvdById(int id) {
        return dvds.stream()
                .filter(i -> i.getDvdId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Dvd> findDvdByTitle(String title) {
        return dvds.stream()
                .filter(i -> i.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> findDvdByReleaseYear(int year) {
        return dvds.stream()
                .filter(i -> i.getReleaseYear() == year)
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> findDvdByDirector(String name) {
        return dvds.stream()
                .filter(i -> i.getDirector().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> findDvdByRating(String rating) {
        return dvds.stream()
                .filter(i -> i.getRating().contains(rating))
                .collect(Collectors.toList());
    }

    @Override
    public boolean update(Dvd dvd) {
        int index = 0;
        while(index < dvds.size() && dvds.get(index).getDvdId() != dvd.getDvdId()){
            index++;
        }

        if(index < dvds.size()){
            dvds.set(index, dvd);
        }

        return index < dvds.size();
    }

    @Override
    public boolean delete(int id) {
        return dvds.removeIf(i -> i.getDvdId() == id);
    }
}
