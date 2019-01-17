package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.model.Room;
import pl.coderslab.repository.RoomRepository;

public class RoomConverter implements Converter<String, Room> {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room convert(String s) { return roomRepository.findOne(Long.valueOf(s)); }
}