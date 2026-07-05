package org.example.dao;

import org.example.entity.Flats;
import org.example.entity.Resident;

import java.util.List;

public interface FlatsDAO {
    void save(Flats flat);
    void update(Flats flat);
    void delete(Long flatId);
    Flats findById(Long flatId);
    List<Flats> findAllFlats();
    List<Flats> findByFloor(int floor);
    List<Flats> findByBlock(String block);
    List<Flats> findByStatus(Flats.FlatStatus flatStatus);
    List<Resident> findResidentsByFlat(String flatNumber);
    Flats findByFlatNumber(String flatNumber);
}
