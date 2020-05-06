package lv.maeteman.cleanmap.service;

import lv.maeteman.cleanmap.model.Mark;
import lv.maeteman.cleanmap.repository.MarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService {

    private MarkRepository markRepository;

    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public List<Mark> fetchAll() {
        return markRepository.findAll();
    }

    public Mark saveMark(Mark mark) {
        return markRepository.saveAndFlush(mark);
    }
}
